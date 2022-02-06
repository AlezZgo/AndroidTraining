package com.example.testlearningmodule

class Doer(private val logger: Logging) {

    private var mainThingDone = false

    fun doMain(){
        if(!mainThingDone){
            logger.log("main thing done")
            mainThingDone = true
        }
    }

}