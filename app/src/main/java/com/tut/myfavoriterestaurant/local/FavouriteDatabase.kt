package com.tut.moviemvvm.datasource.local.entity

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.tut.myfavoriterestaurant.local.FavouriteDao
import com.tut.myfavoriterestaurant.local.entity.FavouriteRestaurant

@Database(entities = [FavouriteRestaurant::class], version = 1, exportSchema = false)
abstract class FavouriteDatabase : RoomDatabase() {
    abstract val favouriteDao: FavouriteDao

    companion object {
        @Volatile
        private var INSTANCE: FavouriteDatabase? = null

        fun getInstance(context: Context): FavouriteDatabase {
            var instance = INSTANCE
            if (instance != null) return instance
            synchronized(this) {
                instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        FavouriteDatabase::class.java,
                        "favourites_db"
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