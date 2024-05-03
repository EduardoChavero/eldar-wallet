package com.example.eldarwalletchallenge.ui.reusables

import android.app.Activity
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.nfc.NfcAdapter
import android.provider.Settings
import android.widget.Toast

class NFCUtil constructor(context: Context?, activity: Activity?){

    private var mContext: Context? = null
    private var mActivity: Activity? = null
    private var nfcAdapter: NfcAdapter? = null
    private var pendingIntent: PendingIntent? = null
    private var nfcEnabled = false
    private var result: String? = null

   init {
        mActivity = activity
        mContext = context
        nfcAdapter = NfcAdapter.getDefaultAdapter(mActivity)
        nfcEnabled = nfcAdapter != null
        nfcEnabled = false
        val intent = Intent(mContext, mActivity!!.javaClass)
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
        pendingIntent = PendingIntent.getActivity(
            mContext,
            0,
            intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP),
            PendingIntent.FLAG_MUTABLE
        )
    }

    fun enableForegroundDispatch() {
        try {
            val nfcAdapterRefCopy = nfcAdapter
            if (nfcAdapterRefCopy != null) {
                if (!nfcAdapterRefCopy.isEnabled) showNFCSettings()
                nfcAdapterRefCopy.enableForegroundDispatch(mActivity, pendingIntent, null, null)
            }
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }

    fun disableForegroundDispatch() {
        if (nfcAdapter != null) nfcAdapter!!.disableForegroundDispatch(mActivity)
    }

    fun readTag(intent: Intent): String? {
        return try {
            handleIntent(intent)
            result
        } catch (ex: Exception) {
            ex.printStackTrace()
            "NFC Error"
        }
    }

    private fun handleIntent(intent: Intent) {
        mActivity!!.intent = intent
        resolveIntent(intent)
    }

    private fun showNFCSettings() {
        Toast.makeText(mContext, "Debe encender el sensor NFC", Toast.LENGTH_SHORT).show()
        val intent = Intent(Settings.ACTION_NFC_SETTINGS)
        mActivity!!.startActivity(intent)
    }

    private fun resolveIntent(intent: Intent) {
        try {
            val action = intent.action
            if (NfcAdapter.ACTION_TAG_DISCOVERED === action || NfcAdapter.ACTION_TECH_DISCOVERED === action || NfcAdapter.ACTION_NDEF_DISCOVERED === action) {
              result = "NFC READIED"
            }
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }
}