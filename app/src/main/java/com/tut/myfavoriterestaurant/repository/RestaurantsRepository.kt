package com.tut.myfavoriterestaurant.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.tut.myfavoriterestaurant.local.FavouriteDao
import com.tut.myfavoriterestaurant.local.entity.FavoriteRestaurant
import com.tut.myfavoriterestaurant.local.isFavoriteRestaurant
import com.tut.myfavoriterestaurant.model.Restaurant
import com.tut.myfavoriterestaurant.network.Network


class RestaurantsRepository(private val favouriteDao: FavouriteDao) {

    private val api = Network().movieApi
    private val _restaurants: MutableLiveData<List<Restaurant>> = MutableLiveData()
    val restaurants: LiveData<List<Restaurant>> = _restaurants

    suspend fun fetchData() {
        val list = api.fetchRestaurants()
        list.forEach {
            //update the local favorite data from database
            it.isFavourite = favouriteDao.isFavoriteRestaurant(it.id)
        }
        _restaurants.postValue(list)
    }

    suspend fun updateFavourite(id: Int, isFavourite: Boolean) {
        //update local database
        if (isFavourite) {
            favouriteDao.addToFavorite(FavoriteRestaurant(id))
        } else {
            favouriteDao.removeFromFavorite(FavoriteRestaurant(id))
        }
    }
}