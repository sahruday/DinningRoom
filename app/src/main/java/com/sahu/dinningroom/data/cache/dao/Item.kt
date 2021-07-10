package com.sahu.dinningroom.data.cache.dao

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index

@Entity(
    primaryKeys = ["itemId", "orderId"],
    foreignKeys = [
        ForeignKey(
            entity = Orders::class, parentColumns = arrayOf("orderId"),
            childColumns = arrayOf("orderId"),
            onDelete = ForeignKey.CASCADE
        )],
    indices = [
        Index(value = ["itemId"]),
        Index(value = ["orderId"])
    ]
)
data class Item(
    val itemId: Int,
    val orderId: Int,
    val title: String,
    val quantity: Int
)