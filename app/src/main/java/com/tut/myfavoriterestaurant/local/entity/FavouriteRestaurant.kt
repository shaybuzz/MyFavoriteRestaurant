package com.tut.myfavoriterestaurant.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favourites")
data class FavouriteRestaurant(
    @PrimaryKey
    var id: Int
)