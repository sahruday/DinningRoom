package com.sahu.dinningroom.data.cache.dao.tables

import androidx.room.*

@Entity(primaryKeys = ["itemId", "orderId"])
data class Item(
    val itemId: Int,
    val orderId: Int,
    val title: String,
    val quantity: Int,
)