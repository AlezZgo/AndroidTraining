package com.example.datasavemodule

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonParser
import kotlinx.coroutines.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val imageView = findViewById<ImageView>(R.id.imageView)
        val button = findViewById<Button>(R.id.button)

        val api = initApi()

        val getContent = registerForActivityResult(ActivityResultContracts.GetContent()) {

            imageView.setImageURI(it)
            val file = File(it.path!!)


            val description = Gson().toJson(QrCodeCloudRequest(false,  "Hello world"))

            //val requestBody = description.toRequestBody("application/json".toMediaTypeOrNull())

            val fields: HashMap<String?, RequestBody?> = HashMap()

            fields["file\"; filename=\"upload_file.txt\" "] =
                (file).asRequestBody("text/plain".toMediaTypeOrNull())

            CoroutineScope(Dispatchers.IO).launch {

                val response = api.uploadEmployeeData(fields)

                withContext(Dispatchers.Main) {
                    if (response.isSuccessful) {

                        val prettyJson = Gson().toJson(response)
                        Log.d("Pretty Printed JSON :", prettyJson)
                    }else{
                        Log.e("RETROFIT_ERROR", response.code().toString())
                }
            }



            }
        }

        button.setOnClickListener {
            getContent.launch("image/*")
        }

    }

    private fun initApi(): QrConeApiService {
        val interceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val client = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()

        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(baseUrl)
            .client(client)
            .build()

        return retrofit.create(QrConeApiService::class.java)
    }

    companion object {
        private const val baseUrl = "https://qrcone.herokuapp.com/"
    }


}