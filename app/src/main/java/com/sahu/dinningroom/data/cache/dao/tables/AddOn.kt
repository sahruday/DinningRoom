package com.sahu.dinningroom.data.cache.dao.tables

import androidx.room.Entity

@Entity(primaryKeys = ["addOnId", "itemId", "orderId"])
data class AddOn(
    val addOnId: Int,
    val itemId: Int,
    val orderId: Int,
    val title: String,
    val quantity: Int
)