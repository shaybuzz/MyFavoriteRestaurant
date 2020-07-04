package com.tut.myfavoriterestaurant.network

import com.tut.myfavoriterestaurant.model.RestaurantsResponse
import retrofit2.http.GET


interface RestaurantService {

    @GET("gilgoldzweig/SampleTest/db")
    suspend fun getRestaurants(): RestaurantsResponse
}