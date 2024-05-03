package com.example.eldarwalletchallenge.domain.useCases

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.example.eldarwalletchallenge.data.network.RapidApiService
import javax.inject.Inject

class GenerateQrUseCase @Inject constructor(
    private val rapidApiService: RapidApiService
) {

    suspend operator fun invoke(userName: String): Bitmap? {
        return try {
            val result = rapidApiService.generateQRCode(userName)
            val inputStream = result?.byteStream()
            BitmapFactory.decodeStream(inputStream)
        } catch (e: Exception) {
            null
        }
    }
}