package com.tut.myfavoriterestaurant.model


data class Restaurant(
    val coverImageUrl: String,
    val id: Int,
    val minimumOrder: Int,
    val name: String,
    val open: Boolean
) {
    var isFavourite: Boolean = false
}
