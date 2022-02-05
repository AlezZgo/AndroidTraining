package com.example.datasavemodule

import java.util.*

class Model {

    private var timer: Timer? = null

    private val timerTask = object : TimerTask(){
        override fun run() {
            count++
            textCallBack?.updateText(count.toString())
        }

    }

    private var textCallBack: TextCallBack? = null
    private var count = 0

    fun start(textCallBack: TextCallBack){
        this.textCallBack = textCallBack
        if(timer == null){
            timer = Timer()
            timer?.scheduleAtFixedRate(timerTask,1000,1000)
        }
    }

}

