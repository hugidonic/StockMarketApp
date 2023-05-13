package com.hugidonic.domain.repository

import com.hugidonic.domain.models.CompanyInfoModel
import com.hugidonic.domain.models.CompanyListingModel
import com.hugidonic.domain.models.IntradayInfoModel
import com.hugidonic.domain.models.Resource
import kotlinx.coroutines.flow.Flow

interface StockRepository {
    suspend fun getCompanyListings(
        isFetchFromRemote: Boolean,
        query: String,
    ): Flow<Resource<List<CompanyListingModel>>>

    suspend fun getIntradayInfo(
        symbol: String,
    ): Resource<List<IntradayInfoModel>>

    suspend fun getCompanyInfo(
        symbol: String
    ): Resource<CompanyInfoModel>
}