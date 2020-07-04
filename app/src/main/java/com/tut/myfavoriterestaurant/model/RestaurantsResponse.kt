package com.tut.myfavoriterestaurant.model


import com.google.gson.annotations.SerializedName

data class RestaurantsResponse(
    val restaurants: List<Restaurant>
)