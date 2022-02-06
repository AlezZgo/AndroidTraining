package com.example.datasavemodule

import org.junit.Assert.assertEquals
import org.junit.Test

class StartWithSavedValueTest {

    @Test
    fun test_start_with_saved_value(){
        val testDataSource = TestDataSource()
        val model = Model(testDataSource)
        val callBack = TestCallback()
        testDataSource.saveInt("",5)
        model.start(callBack)
        Thread.sleep(10)
        val actual = callBack.text
        val expected = "5"
        assertEquals(expected, actual)
    }

    @Test
    fun test_stop_after_2_seconds(){
        val testDataSource = TestDataSource()
        val model = Model(testDataSource)
        val callback = TestCallback()
        testDataSource.saveInt("",5)
        model.start(callback)
        Thread.sleep(2010)
        val savedCountActual = testDataSource.getInt("")
        val savedCountExpected = 2
        assertEquals(savedCountExpected,savedCountActual)


    }

}