package com.hugidonic.data.repositories

import com.hugidonic.domain.models.CompanyInfoModel
import com.hugidonic.domain.models.CompanyListingModel
import com.hugidonic.domain.models.IntradayInfoModel
import com.hugidonic.domain.models.Resource
import com.hugidonic.domain.repository.StockRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeStockRepository: StockRepository {

    private var isNetworkError = false

    fun shouldReturnNetworkError(isError: Boolean) {
        isNetworkError = isError
    }

    override suspend fun getCompanyListings(
        isFetchFromRemote: Boolean,
        query: String
    ): Flow<Resource<List<CompanyListingModel>>> = flow{
        emit(Resource.Loading(true))
        if (isNetworkError) {
            emit(Resource.Error("Couldn't load data"))
        } else {
            val data = emptyList<CompanyListingModel>()
            emit(Resource.Success(data))
        }
    }

    override suspend fun getIntradayInfo(symbol: String): Resource<List<IntradayInfoModel>> {
        return if (isNetworkError) {
            Resource.Error("Couldn't load data")
        } else {
            val data = emptyList<IntradayInfoModel>()
            Resource.Success(data)
        }
    }

    override suspend fun getCompanyInfo(symbol: String): Resource<CompanyInfoModel> {
        return if (isNetworkError) {
            Resource.Error("Couldn't load data")
        } else {
            val data = CompanyInfoModel(
                name = "name",
                symbol = "symbol",
                industry = "industry",
                description = "description",
                country = "country"
            )
            Resource.Success(data)
        }
    }
}