package com.hugidonic.stockmarketapp.di

import android.app.Application
import androidx.room.Room
import com.hugidonic.data.db.StockDao
import com.hugidonic.data.db.StockDatabase
import com.hugidonic.data.remote.ApiFactory
import com.hugidonic.data.remote.StockApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    @Singleton
    fun provideStockApi(): StockApiService {
        return ApiFactory().stockApi
    }

    @Provides
    @Singleton
    fun provideDatabase(app: Application): StockDatabase {
        return Room.databaseBuilder(
            context=app,
            klass=StockDatabase::class.java,
            name="stock.db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideStockDao(db: StockDatabase): StockDao {
        return db.stockDao
    }
}