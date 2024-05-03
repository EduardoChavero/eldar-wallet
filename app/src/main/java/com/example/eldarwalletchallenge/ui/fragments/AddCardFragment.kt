package com.example.eldarwalletchallenge.ui.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.MutableLiveData
import androidx.navigation.fragment.findNavController
import com.example.eldarwalletchallenge.R
import com.example.eldarwalletchallenge.databinding.FragmentAddCardBinding
import com.example.eldarwalletchallenge.ui.reusables.EventObserver
import com.example.eldarwalletchallenge.ui.reusables.GetResourceUtil
import com.example.eldarwalletchallenge.ui.reusables.LoaderDialog
import com.example.eldarwalletchallenge.ui.viewModels.MainViewModel

class AddCardFragment : Fragment(), TextWatcher {

    private lateinit var binding: FragmentAddCardBinding
    private val viewModel: MainViewModel by activityViewModels()

    private val cardLogoObserve = MutableLiveData<String>()
    private val loaderDialog = LoaderDialog()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddCardBinding.inflate(inflater, container, false)


        binding.addCardButton.setOnClickListener {
            loaderDialog.show(parentFragmentManager, getString(R.string.loader_tag))

            val buildExpiration =
                binding.cardExpirationMonthInput.text.toString() + "/" + binding.cardExpirationYearInput.text.toString()

            viewModel.addCardRequest(
                binding.cardNumberInput.text.toString(),
                binding.cardOwnerInput.text.toString(),
                buildExpiration,
                binding.cardCvvInput.text.toString()
            )
        }

        attachObservers()
        binding.cardNumberInput.addTextChangedListener(this)
        return binding.root
    }

    private fun attachObservers() {
        cardLogoObserve.observe(viewLifecycleOwner) {
            val logo = GetResourceUtil.getLogo(it)
            if (logo == 0) {
                Toast.makeText(
                    requireContext(),
                    getString(R.string.add_card_number_error_toast),
                    Toast.LENGTH_SHORT
                ).show()
                return@observe
            }
            binding.addCardLogo.setImageDrawable(
                ContextCompat.getDrawable(
                    requireContext(),
                    logo
                )
            )
        }
        viewModel.addCardSuccess.observe(viewLifecycleOwner, EventObserver {
            loaderDialog.dismiss()
            if (it) {
                Toast.makeText(
                    requireContext(),
                    getString(R.string.add_card_success_toast),
                    Toast.LENGTH_SHORT
                ).show()
                findNavController().popBackStack()
            } else {
                Toast.makeText(
                    requireContext(),
                    getString(R.string.add_card_error_toast),
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        //
    }

    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        //
    }

    override fun afterTextChanged(input: Editable) {
        if (input.toString().isNotEmpty()) {
            cardLogoObserve.postValue(input.first().toString())
        }
    }

}