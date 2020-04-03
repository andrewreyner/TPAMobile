package com.tpa.HelepDoc.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tpa.HelepDoc.R
import com.tpa.HelepDoc.adapters.viewHolders.ProductViewHolder
import com.tpa.HelepDoc.models.Product

class ProductAdapter(val drugs: ArrayList<Product>): RecyclerView.Adapter<ProductViewHolder>()    {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.product, parent, false)
        return ProductViewHolder(view)
    }

    override fun getItemCount(): Int {
        return drugs.size
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val drug:Product = drugs[position]
//        holder.ivProductImage.setImageResource("")
        holder.tvProductName.text = drug.name
        holder.tvProductPrice.text= drug.price.toString()


    }


}