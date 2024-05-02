package com.example.eldarwalletchallenge.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.eldarwalletchallenge.R
import com.example.eldarwalletchallenge.databinding.FragmentHomeBinding
import com.example.eldarwalletchallenge.domain.model.HomeAction
import com.example.eldarwalletchallenge.ui.adapters.HomeActionAdapter
import com.example.eldarwalletchallenge.ui.reusables.LoaderDialog
import com.example.eldarwalletchallenge.ui.viewModels.MainViewModel

class HomeFragment : Fragment(),
    HomeActionAdapter.OnActionClickListener {

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
        attachObservers()
        return binding.root
    }

    private fun attachObservers() {
        viewModel.homeActions.observe(viewLifecycleOwner) { homeActions ->
            with(binding.homeActions) {
                setHasFixedSize(true)
                layoutManager = GridLayoutManager(requireContext(), 3)
                val actionAdapter = HomeActionAdapter(homeActions)
                actionAdapter.setListener(this@HomeFragment)
                adapter = actionAdapter
            }
            loaderDialog.dismiss()
        }
    }

    override fun onActionClicked(homeAction: HomeAction) {

    }
}