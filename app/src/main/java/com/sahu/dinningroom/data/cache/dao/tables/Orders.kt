package com.sahu.dinningroom.data.cache.dao.tables

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Orders(
    @PrimaryKey(autoGenerate = true) val orderId: Int = 100,
    val createdAt: String,
    val alertAt: String,
    val expireAt: String,
    val orderStatus: Int,
)