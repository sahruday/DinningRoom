package com.sahu.dinningroom.dataHolders

import com.sahu.dinningroom.data.cache.dao.tables.Menu

data class MenuItem(
    val menu: Menu,
    val addOnList: List<Menu>
)