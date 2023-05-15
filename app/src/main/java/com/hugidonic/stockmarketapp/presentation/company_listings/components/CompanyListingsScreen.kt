package com.hugidonic.stockmarketapp.presentation.company_listings.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hugidonic.stockmarketapp.presentation.company_listings.state.CompanyListingsEvent
import com.hugidonic.stockmarketapp.presentation.company_listings.viewmodel.CompanyListingsViewModel
import com.hugidonic.stockmarketapp.presentation.destinations.CompanyInfoScreenDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@OptIn(ExperimentalMaterialApi::class)
@Composable
@RootNavGraph(start = true)
@Destination
fun CompanyListingsScreen(
    navigator: DestinationsNavigator,
    viewModel: CompanyListingsViewModel = hiltViewModel()
) {

    val state = viewModel.state

    val swipeRefreshState = rememberPullRefreshState(
        refreshing = state.isRefreshing,
        onRefresh = {
            viewModel.onEvent(CompanyListingsEvent.Refresh)
        }
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .pullRefresh(swipeRefreshState)
    ) {
        OutlinedTextField(
            value = state.searchQuery,
            onValueChange = {
                viewModel.onEvent(
                    CompanyListingsEvent.OnSearchQueryChange(it)
                )
            },
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            placeholder = {
                Text(text = "Search...")
            },
            maxLines = 1,
            singleLine = true,
        )
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(state.companies.size) {i ->
                val company = state.companies[i]
                CompanyListingsItem(
                    company = company,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            navigator.navigate(CompanyInfoScreenDestination(company.symbol))
                        }
                        .padding(16.dp)
                )

                if (i < state.companies.size) {
                    Divider(modifier = Modifier.padding(horizontal = 16.dp))
                }
            }
        }
    }
}
