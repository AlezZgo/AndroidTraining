package com.example.datasavemodule

import java.util.*

class MyViewModel(private val model: Model) {

    private var textObservable: TextObservable? = null

    private val textCallBack = object : TextCallBack{
        override fun updateText(str: String) {
            textObservable?.postValue(str)
        }
    }


    fun init(textObservable: TextObservable?) {
        this.textObservable = textObservable
        model.start(textCallBack)
    }

    fun clear(){
        textObservable = null
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
