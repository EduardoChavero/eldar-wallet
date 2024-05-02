package com.example.eldarwalletchallenge.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.eldarwalletchallenge.R
import com.example.eldarwalletchallenge.databinding.HomeCardBinding
import com.example.eldarwalletchallenge.domain.model.Card

class HomeCardAdapter(private val userCards: List<Card>) :
    RecyclerView.Adapter<HomeCardViewHolder>(),
    HomeCardViewHolder.OnItemClickListener {

    fun interface OnCardClickListener {
        fun onCardClicked(card: Card)
    }

    private var listener: OnCardClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeCardViewHolder {
        return HomeCardViewHolder(
            HomeCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun getItemCount(): Int {
        return userCards.size
    }

    override fun onBindViewHolder(holder: HomeCardViewHolder, position: Int) {
        holder.bind(userCards[position], position, this)
    }

    fun setListener(listener: OnCardClickListener) {
        this.listener = listener
    }

    override fun onItemClicked(position: Int) {
        listener?.onCardClicked(userCards[position])
    }

}

class HomeCardViewHolder(private val binding: HomeCardBinding) :
    RecyclerView.ViewHolder(binding.root),
    View.OnClickListener {

    interface OnItemClickListener {
        fun onItemClicked(position: Int)
    }

    private var position: Int? = null
    private var listener: OnItemClickListener? = null

    fun bind(card: Card, position: Int, listener: OnItemClickListener) {
        this.position = position
        this.listener = listener

        binding.cardLogo.setImageDrawable(
            ContextCompat.getDrawable(
                itemView.context,
                card.logoRes
            )
        )

        binding.ownerName.text = card.ownerName
        binding.cardExpiration.text = card.expirationDate
        binding.cardNumber.text =
            itemView.context.getString(R.string.home_card_number_hidden, card.lastNumbers)

        binding.homeCardContainer.setOnClickListener(this)

    }

    override fun onClick(p0: View) {
        position?.let { listener?.onItemClicked(it) }
    }

}