package com.example.datasavemodule

import android.app.Application

class MyApp : Application() {
    lateinit var viewModel: MyViewModel

    override fun onCreate() {
        super.onCreate()

        viewModel = MyViewModel(Model(CacheDataSource(this),TimerTicker()))
    }
}