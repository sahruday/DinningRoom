package com.sahu.dinningroom.di

import android.content.Context
import androidx.room.Room
import com.sahu.dinningroom.appUtil.AppDatabase
import com.sahu.dinningroom.appUtil.DB_NAME
import com.sahu.dinningroom.data.cache.dao.OrdersDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DBModule {

    @Singleton
    @Provides
    fun getDB(@ApplicationContext context: Context): AppDatabase = Room.databaseBuilder(context, AppDatabase::class.java, DB_NAME)
        .build()

    @Singleton
    @Provides
    fun getOrderDao(appDatabase: AppDatabase): OrdersDao = appDatabase.orderDao()
}