package com.newstore.favqs.application

import com.jakewharton.threetenabp.AndroidThreeTen
import com.newstore.favqs.BuildConfig
import com.newstore.network.NetworkUtil
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
import javax.inject.Inject

@HiltAndroidApp
class QuotesApplication : android.app.Application() {

    @Inject
    lateinit var networkUtil: NetworkUtil

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
        AndroidThreeTen.init(this)
        networkUtil.registerNetworkCallback()
    }

    companion object {
        const val TITLE = "FavQs"
    }
}