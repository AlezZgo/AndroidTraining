package com.example.datasavemodule

import org.junit.Assert.*
import org.junit.Test

class ModelTest{
    @Test
    fun test_start_with_saved_value(){
        val testDataSource = TestDataSource()
        val timeTicker = TestTimeTicker()
        val model = Model(testDataSource,timeTicker)
        val callBack = TestCallback()
        testDataSource.saveInt("",5)
        model.start(callBack)
        timeTicker.tick(1)
        val actual = callBack.text
        val expected = "6"
        assertEquals(expected, actual)
    }

    @Test
    fun test_stop_after_2_seconds(){
        val testDataSource = TestDataSource()
        val timeTicker = TestTimeTicker()
        val model = Model(testDataSource,timeTicker)
        val callBack = TestCallback()
        testDataSource.saveInt("",0)
        model.start(callBack)
        timeTicker.tick(2)

        val actual = callBack.text
        val expected = "2"
        assertEquals(expected, actual)

        model.stop()
        val savedCountActual = testDataSource.getInt("")
        val savedCountExpected = 2
        assertEquals(savedCountExpected,savedCountActual)


    }
}