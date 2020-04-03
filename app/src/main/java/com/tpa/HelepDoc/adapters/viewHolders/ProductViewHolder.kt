package com.tpa.HelepDoc.adapters.viewHolders

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tpa.HelepDoc.R
import com.tpa.HelepDoc.adapters.ProductAdapter
import kotlinx.android.synthetic.main.product.view.*

class ProductViewHolder  constructor (view: View): RecyclerView.ViewHolder(view){
    var ivProductImage:ImageView = view.findViewById(R.id.iv_product_image) as ImageView
    var tvProductName:TextView = view.findViewById(R.id.tv_product_name)
    var tvProductPrice:TextView = view.findViewById(R.id.tv_product_price)



}