package com.example.eldarwalletchallenge.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.eldarwalletchallenge.R
import com.example.eldarwalletchallenge.databinding.FragmentLoginBinding
import com.example.eldarwalletchallenge.ui.viewModels.MainViewModel

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)

        binding.loginButton.setOnClickListener {
            viewModel.login(
                binding.loginUserInput.text.toString(),
                binding.loginPasswordInput.text.toString()
            )
        }

        attachObservers()
        return binding.root
    }

    private fun attachObservers() {
        viewModel.loginSuccess.observe(viewLifecycleOwner) {
            if (it) {
                findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
            } else {
                //ERROR
            }
        }
    }


}