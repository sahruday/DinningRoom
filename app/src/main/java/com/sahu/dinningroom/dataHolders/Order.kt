package com.sahu.dinningroom.dataHolders

data class Order(
    val id: Int,
    val createdAt: String,
    val alertAt: String,
    val expireAt: String,
    val isAccepted: Boolean,
    val items: List<Items>,
){
    val orderNumber = "#$id"
}

data class Items(
    val id: String,
    val title: String,
    val addOns: List<AddOns>,
    val quantity: Int
)

data class AddOns(
    val id: String,
    val title: String,
    val quantity: Int
)