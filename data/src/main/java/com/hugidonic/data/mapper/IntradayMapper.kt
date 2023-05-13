package com.hugidonic.data.mapper

import com.hugidonic.data.remote.dto.IntradayInfoDto
import com.hugidonic.domain.models.IntradayInfoModel
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

fun IntradayInfoDto.toIntradayInfoModel(): IntradayInfoModel {
    val pattern = "yyyy-MM-dd H:mm:ss"

    val formatter = DateTimeFormatter.ofPattern(pattern, Locale.getDefault())
    val localDateTime = LocalDateTime.parse(timestamp, formatter)

    return IntradayInfoModel(
        date = localDateTime,
        close = close
    )
}

