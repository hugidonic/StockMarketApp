package com.hugidonic.stockmarketapp.presentation.company_info.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hugidonic.domain.models.Resource
import com.hugidonic.domain.repository.StockRepository
import com.hugidonic.stockmarketapp.presentation.company_info.state.CompanyInfoState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CompanyInfoViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val repository: StockRepository,
): ViewModel() {

    var state by mutableStateOf(CompanyInfoState())

    init {
        viewModelScope.launch {
            val symbol = savedStateHandle.get<String>("symbol") ?: return@launch
            state = state.copy(isLoading = true)
            val companyInfoResult = async { repository.getCompanyInfo(symbol) }
            val intradayInfoResult = async { repository.getIntradayInfo(symbol) }

            when (val result = companyInfoResult.await()) {
                is Resource.Success -> {
                    state = state.copy(
                        company = result.data,
                        isLoading = false,
                        errorMessage = null,
                    )
                }
                is Resource.Error -> {
                    state = state.copy(
                        isLoading = false,
                        errorMessage = result.message,
                        company = null,
                    )
                }
                else -> Unit
            }

            when (val result = intradayInfoResult.await()) {
                is Resource.Success -> {
                    state = state.copy(
                        stockInfos = result.data ?: emptyList(),
                        isLoading = false,
                        errorMessage = null,
                    )
                }
                is Resource.Error -> {
                    state = state.copy(
                        isLoading = false,
                        errorMessage = result.message,
                        company = null,
                    )
                }
                else -> Unit
            }
        }
    }

}