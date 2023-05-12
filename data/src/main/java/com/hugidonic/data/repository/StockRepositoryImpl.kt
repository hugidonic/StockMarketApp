package com.hugidonic.data.repository

import com.hugidonic.data.csv.CSVParser
import com.hugidonic.data.db.StockDao
import com.hugidonic.data.mapper.toCompanyListingEntity
import com.hugidonic.data.mapper.toCompanyListingModel
import com.hugidonic.data.remote.StockApiService
import com.hugidonic.domain.models.CompanyListingModel
import com.hugidonic.domain.models.Resource
import com.hugidonic.domain.repository.StockRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StockRepositoryImpl @Inject constructor(
    val api: StockApiService,
    val stockDao: StockDao,
    val companyListingsParser: CSVParser<CompanyListingModel>
): StockRepository {

    override suspend fun getCompanyListings(
        isFetchFromRemote: Boolean,
        query: String
    ): Flow<Resource<List<CompanyListingModel>>> {
        return flow {
            emit(Resource.Loading(true))
            val localListings = stockDao.searchCompanyListing(query=query)

            emit(Resource.Success(
                data=localListings.map { it.toCompanyListingModel() }
            ))

            val isDbEmpty = localListings.isEmpty() && query.isBlank()
            val shouldLoadFromCache = !isDbEmpty && !isFetchFromRemote

            if (shouldLoadFromCache) {
                emit(Resource.Loading(false))
                return@flow
            }

            val remoteListings = try {
                val response = api.getListings()
                companyListingsParser.parse(response.byteStream())
            } catch (e: IOException) {
                e.printStackTrace()
                emit(Resource.Error("Couldn't load data"))
                null
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Resource.Error("Couldn't load data"))
                null
            }

            remoteListings?.let { listings ->
                stockDao.clearCompanyListings()
                stockDao.insertCompanyListings(
                    companyListingEntities = listings.map { it.toCompanyListingEntity() }
                )
                emit(Resource.Success(
                    data = stockDao
                        .searchCompanyListing(query="")
                        .map { it.toCompanyListingModel() }
                ))
                emit(Resource.Loading(false))
            }

        }

    }
}











