package com.hugidonic.data.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [CompanyListingEntity::class],
    version = 1,
)
abstract class StockDatabase: RoomDatabase() {
    abstract val stockDao: StockDao
}