package com.example.eldarwalletchallenge.data.network

import com.example.eldarwalletchallenge.data.network.model.RapidApiBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @Headers(
        "content-type: application/json",
        "User-ID: Chave",
        "API-Key: faTHiaKntQuywwqDUu7JLyg0aT6b8O1kBW6ervxJu7j23mkj"
    )
    @POST("qr-code")
    fun generateQrCode(
        @Body content: RapidApiBody
    ): Call<ResponseBody>

}