package com.tut.moviemvvm.datasource.local.entity

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.tut.myfavoriterestaurant.local.FavouriteDao
import com.tut.myfavoriterestaurant.local.entity.FavoriteRestaurant

@Database(entities = [FavoriteRestaurant::class], version = 1, exportSchema = false)
abstract class FavoriteDatabase : RoomDatabase() {
    abstract val favouriteDao: FavouriteDao

    companion object {
        @Volatile
        private var INSTANCE: FavoriteDatabase? = null

        fun getInstance(context: Context): FavoriteDatabase {
            var instance = INSTANCE
            if (instance != null) return instance
            synchronized(this) {
                instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        FavoriteDatabase::class.java,
                        "favorites_db"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                    return INSTANCE!!
                } else {
                    return INSTANCE!!
                }
            }
        }
    }
}