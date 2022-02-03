package com.example.training

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.net.URL

class NetImage(
    private val url: String,
    private val callBack : ImageCallBack
) : Thread() {

    override fun run() {
        super.run()
        try{
            val connection = URL(url).openConnection()
            connection.doInput = true
            connection.connect()
            connection.getInputStream().use {
                callBack.success(BitmapFactory.decodeStream(it))
            }
        }catch (e: Exception){
            callBack.failed()
        }

    }

    interface ImageCallBack{
        fun success(bitmap: Bitmap)

        fun failed()
    }
}