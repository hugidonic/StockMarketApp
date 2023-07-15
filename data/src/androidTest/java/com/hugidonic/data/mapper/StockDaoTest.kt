package com.hugidonic.data.mapper

import android.util.Log
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.google.common.truth.Truth.assertThat
import com.hugidonic.data.db.CompanyListingEntity
import com.hugidonic.data.db.StockDao
import com.hugidonic.data.db.StockDatabase
import com.hugidonic.domain.models.CompanyListingModel
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class StockDaoTest {

    private lateinit var stockDb: StockDatabase
    private lateinit var stockDao: StockDao

    @BeforeEach
    fun setUp() {
        stockDb = Room
            .inMemoryDatabaseBuilder(
                context = ApplicationProvider.getApplicationContext(),
                klass = StockDatabase::class.java
            )
            .allowMainThreadQueries()
            .build()
        stockDao = stockDb.stockDao
    }

    @AfterEach
    fun tearDown() {
        stockDb.close()
    }

    @Test
    @DisplayName("should Insert And Get Items From Db")
    fun shouldInsertAndGetFromDb(): Unit = runTest {
        val companyListing = CompanyListingEntity(
            id = 1,
            symbol = "AAA",
            name = "Tesla",
            exchange = "NYES"
        )
        val entityList = listOf(companyListing)
        stockDao.insertCompanyListings(entityList)

        val listFromDb = stockDao.searchCompanyListing(query = "")
        assertThat(listFromDb).contains(companyListing)
    }

    @Test
    @DisplayName("should clear everything in db")
    fun shouldClearEverythingInDb() = runTest {
        stockDao.clearCompanyListings()
        val listFromDb = stockDao.searchCompanyListing(query = "")
        assertThat(listFromDb)
            .isEmpty()
    }

    @Test
    @DisplayName("should search company listing by given name")
    fun test3() = runTest {
        val companyListing = CompanyListingEntity(
            id = 1,
            symbol = "AAA",
            name = "Tesla",
            exchange = "NYES"
        )
        val entityList = listOf(companyListing)
        stockDao.insertCompanyListings(entityList)

        val listFromDb = stockDao.searchCompanyListing(query = "tes")
        Log.d("test", "expected $listFromDb contains $companyListing ")
        assertThat(listFromDb).contains(companyListing)
    }
}