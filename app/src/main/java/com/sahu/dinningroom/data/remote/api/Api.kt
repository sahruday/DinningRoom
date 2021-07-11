package com.sahu.dinningroom.data.remote.api

import android.view.MenuItem
import com.sahu.dinningroom.appUtil.AppResult
import com.sahu.dinningroom.dataHolders.Order
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface Api {

    @GET("data")
    suspend fun getData(): Flow<AppResult<List<Int>>>

    @GET("Search")
    suspend fun getIngredientSearch(@Query("keyword") searchText: String): Flow<AppResult<List<MenuItem>>>

    @GET("ingredient_by_category")
    suspend fun getCategoryIngredients(@Query("category_id") category: Int): Flow<AppResult<List<MenuItem>>>

    @POST("status_update")
    suspend fun updateStatus(
        @Query("order_id") orderId: Int,
        @Query("status") updateStatus: Int): Flow<AppResult<Order>>
}