package com.tpa.HelepDoc.adapters.viewHolders

import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tpa.HelepDoc.ProductPage
import com.tpa.HelepDoc.R
import com.tpa.HelepDoc.models.Cart
import com.tpa.HelepDoc.models.Product

class CartViewHolder  constructor (view: View): RecyclerView.ViewHolder(view){
    var ivProductImage: ImageView = view.findViewById(R.id.iv_product_image) as ImageView
    var tvProductName:TextView = view.findViewById(R.id.tv_product_name)
    var tvProductPrice: TextView = view.findViewById(R.id.tv_product_price)
    var tvQuantity:TextView = view.findViewById(R.id.tv_quantity)
    var btnAdd:Button = view.findViewById(R.id.btn_add)
    var btnDelete:Button = view.findViewById(R.id.btn_delete)
    fun bindItem(cart:Cart){
        tvProductName.text = cart.product.name
        tvProductPrice.text= "IDR " +cart.product.price.toInt().toString()
        Glide.with(itemView.context).load(cart.product.image).into(ivProductImage)
        tvQuantity.text = cart.quantity.toString()


    }


}