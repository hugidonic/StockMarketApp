package com.hugidonic.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "company_listing")
data class CompanyListingEntity(
    val symbol: String,
    val name: String,
    val exchange: String,
    @PrimaryKey val id: Int? = null,
)