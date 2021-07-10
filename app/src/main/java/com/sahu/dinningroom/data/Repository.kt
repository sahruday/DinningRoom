package com.sahu.dinningroom.data

import com.sahu.dinningroom.appUtil.Callback
import com.sahu.dinningroom.appUtil.MyResult
import com.sahu.dinningroom.data.cache.LocalService
import com.sahu.dinningroom.data.cache.dao.OrderWithItem
import com.sahu.dinningroom.data.remote.RemoteService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Repository @Inject constructor(
    private val remote: RemoteService,
    private val local: LocalService,
) {

    fun getData(): Flow<List<OrderWithItem>> = local.getData()

    suspend fun getDataFromApi(): Flow<Callback> = flow {
        val result: MyResult<List<Int>> = remote.getData().first()
        result.handle(
            onSuccess = {
//                local.insertData(it)
                emit(Callback.Success)
            },
            onFailure = {
                emit(Callback.Error(it.message))
            }
        )

    }

}