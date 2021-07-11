package com.sahu.dinningroom.data

import com.sahu.dinningroom.appUtil.Callback
import com.sahu.dinningroom.data.cache.LocalService
import com.sahu.dinningroom.data.remote.RemoteService
import com.sahu.dinningroom.dataHolders.EXPIRED
import com.sahu.dinningroom.dataHolders.MenuItem
import com.sahu.dinningroom.dataHolders.Order
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Repository @Inject constructor(
    private val remote: RemoteService,
    private val local: LocalService,
) {

    fun getMenuData(): Flow<List<MenuItem>> = local.getMenuItems()

    fun getData(): Flow<List<Order>> = local.getData()

    suspend fun postMockOrder()  {
        val result = remote.prepareMockData().first()
        result.handle(
            onSuccess = { local.addOrderResponse(it) },
            onFailure = {}
        )
    }

    suspend fun updateOrderState(orderId: Int, updateStatus: Int) {
        if(updateStatus == EXPIRED) { //Expired status need not be posted to web
            local.updateOrderStatus(orderId, updateStatus)
        }else {
            val result = remote.updateOrderStatus(orderId, updateStatus).first()
            result.handle(
                onSuccess = { local.updateOrderStatus(it.first, it.second) },
                onFailure = {}
            )
        }
    }

    suspend fun searchForIngredients(searchString: String): Flow<com.sahu.dinningroom.appUtil.Callback> = flow {
        val result = remote.searchForIngredients(searchString).first()
        result.handle(
            onSuccess = {emit(Callback.Success)},
            onFailure = {emit(Callback.Error(it.message))}
        )
    }

    suspend fun clearOrders() = local.deleteAll()

}