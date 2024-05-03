package com.example.eldarwalletchallenge.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.eldarwalletchallenge.R
import com.example.eldarwalletchallenge.databinding.FragmentLoginBinding
import com.example.eldarwalletchallenge.ui.reusables.EventObserver
import com.example.eldarwalletchallenge.ui.reusables.LoaderDialog
import com.example.eldarwalletchallenge.ui.viewModels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private val viewModel: MainViewModel by activityViewModels()

    private val loaderDialog = LoaderDialog()

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
        viewModel.populateDB()
        loaderDialog.show(parentFragmentManager, "LoaderDialog")
        attachObservers()
        return binding.root
    }

    private fun attachObservers() {
        viewModel.loginSuccess.observe(viewLifecycleOwner, EventObserver {
            if (it) {
                findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
            } else {
                Toast.makeText(
                    requireContext(),
                    getString(R.string.login_error),
                    Toast.LENGTH_SHORT
                ).show()
            }
        })

        viewModel.populateSuccess.observe(viewLifecycleOwner, EventObserver {
            if (it) {
                loaderDialog.dismiss()
            } else {
                Toast.makeText(
                    requireContext(),
                    getString(R.string.populate_database_error),
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }


}