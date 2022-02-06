package com.example.datasavemodule

import java.util.*

class Model(private val dataSource: DataSource) {

    private var timer: Timer? = null

    private val timerTask
        get() = object : TimerTask() {
            override fun run() {
                count++
                textCallBack?.updateText(count.toString())
            }

        }

    private var textCallBack: TextCallBack? = null
    private var count = -1

    fun start(textCallBack: TextCallBack) {
        this.textCallBack = textCallBack
        if (count < 0)
            count = dataSource.getInt(COUNTER_KEY)
        timer = Timer()
        timer?.scheduleAtFixedRate(timerTask, 0, 1000)
    }

    fun stop() {
        dataSource.saveInt(COUNTER_KEY,count)
        timer?.cancel()
        timer = null
    }

    companion object{
        private const val COUNTER_KEY = "counterKey"
    }

}

