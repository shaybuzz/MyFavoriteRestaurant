package com.tut.myfavoriterestaurant.view

import android.app.Application
import androidx.lifecycle.*
import com.tut.moviemvvm.datasource.local.entity.FavoriteDatabase
import com.tut.myfavoriterestaurant.repository.RestaurantsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber

class RestaurantsViewModel(application: Application) : AndroidViewModel(application) {

    private val db = FavoriteDatabase.getInstance(application)
    private val repo = RestaurantsRepository(db.favouriteDao)
    val restaurants = repo.restaurants
    private val _loading: MutableLiveData<Boolean> = MutableLiveData(false)
    val loading: LiveData<Boolean> = _loading

    init {
        fetchData()
    }

    fun updateFavourite(id: Int, favourite: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.updateFavourite(id, favourite)
        }
    }

    private fun fetchData() {
        viewModelScope.launch(Dispatchers.IO) {
            _loading.postValue(true)
            try {
                repo.fetchData()
                _loading.postValue(false)
            } catch (throwable: Throwable) {
                //TODO handle error...
                Timber.e("error ${throwable.message}")
                _loading.postValue(false)
            }
        }
    }
}

class RestaurantsViewModelFactory(private val application: Application) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RestaurantsViewModel::class.java)) {
            return RestaurantsViewModel(application) as T
        }
        throw IllegalArgumentException()
    }

}

