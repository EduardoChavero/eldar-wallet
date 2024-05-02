package com.example.eldarwalletchallenge.ui.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.eldarwalletchallenge.databinding.HomeActionBinding
import com.example.eldarwalletchallenge.domain.model.HomeAction

class HomeActionAdapter(private val homeActions: List<HomeAction>) :
    RecyclerView.Adapter<HomeActionViewHolder>(),
    HomeActionViewHolder.OnItemClickListener {

    fun interface OnActionClickListener {
        fun onActionClicked(homeAction: HomeAction)
    }

    private var listener: OnActionClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeActionViewHolder {
        return HomeActionViewHolder(
            HomeActionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun getItemCount(): Int {
        return homeActions.size
    }

    override fun onBindViewHolder(holder: HomeActionViewHolder, position: Int) {
        holder.bind(homeActions[position], position, this)
    }

    fun setListener(listener: OnActionClickListener) {
        this.listener = listener
    }

    override fun onItemClicked(position: Int) {
        listener?.onActionClicked(homeActions[position])
    }

}

class HomeActionViewHolder(private val binding: HomeActionBinding) :
    RecyclerView.ViewHolder(binding.root),
    View.OnClickListener {

    interface OnItemClickListener {
        fun onItemClicked(position: Int)
    }

    private var position: Int? = null
    private var listener: OnItemClickListener? = null

    fun bind(homeAction: HomeAction, position: Int, listener: OnItemClickListener) {
        this.position = position
        this.listener = listener

        binding.actionIcon.setImageDrawable(
            ContextCompat.getDrawable(
                itemView.context,
                homeAction.icon
            )
        )
        binding.actionLabel.text = homeAction.label
        binding.homeActionContainer.setOnClickListener(this)
    }

    override fun onClick(p0: View) {
        Log.d("TAG", "onClick")
        position?.let {
            listener?.onItemClicked(it)
        }
    }

}