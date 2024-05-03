package com.example.eldarwalletchallenge.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.eldarwalletchallenge.databinding.FragmentQrViewBinding
import com.example.eldarwalletchallenge.ui.viewModels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class QRViewFragment : Fragment() {

    private lateinit var binding: FragmentQrViewBinding
    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentQrViewBinding.inflate(inflater, container, false)

        binding.qrViewContainer.setImageBitmap(viewModel.getQrBitmap())
        binding.qrViewButton.setOnClickListener {
            findNavController().popBackStack()
        }

        return binding.root
    }

}