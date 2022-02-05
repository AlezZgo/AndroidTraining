package com.example.datasavemodule

import java.util.*

class MyViewModel(private val textObservable: TextObservable) {

    private val model = Model(object : TextCallBack{
        override fun updateText(str: String) {
            textObservable.postValue(str)
        }

    })

    fun init() {
        model.start()
    }

}

class TextObservable {

    private lateinit var callBack: TextCallBack

    fun observe(cb: TextCallBack){
        this.callBack = cb
    }

    fun postValue(str: String) {
        callBack.updateText(str)
    }

}

interface TextCallBack {
    fun updateText(str: String)
}
