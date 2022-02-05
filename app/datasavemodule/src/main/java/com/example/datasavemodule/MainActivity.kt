package com.example.datasavemodule

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MyViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = (application as MyApp).viewModel
        val textView = findViewById<TextView>(R.id.tv)
        val observable = TextObservable()
        observable.observe(object : TextCallBack {
            override fun updateText(str: String) = runOnUiThread {
                textView.text = str
            }

        })

        viewModel.init(observable)

    }

    override fun onDestroy() {
        viewModel.clear()
        super.onDestroy()
    }
}