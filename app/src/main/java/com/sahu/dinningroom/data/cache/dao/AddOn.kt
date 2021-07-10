package com.sahu.dinningroom.data.cache.dao

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index

@Entity(
    primaryKeys = ["addOnId", "itemId"],
    foreignKeys = [
        ForeignKey(
            entity = Item::class,
            parentColumns = arrayOf("itemId", "orderId"),
            childColumns = arrayOf("itemId", "orderId"),
            onDelete = ForeignKey.CASCADE
        )],
//    indices = [
//        Index(value = ["itemId"]),
//        Index(value = ["addOnId"])
//    ]
)
data class AddOn(
    val addOnId: Int,
    val itemId: Int,
    val orderId: Int,
    val title: String,
    val quantity: Int
)