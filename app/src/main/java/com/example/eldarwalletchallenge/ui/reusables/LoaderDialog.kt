package com.example.eldarwalletchallenge.ui.reusables

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import com.example.eldarwalletchallenge.R
import com.example.eldarwalletchallenge.databinding.LoaderDialogBinding

class LoaderDialog: DialogFragment() {

    private lateinit var binding: LoaderDialogBinding

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        super.onCreateDialog(savedInstanceState)
        val builder: AlertDialog.Builder = AlertDialog.Builder(activity)
        val inflater: LayoutInflater = requireActivity().layoutInflater
        binding = LoaderDialogBinding.inflate(inflater)

        builder.setView(binding.root)
        return builder.create()
    }


    override fun onResume() {
        dialog?.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.MATCH_PARENT
        )
        dialog?.window?.setGravity(Gravity.CENTER)
        dialog?.window?.setBackgroundDrawableResource(R.color.loaderBackground)
        super.onResume()
    }

}