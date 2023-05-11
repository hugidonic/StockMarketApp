package com.hugidonic.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface StockDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCompanyListings(
        companyListingEntities: List<CompanyListingEntity>
    )

    @Query("DELETE FROM company_listing")
    suspend fun clearCompanyListings()

    @Query("""
        SELECT * 
        FROM company_listing
        WHERE LOWER(name) LIKE '%' || LOWER(:query) || '%' OR 
            UPPER(:query) == symbol
    """)
    suspend fun searchCompanyListing(query: String): List<CompanyListingEntity>
}