package com.hugidonic.domain.repository

import com.hugidonic.domain.models.CompanyListingModel
import com.hugidonic.domain.models.Resource
import kotlinx.coroutines.flow.Flow

interface StockRepository {
    suspend fun getCompanyListings(
        isFetchFromRemote: Boolean,
        query: String,
    ): Flow<Resource<List<CompanyListingModel>>>
}