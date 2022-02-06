package com.example.datasavemodule

class TestCallback : TextCallBack {

    var text = ""

    override fun updateText(str: String) {
        text = str
    }
}