package com.sahu.dinningroom.data.remote.api

import com.sahu.dinningroom.appUtil.MyResult
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET

interface Api {

    @GET("data")
    suspend fun getData(): Flow<MyResult<List<Int>>>
}