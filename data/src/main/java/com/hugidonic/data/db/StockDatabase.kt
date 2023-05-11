package com.hugidonic.data.db

import androidx.room.RoomDatabase

abstract class StockDatabase: RoomDatabase() {
    abstract val stockDao: StockDao
}