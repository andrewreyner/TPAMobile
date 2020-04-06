package com.tpa.HelepDoc.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tpa.HelepDoc.R
import com.tpa.HelepDoc.adapters.viewHolders.CartViewHolder
import com.tpa.HelepDoc.models.Cart

class TransactionDetailAdapter (val details: ArrayList<Cart>): RecyclerView.Adapter<CartViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cart_transaction, parent, false)
        return CartViewHolder(view)
    }

    override fun getItemCount(): Int {
        return details.size
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        var cart = details[position]
        holder.bindItemTransaction(cart)
    }
}