package com.sahu.dinningroom.data.cache.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sahu.dinningroom.data.cache.dao.tables.AddOn
import com.sahu.dinningroom.data.cache.dao.tables.Item
import com.sahu.dinningroom.data.cache.dao.tables.Orders
import com.sahu.dinningroom.dataHolders.AddOns
import com.sahu.dinningroom.dataHolders.Items
import com.sahu.dinningroom.dataHolders.Order
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


@Dao
interface OrdersDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addOrder(table: Orders): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addOrders(tablesList: List<Orders>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addOrderItem(table: Item)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addOrderItems(tablesList: List<Item>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addItemAddOn(table: AddOn)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addItemAddOn(tablesList: List<AddOn>)

    fun getOrdersData() = getOrderDetailsFlow().map {
        it.map { order ->
            Order(order.orderId, order.createdAt, order.alertAt, order.expireAt, order.orderStatus,
                getOrderItems(order.orderId).map { item ->
                    Items(item.itemId, item.title, item.quantity,
                        getItemAddOns(order.orderId, item.itemId).map { addOn ->
                            AddOns(addOn.addOnId, addOn.title, addOn.quantity)
                        }
                    )
                }
            )
        }
    }

    @Query("SELECT * FROM Orders WHERE orderStatus < 2")
    fun getOrderDetailsFlow(): Flow<List<Orders>>

    @Query("SELECT * FROM Item WHERE orderId = :orderId")
    fun getOrderItems(orderId: Int): List<Item>

    @Query("SELECT * FROM AddOn WHERE orderId = :orderId AND itemId = :itemId")
    fun getItemAddOns(orderId: Int, itemId: Int): List<AddOn>

    @Query("DELETE FROM Orders")
    suspend fun deleteAllOrders()

    @Query("DELETE FROM Item")
    suspend fun deleteAllOrderedItems()

    @Query("DELETE FROM AddOn")
    suspend fun deleteAllOrderedAddOns()

    @Query("UPDATE Orders SET orderStatus = :updatedStatus WHERE orderId = :orderId ")
    suspend fun updateOrderStatus(orderId: Int, updatedStatus: Int)
}