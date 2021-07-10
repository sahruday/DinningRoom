package com.sahu.dinningroom.data.remote

import com.sahu.dinningroom.appUtil.MyResult
import com.sahu.dinningroom.data.remote.api.Api
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteService @Inject constructor(
    private val api: Api,
    //mappers
) {

    suspend fun getData() : Flow<MyResult<List<Int>>>{
//        return api.getData()
        return flow{
            delay(1500)
            emit(MyResult.success(arrayListOf(1,2,3,4,5,6,7,8,9)))
        }
//        return try {
//            val response: Flow<MyResult<List<Int>>> = api.getData()
//
//            if (response.isSuccessful) {
//                val list = response.body()
//                if(!list.isNullOrEmpty()) {
//                    Result.Success(list)
//                }else Result.Error(failureHandler.noData())
//            }
//            else {
//                Result.Error(failureHandler.serverError())
//            }
//        } catch (ex: Exception) {
//            Result.Error(failureHandler.handleException(ex))
//        }
    }

}