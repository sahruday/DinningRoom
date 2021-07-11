package com.sahu.dinningroom.data.cache.dao.tables

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Menu(
    @PrimaryKey val id: Int,
    val Name: String,
    val category: Int, //0 For only add-on which not sold separately.
    val availableQuantity: Int
)