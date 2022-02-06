package com.example.datasavemodule

import java.util.*

class Model(private val dataSource: DataSource,
            private val timeTicker: TimeTicker) {

    private val tickerCallBack
        get() = object : TimeTicker.Callback {
            override fun tick() {
                count++
                callBack?.updateText(count.toString())
            }

        }

    private var callBack: TextCallBack? = null
    private var count = -1

    fun start(textCallBack: TextCallBack) {
        callBack = textCallBack
        if (count < 0)
            count = dataSource.getInt(COUNTER_KEY)
        timeTicker.start(tickerCallBack)
    }

    fun stop() {
        dataSource.saveInt(COUNTER_KEY,count)
        timeTicker.stop()
    }

    companion object{
        private const val COUNTER_KEY = "counterKey"
    }

}

