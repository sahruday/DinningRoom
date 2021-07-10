package com.sahu.dinningroom.data.cache.dao

import androidx.room.Embedded
import androidx.room.Relation

data class ItemWithAddOn(
    @Embedded val items: Item,
    @Relation(
        parentColumn = "itemId",
        entityColumn = "itemId",
        entity = AddOn::class
    )
    val addOns: List<AddOn>
)