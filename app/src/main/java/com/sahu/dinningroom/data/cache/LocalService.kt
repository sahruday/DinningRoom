package com.sahu.dinningroom.data.cache

import android.util.Log.i
import com.sahu.dinningroom.data.cache.dao.*
import com.sahu.dinningroom.data.cache.dao.tables.*
import com.sahu.dinningroom.dataHolders.MenuItem
import com.sahu.dinningroom.dataHolders.Order
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalService @Inject constructor(
    private val ordersDao: OrdersDao,
    private val menuDao: MenuDao,
    //use mapper(s)
) {

    fun getMenuItems(): Flow<List<MenuItem>> = menuDao.getMenuItems()

    fun getData(): Flow<List<Order>> = ordersDao.getOrdersData()

    suspend fun deleteAll() {
        ordersDao.deleteAllOrderedAddOns()
        ordersDao.deleteAllOrderedItems()
        ordersDao.deleteAllOrders()
    }

    suspend fun updateOrderStatus(orderId: Int, updatedStatus: Int) =
        ordersDao.updateOrderStatus(orderId, updatedStatus)


    suspend fun addOrderResponse(order: Order){
        val preparedOrder = Orders(0, order.createdAt, order.alertAt, order.expireAt, 0)
        val orderId = ordersDao.addOrder(preparedOrder).toInt()
        i("Local Service", "OrderId: $orderId")
        val itemsList = arrayListOf<Item>()
        val addOnsList = arrayListOf<AddOn>()
        for(item in order.items){
            itemsList.add(
                Item(
                    item.id,
                    orderId,
                    menuDao.getMenuWithId(item.id)?.Name ?: "",
                    item.quantity,
                )
            )
            for(addOns in item.addOns){
                addOnsList.add(
                    AddOn(
                        addOns.id,
                        item.id,
                        orderId,
                        menuDao.getMenuWithId(addOns.id)?.Name ?: "",
                        addOns.quantity
                    )
                )
            }
        }

        ordersDao.addItemAddOn(addOnsList)
        ordersDao.addOrderItems(itemsList)
        ordersDao.addOrder(preparedOrder.copy(orderId = orderId))
    }

}