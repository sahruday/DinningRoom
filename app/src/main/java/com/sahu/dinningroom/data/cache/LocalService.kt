package com.sahu.dinningroom.data.cache

import com.sahu.dinningroom.data.cache.dao.OrdersDao
import com.sahu.dinningroom.data.cache.dao.OrderWithItem
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalService @Inject constructor(
    private val ordersDao: OrdersDao,
    //use mapper(s)
) {

    fun getData(): Flow<List<OrderWithItem>> = ordersDao.getData()

    suspend fun deleteAll() = ordersDao.deleteAll()

//    suspend fun insertData(list: List<Int>) =
//        ordersDao.addData(list.map { Table(it)) })

}