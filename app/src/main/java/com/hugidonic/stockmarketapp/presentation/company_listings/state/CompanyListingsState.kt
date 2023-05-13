package com.hugidonic.stockmarketapp.presentation.company_listings.state

import com.hugidonic.domain.models.CompanyListingModel

data class CompanyListingsState(
    val companies: List<CompanyListingModel> = emptyList(),
    val isLoading: Boolean = false,
    val isRefreshing: Boolean = false,
    val searchQuery: String = "",
)