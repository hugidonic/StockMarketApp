package com.hugidonic.data.csv

import com.hugidonic.data.mapper.toIntradayInfoModel
import com.hugidonic.data.remote.dto.IntradayInfoDto
import com.hugidonic.domain.models.IntradayInfoModel
import com.opencsv.CSVReader
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.InputStream
import java.io.InputStreamReader
import java.time.LocalDate
import javax.inject.Inject

class IntradayParser @Inject constructor(): CSVParser<IntradayInfoModel> {
    override suspend fun parse(stream: InputStream): List<IntradayInfoModel> {
        val csvReader = CSVReader(InputStreamReader(stream))

        return withContext(Dispatchers.IO) {
            csvReader
                .readAll()
                .drop(1)
                .mapNotNull { line ->
                    val timestamp = line.getOrNull(0) ?: return@mapNotNull null
                    val close = line.getOrNull(4) ?: return@mapNotNull null
                    val dto = IntradayInfoDto(timestamp, close.toDouble())

                    dto.toIntradayInfoModel()
                }
                .filter {
                    val yesterday = LocalDate.now().minusDays(4).dayOfMonth
                    it.date.dayOfMonth == yesterday
                }
                .sortedBy {
                    it.date.hour
                }
                .also {
                    csvReader.close()
                }
        }
    }
}