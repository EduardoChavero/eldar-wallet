package com.example.eldarwalletchallenge.ui.reusables

import android.os.Build
import androidx.annotation.RequiresApi
import java.util.Base64
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec


object EncryptUtil {

    private const val ALGORITHM = "Blowfish"
    private const val MODE = "Blowfish/CBC/PKCS5Padding"
    private const val IV = "abcdefgh"
    private const val KEY = "SECRETKEY"

    @RequiresApi(Build.VERSION_CODES.O)
    fun encrypt(value: String): String {
        val secretKeySpec = SecretKeySpec(KEY.toByteArray(), ALGORITHM)
        val cipher = Cipher.getInstance(MODE)
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, IvParameterSpec(IV.toByteArray()))
        val values = cipher.doFinal(value.toByteArray())
        return Base64.getEncoder().encodeToString(values)

    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun decrypt(value: String): String {
        val values = Base64.getDecoder().decode(value)
        val secretKeySpec = SecretKeySpec(KEY.toByteArray(), ALGORITHM)
        val cipher = Cipher.getInstance(MODE)
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, IvParameterSpec(IV.toByteArray()))
        return String(cipher.doFinal(values))
    }
}