package com.sahu.dinningroom.data.cache.dao

import androidx.room.Embedded
import androidx.room.Relation

data class OrderWithItem(
    @Embedded val order: Orders,
    @Relation(
        parentColumn = "orderId",
        entityColumn = "orderId",
        entity = Item::class
    )
    val itemsWithAddOns: List<ItemWithAddOn>
)
