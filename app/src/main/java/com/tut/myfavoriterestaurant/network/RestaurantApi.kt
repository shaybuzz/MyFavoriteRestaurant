package com.tut.myfavoriterestaurant.network

import com.tut.myfavoriterestaurant.model.Restaurant

class RestaurantApi(private val restaurantService: RestaurantService) {

    suspend fun fetchRestaurants(): List<Restaurant> {
        return restaurantService.getRestaurants().restaurants
    }
}