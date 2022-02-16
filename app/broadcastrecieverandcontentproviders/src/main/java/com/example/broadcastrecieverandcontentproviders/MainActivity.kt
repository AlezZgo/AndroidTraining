package com.example.broadcastrecieverandcontentproviders

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.broadcastrecieverandcontentproviders.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private lateinit var receiver: BroadcastReceiver
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        receiver = ConnectionReceiver()
    }

    override fun onStart() {
        super.onStart()
        registerReceiver(receiver, IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED))
    }

    override fun onStop() {
        unregisterReceiver(receiver)
        super.onStop()
    }
}

class ConnectionReceiver : BroadcastReceiver(){
    override fun onReceive(context: Context?, intent: Intent?) {
        Toast.makeText(context,"airplane mode changed}",Toast.LENGTH_LONG).show()
    }

}