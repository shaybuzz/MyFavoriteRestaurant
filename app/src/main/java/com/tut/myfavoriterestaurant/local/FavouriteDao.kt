package com.tut.myfavoriterestaurant.local

import androidx.room.*
import com.tut.myfavoriterestaurant.local.entity.FavouriteRestaurant

@Dao
interface FavouriteDao {
    @Query("SELECT COUNT(*) FROM favourites WHERE id = :id")
    suspend fun getFavoriteCount(id: Int): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addToFavourite(favouriteRestaurant: FavouriteRestaurant)

    @Delete
    suspend fun removeFromFavorite(favouriteRestaurant: FavouriteRestaurant)
}

suspend fun FavouriteDao.isFavoriteRestaurant(id: Int): Boolean {
    return getFavoriteCount(id) != 0
}