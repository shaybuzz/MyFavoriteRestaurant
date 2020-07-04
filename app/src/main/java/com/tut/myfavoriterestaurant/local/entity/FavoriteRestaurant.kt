package com.tut.myfavoriterestaurant.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorites")
data class FavoriteRestaurant(
    @PrimaryKey
    var id: Int
)