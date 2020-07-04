package com.tut.myfavoriterestaurant.view

import android.app.Application
import timber.log.Timber

class FavRestApp:Application() {
    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
}