package com.sahu.dinningroom.data.cache.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sahu.dinningroom.data.cache.dao.tables.AddOnIds
import com.sahu.dinningroom.data.cache.dao.tables.Menu
import com.sahu.dinningroom.dataHolders.MenuItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

@Dao
interface MenuDao{

    fun getMenuItems(): Flow<List<MenuItem>> = getMenuWithCategoryNot().map {
        it.map {  menu ->
            MenuItem(menu, getMenuWithItem(getAddOnList(menu.id)))
        }
    }

    @Query("SELECT addOnId FROM AddOnIds WHERE id = :id")
    fun getAddOnList(id: Int): List<Int>

    @Query("SELECT * FROM Menu WHERE id IN (:ids)")
    fun getMenuWithItem(ids: List<Int>) : List<Menu>

    @Query("SELECT * FROM Menu WHERE id = :id")
    suspend fun getMenuWithId(id: Int): Menu?

    @Query("SELECT * FROM Menu WHERE category != :category")
    fun getMenuWithCategoryNot(category: Int = 0): Flow<List<Menu>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMenuItems(list: List<Menu>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAddOnIds(list: List<AddOnIds>)
}