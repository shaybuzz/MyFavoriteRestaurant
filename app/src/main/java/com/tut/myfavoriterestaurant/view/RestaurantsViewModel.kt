package com.tut.myfavoriterestaurant.view

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.tut.moviemvvm.datasource.local.entity.FavouriteDatabase
import com.tut.myfavoriterestaurant.repository.RestaurantsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RestaurantsViewModel(application: Application) : AndroidViewModel(application) {

    private val db = FavouriteDatabase.getInstance(application)
    private val repo = RestaurantsRepository(db.favouriteDao)
    val restaurants = repo.restaurants

    init {
        fetchData()
    }

    fun updateFavourite(id: Int, favourite: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.updateFavourite(id, favourite)
        }
    }

    private fun fetchData(){
        viewModelScope.launch(Dispatchers.IO) {
            repo.fetchData()
        }
    }
}

class RestaurantsViewModelFactory(private val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RestaurantsViewModel::class.java)) {
            return RestaurantsViewModel(application) as T
        }
        throw IllegalArgumentException()
    }

}

