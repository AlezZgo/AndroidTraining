package com.example.datasavemodule

import javax.security.auth.callback.Callback

interface TimeTicker {
    fun start(callback: Callback, period: Long = 1000)

    fun stop()

    interface Callback {

        fun tick()
    }


}