package com.sahu.dinningroom.di

import android.content.Context
import com.sahu.dinningroom.appUtil.AppDatabase
import com.sahu.dinningroom.data.cache.dao.MenuDao
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
    fun getDB(@ApplicationContext context: Context): AppDatabase = AppDatabase.getInstance(context)

    @Singleton
    @Provides
    fun getOrderDao(appDatabase: AppDatabase): OrdersDao = appDatabase.orderDao()

    @Singleton
    @Provides
    fun getMenuDao(appDatabase: AppDatabase): MenuDao = appDatabase.menuDao()
}