package com.example.unittest.presentation.list_page.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.unittest.R
import com.example.unittest.domain.core.model.CoinPresentation
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou

class CoinsListAdapter(private val onClick: (CoinPresentation) -> Unit) :
    ListAdapter<CoinPresentation, CoinsListAdapter.CoinViewHolder>(CoinDiffCallback) {

    class CoinViewHolder(itemView: View, val onClick: (CoinPresentation) -> Unit) :
        RecyclerView.ViewHolder(itemView) {
        private val coinTitleTextView: TextView = itemView.findViewById(R.id.coinTitleTextView)
        private val coinPriceTextView: TextView = itemView.findViewById(R.id.coinPriceTextView)
        private val coinImageView: ImageView = itemView.findViewById(R.id.coinImageView)

        private var currentFlower: CoinPresentation? = null

        init {
            itemView.setOnClickListener {
                currentFlower?.let {
                    onClick(it)
                }
            }
        }

        fun bind(coin: CoinPresentation) {
            currentFlower = coin
            coinTitleTextView.text = coin.name
            coinPriceTextView.text = coin.price

            GlideToVectorYou
                .init()
                .with(itemView.context).setPlaceHolder(R.drawable.ic_dollar, R.drawable.ic_dollar)
                .load(Uri.parse(coin.iconUrl), coinImageView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_coin, parent, false)
        return CoinViewHolder(view, onClick)
    }

    override fun onBindViewHolder(holder: CoinViewHolder, position: Int) {
        val coin = getItem(position)
        holder.bind(coin)
    }
}

object CoinDiffCallback : DiffUtil.ItemCallback<CoinPresentation>() {
    override fun areItemsTheSame(oldItem: CoinPresentation, newItem: CoinPresentation): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: CoinPresentation, newItem: CoinPresentation): Boolean {
        return oldItem.uuid == newItem.uuid
    }
}