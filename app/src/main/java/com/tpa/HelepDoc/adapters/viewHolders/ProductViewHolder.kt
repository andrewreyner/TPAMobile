package com.tpa.HelepDoc.adapters.viewHolders

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tpa.HelepDoc.R
import com.tpa.HelepDoc.adapters.ProductAdapter
import com.tpa.HelepDoc.models.Product
import kotlinx.android.synthetic.main.product.view.*

class ProductViewHolder  constructor (view: View): RecyclerView.ViewHolder(view){
    var ivProductImage:ImageView = view.findViewById(R.id.iv_product_image) as ImageView
    var tvProductName:TextView = view.findViewById(R.id.tv_product_name)
    var tvProductPrice:TextView = view.findViewById(R.id.tv_product_price)
    var tvProductRate:TextView = view.findViewById(R.id.tv_product_rating)
    fun bindItem(product: Product){
        ivProductImage.setImageResource(R.drawable.sample_product)
        tvProductName.text = product.name
        tvProductPrice.text= "IDR " +product.price.toInt().toString()
        Glide.with(itemView.context).load(product.image).into(ivProductImage)
        tvProductRate.text = "Rate: "+ product.rating.toString()

    }

}