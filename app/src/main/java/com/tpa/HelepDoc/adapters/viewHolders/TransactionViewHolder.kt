package com.tpa.HelepDoc.adapters.viewHolders

import android.content.Context
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tpa.HelepDoc.R
import com.tpa.HelepDoc.adapters.TransactionDetailAdapter
import com.tpa.HelepDoc.models.Cart
import com.tpa.HelepDoc.models.Transaction

class TransactionViewHolder constructor (view: View): RecyclerView.ViewHolder(view) {
    var layout = view.findViewById<LinearLayout>(R.id.transaction_detail_container)
    var tvTransactionDate = view.findViewById<TextView>(R.id.tv_transaction_date)
    var detail_layout = view.findViewById<LinearLayout>(R.id.ly_transaction_detail)
    var rvCart = view.findViewById<RecyclerView>(R.id.drop_detail_transaction)
    var tvGrandTotal = view.findViewById<TextView>(R.id.tv_grand_total)

    fun bindItem(trans: Transaction, cont: Context) {
        tvTransactionDate.setText("Transaction Date: " + trans.transactionDate)
        tvGrandTotal.setText("Grand Total: IDR " + trans.userBalance)
    }

}