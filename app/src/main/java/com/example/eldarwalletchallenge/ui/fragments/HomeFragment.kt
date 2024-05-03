package com.example.eldarwalletchallenge.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.eldarwalletchallenge.R
import com.example.eldarwalletchallenge.databinding.FragmentHomeBinding
import com.example.eldarwalletchallenge.domain.model.Card
import com.example.eldarwalletchallenge.domain.model.HomeAction
import com.example.eldarwalletchallenge.domain.model.HomeActionTypes
import com.example.eldarwalletchallenge.ui.adapters.HomeActionAdapter
import com.example.eldarwalletchallenge.ui.adapters.HomeCardAdapter
import com.example.eldarwalletchallenge.ui.reusables.EventObserver
import com.example.eldarwalletchallenge.ui.reusables.LoaderDialog
import com.example.eldarwalletchallenge.ui.viewModels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(),
    HomeActionAdapter.OnActionClickListener,
    HomeCardAdapter.OnCardClickListener {

    private lateinit var binding: FragmentHomeBinding
    private val viewModel: MainViewModel by activityViewModels()

    private val loaderDialog = LoaderDialog()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        loaderDialog.show(parentFragmentManager, getString(R.string.loader_tag))
        binding.homeHeader.text =
            getString(R.string.home_header_welcome, viewModel.getUserFistName())
        binding.balanceAmount.text = viewModel.getUserBalance()
        viewModel.getHomeActions()
        viewModel.getUserCards()

        attachObservers()
        return binding.root
    }

    private fun attachObservers() {
        viewModel.homeActions.observe(viewLifecycleOwner) { homeActions ->
            with(binding.homeActions) {
                layoutManager = GridLayoutManager(context, 3)
                val actionAdapter = HomeActionAdapter(homeActions)
                actionAdapter.setListener(this@HomeFragment)
                setHasFixedSize(true)
                adapter = actionAdapter
            }
        }
        viewModel.userCards.observe(viewLifecycleOwner, EventObserver { userCards ->
            with(binding.cardList) {
                layoutManager = LinearLayoutManager(context)
                val cardsAdapter = HomeCardAdapter(userCards)
                cardsAdapter.setListener(this@HomeFragment)
                setHasFixedSize(true)
                adapter = cardsAdapter
            }
            loaderDialog.dismiss()
        })

        viewModel.generateQrCodeSuccess.observe(viewLifecycleOwner, EventObserver { qrGenerated ->
            loaderDialog.dismiss()
            if (qrGenerated) {
                findNavController().navigate(R.id.action_homeFragment_to_QRViewFragment)
            } else {
                Toast.makeText(
                    requireContext(),
                    getString(R.string.home_qr_generate_error),
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }

    override fun onActionClicked(homeAction: HomeAction) {
        when (homeAction.type) {
            HomeActionTypes.PAYMENT -> {
                findNavController().navigate(R.id.action_homeFragment_to_paySelectCardFragment)
            }
            HomeActionTypes.ADD_CARD -> {
                findNavController().navigate(R.id.action_homeFragment_to_addCardFragment)
            }
            HomeActionTypes.GENERATE_QR -> {
                loaderDialog.show(parentFragmentManager, getString(R.string.loader_tag))
                viewModel.generateQRCode()
            }
            HomeActionTypes.DEFAULT -> {}
        }
    }

    override fun onCardClicked(card: Card) {


    }
}