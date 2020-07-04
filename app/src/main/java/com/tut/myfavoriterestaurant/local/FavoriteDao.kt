package com.tut.myfavoriterestaurant.local

import androidx.room.*
import com.tut.myfavoriterestaurant.local.entity.FavoriteRestaurant

@Dao
interface FavouriteDao {
    @Query("SELECT COUNT(*) FROM favorites WHERE id = :id")
    suspend fun getFavoriteCount(id: Int): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addToFavorite(favouriteRestaurant: FavoriteRestaurant)

    @Delete
    suspend fun removeFromFavorite(favouriteRestaurant: FavoriteRestaurant)
}

suspend fun FavouriteDao.isFavoriteRestaurant(id: Int): Boolean {
    return getFavoriteCount(id) != 0
}