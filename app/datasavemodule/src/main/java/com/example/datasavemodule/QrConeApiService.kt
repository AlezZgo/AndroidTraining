package com.example.datasavemodule

import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface QrConeApiService {

    @POST("qrgen")
    suspend fun generateQrCode(
        @Body requestBody: RequestBody
    ): Response<ResponseBody>

    @Multipart
    @POST("qrgen")
    suspend fun uploadEmployeeData(@PartMap map: HashMap<String?, RequestBody?>): Response<ResponseBody>

}