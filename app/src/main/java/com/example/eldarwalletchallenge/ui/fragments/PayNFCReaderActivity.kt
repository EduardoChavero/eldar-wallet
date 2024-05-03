package com.example.eldarwalletchallenge.ui.fragments

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.eldarwalletchallenge.databinding.FragmentPayNfcReaderBinding
import com.example.eldarwalletchallenge.ui.reusables.NFCUtil
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PayNFCReaderActivity : AppCompatActivity() {

    private lateinit var binding: FragmentPayNfcReaderBinding

    private var nfcUtil: NFCUtil? = null

    override fun onCreate(
        savedInstanceState: Bundle?
    ) {
        super.onCreate(savedInstanceState)
        binding = FragmentPayNfcReaderBinding.inflate(layoutInflater)
        setContentView(binding.root)

        nfcUtil = NFCUtil(applicationContext, this)
    }

    public override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        val nfcResult = nfcUtil!!.readTag(intent)
        finish()
    }

    public override fun onResume() {
        super.onResume()
        nfcUtil!!.enableForegroundDispatch()
    }

    public override fun onPause() {
        super.onPause()
        nfcUtil!!.disableForegroundDispatch()
    }
}