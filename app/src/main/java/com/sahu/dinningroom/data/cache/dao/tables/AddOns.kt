package com.sahu.dinningroom.data.cache.dao.tables

import androidx.room.Entity

@Entity(primaryKeys = ["id", "addOnId"])
data class AddOnIds(
    val id: Int,
    val addOnId: Int
)