package com.example.eldarwalletchallenge.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.eldarwalletchallenge.R
import com.example.eldarwalletchallenge.databinding.FragmentPayCardSelectionBinding
import com.example.eldarwalletchallenge.domain.model.Card
import com.example.eldarwalletchallenge.ui.adapters.HomeCardAdapter
import com.example.eldarwalletchallenge.ui.reusables.EventObserver
import com.example.eldarwalletchallenge.ui.viewModels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PaySelectCardFragment : Fragment(),
    HomeCardAdapter.OnCardClickListener {

    private lateinit var binding: FragmentPayCardSelectionBinding
    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPayCardSelectionBinding.inflate(inflater, container, false)

        viewModel.getUserCards()

        binding.payCardSelectionButton.setOnClickListener {
            findNavController().popBackStack()
        }

        attachObservers()
        return binding.root
    }

    private fun attachObservers() {
        viewModel.userCards.observe(viewLifecycleOwner, EventObserver { userCards ->
            with(binding.payCardList) {
                layoutManager = LinearLayoutManager(context)
                val cardsAdapter = HomeCardAdapter(userCards)
                cardsAdapter.setListener(this@PaySelectCardFragment)
                setHasFixedSize(true)
                adapter = cardsAdapter
            }
        })
    }

    override fun onCardClicked(card: Card) {
        viewModel.saveCardSelected(card)
        val i = Intent(activity, PayNFCReaderActivity::class.java)
        startActivity(i)
    }
}