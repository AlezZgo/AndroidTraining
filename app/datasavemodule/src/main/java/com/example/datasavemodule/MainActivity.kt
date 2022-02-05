package com.example.datasavemodule

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textView = findViewById<TextView>(R.id.tv)
        val observable = TextObservable()
        observable.observe(object : TextCallBack {
            override fun updateText(str: String) {
                textView.text = str
            }

        })

        val viewModel = MyViewModel(observable)
        viewModel.init()

    }
}