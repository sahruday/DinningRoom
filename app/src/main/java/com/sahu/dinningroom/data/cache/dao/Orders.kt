package com.sahu.dinningroom.data.cache.dao

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Entity
data class Orders(
    @PrimaryKey val orderId: Int,
    val createdAt: String,
    val alertAt: String,
    val expireAt: String,
    val isAccepted: Boolean,
)

@Dao
interface OrdersDao {

//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun addData(table: Orders)
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun addData(tablesList: List<Orders>)

    @Query("SELECT * FROM Orders")
    fun getData(): Flow<List<OrderWithItem>>

    @Query("DELETE FROM Orders")
    suspend fun deleteAll()
}