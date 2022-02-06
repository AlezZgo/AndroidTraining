package com.example.datasavemodule

class TestDataSource : DataSource {
    private var int : Int = Int.MIN_VALUE
    override fun saveInt(key: String, value: Int) {
        int = value
     }

    override fun getInt(key: String) = int
}