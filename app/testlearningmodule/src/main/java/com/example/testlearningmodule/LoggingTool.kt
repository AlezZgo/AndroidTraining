package com.example.testlearningmodule

import android.util.Log

class LoggingTool : Logging {
    override fun log(mes: String) {
        Log.d(javaClass.canonicalName,mes)
    }
}

interface Logging {

    fun log(mes: String)

}
