package com.hugidonic.stockmarketapp.presentation.company_info.state

import com.hugidonic.domain.models.CompanyInfoModel
import com.hugidonic.domain.models.IntradayInfoModel

data class CompanyInfoState(
    val stockInfos: List<IntradayInfoModel> = emptyList(),
    val company: CompanyInfoModel? = null,
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
)
