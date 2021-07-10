package com.sahu.dinningroom.appUtil

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sahu.dinningroom.data.cache.dao.*


const val DB_NAME = "dinningroom.db"

@Database(
    entities = [Orders::class, Item::class, AddOn::class],
    version = 1,
    exportSchema = true
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun orderDao() : OrdersDao
}