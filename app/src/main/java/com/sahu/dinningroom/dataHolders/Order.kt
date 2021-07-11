package com.sahu.dinningroom.dataHolders

const val PENDING = 0   //Initial state when order is received
const val EXPIRED = 1   //State where order has expired acceptance time
const val ACCEPTED = 2  //Accepted State
const val REJECTED = 3  //Rejected state by the merchant

data class Order(
    val id: Int,
    val createdAt: String,
    val alertAt: String,
    val expireAt: String,
    val status: Int,
    val items: List<Items>,
){
    val orderNumber = "#$id"
}

data class Items(
    val id: Int,
    val title: String,
    val quantity: Int,
    val addOns: List<AddOns>
)

data class AddOns(
    val id: Int,
    val title: String,
    val quantity: Int
)