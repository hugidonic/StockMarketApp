package com.hugidonic.stockmarketapp.di

import com.hugidonic.data.csv.CSVParser
import com.hugidonic.data.csv.CompanyListingsParser
import com.hugidonic.data.csv.IntradayParser
import com.hugidonic.data.repository.StockRepositoryImpl
import com.hugidonic.domain.models.CompanyListingModel
import com.hugidonic.domain.models.IntradayInfoModel
import com.hugidonic.domain.repository.StockRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindCompanyListingParser(
        companyListingsParser: CompanyListingsParser
    ): CSVParser<CompanyListingModel>

    @Binds
    @Singleton
    abstract fun bindIntradayParser(
        intradayParser: IntradayParser
    ): CSVParser<IntradayInfoModel>

    @Binds
    @Singleton
    abstract fun bindStockRepository(
        stockRepositoryImpl: StockRepositoryImpl
    ): StockRepository
}