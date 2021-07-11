package com.sahu.dinningroom.data.remote

import com.sahu.dinningroom.appUtil.AppResult
import com.sahu.dinningroom.data.remote.api.Api
import com.sahu.dinningroom.dataHolders.AddOns
import com.sahu.dinningroom.dataHolders.Items
import com.sahu.dinningroom.dataHolders.MenuItem
import com.sahu.dinningroom.dataHolders.Order
import com.sahu.dinningroom.ext.getTimeZoneDate
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.random.Random

@Singleton
class RemoteService @Inject constructor(
    private val api: Api,
    //mappers
) {

    suspend fun searchForIngredients(searchString: String) : Flow<AppResult<List<MenuItem>>> = flow {
//        api.getIngredientSearch(searchString)
        emit(AppResult.success(arrayListOf()))
    }

    //As of now passing same received values.
    suspend fun updateOrderStatus(orderId: Int, updateStatus: Int) : Flow<AppResult<Pair<Int, Int>>> {
//        api.updateStatus(orderId, updateStatus)
        return flow {
            emit(AppResult.success(orderId to updateStatus))
        }
    }

    suspend fun prepareMockData(): Flow<AppResult<Order>> {
        return flow {
            val calendar = Calendar.getInstance()
            val createdAt: String = calendar.getTimeZoneDate()
            calendar.add(Calendar.MINUTE, 3)
            val alertAt = calendar.getTimeZoneDate()
            calendar.add(Calendar.MINUTE, 2)
            val expireAt = calendar.getTimeZoneDate()

            val mockOrder = Order(
                0,
                createdAt,
                alertAt,
                expireAt,
                0,
                getMockItems()
            )

            emit(AppResult.success(mockOrder))
        }
    }

    private fun getMockItems(): List<Items> {
        val itemCount = Random.nextInt(1, 5)
        return (1 .. itemCount).map {
            Items(it, "", Random.nextInt(1, 5), getMockAddOns())
        }
    }

    private fun getMockAddOns(): List<AddOns> {
        val addOnCount = Random.nextInt(1, 5)
        return (1 .. addOnCount).map {
            AddOns(it+8, "", Random.nextInt(1, 5))
        }
    }

}