package com.example.eldarwalletchallenge.data.network

import android.util.Log
import com.example.eldarwalletchallenge.data.network.model.RapidApiBody
import com.google.gson.GsonBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.ResponseBody
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject


class RapidApiService @Inject constructor() {

    suspend fun generateQRCode(content: String): ResponseBody? {
        return withContext(Dispatchers.IO) {
            try {

                val gson = GsonBuilder()
                    .setLenient()
                    .create()

                val retrofit = Retrofit.Builder()
                    .baseUrl("https://neutrinoapi.net/")
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build()

                val apiService = retrofit.create(ApiService::class.java)

                val call = apiService.generateQrCode(
                    RapidApiBody(content)
                )

                val response = call.execute()
                if (response.isSuccessful) {
                    Log.d("Service", response.body().toString())
                    response.body()
                } else {
                    Log.d("Service", response.message().toString())
                    null
                }
            } catch (e: Exception) {
                Log.d("Service Error", e.message.toString())
                null
            }
        }
    }

}