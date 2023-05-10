package com.appsolute.pokedex.application

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

/**
 * Created by Toan (Alex) Duong.
 * This project Pokedex belongs to Appsolute.
 * Do Not Copy
 * Please Contact braveheart3208@gmail.com for more information
 */
@HiltAndroidApp
class PokedexApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        //Timber
        Timber.plant(Timber.DebugTree())
    }
}