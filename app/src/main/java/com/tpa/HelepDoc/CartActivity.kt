package com.tpa.HelepDoc

import android.app.Dialog
import android.content.DialogInterface
import android.icu.util.Calendar
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Adapter
import android.widget.Button
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.tpa.HelepDoc.ProductPage.Companion.carts
import com.tpa.HelepDoc.adapters.CartAdapter
import com.tpa.HelepDoc.models.Cart
import com.tpa.HelepDoc.models.Product
import com.tpa.HelepDoc.models.Transaction

class CartActivity : AppCompatActivity() {
    private lateinit var rvCart:RecyclerView
    private lateinit var cartAdapter:CartAdapter
    lateinit var btnCheckout:Button
    private val USERID:String = "-M43c_mp8Ur1bDV3PksP"
    private lateinit var databaseReference: DatabaseReference

    companion object{
        var totalPayment: Double = 0.0
    }
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        databaseReference = FirebaseDatabase.getInstance().getReference("transaction")

        rvCart = findViewById(R.id.rv_carts)
        cartAdapter = CartAdapter(carts,this@CartActivity)
        btnCheckout = findViewById(R.id.btn_checkout)
        updateTotalPayment()
        rvCart.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@CartActivity)
            this.adapter = cartAdapter
        }

        var dialogListener:DialogInterface.OnClickListener = DialogInterface.OnClickListener{
                _, which ->
            if(which == DialogInterface.BUTTON_POSITIVE){
                val id:String? = databaseReference.push().key
                val  calendar:Calendar = Calendar.getInstance()
                val transaction:Transaction = Transaction(id, carts, USERID, calendar.time, totalPayment)
                databaseReference.child(id!!).setValue(transaction)
                carts.removeAll(carts)
                finish()
            }else if(which==DialogInterface.BUTTON_NEGATIVE){
                //Do nothing
            }


        }

        btnCheckout.setOnClickListener(View.OnClickListener {
            updateTotalPayment()
            var builder : AlertDialog.Builder = AlertDialog.Builder(this@CartActivity)
            builder.setMessage("Total payment: $totalPayment").setPositiveButton("Yes",dialogListener)
                .setNegativeButton("No",dialogListener).show()

        })


    }

    fun updateTotalPayment(){
        if(!carts.isEmpty()){
            totalPayment= 0.0;
            for(i in carts){
                totalPayment += i.quantity * i.product.price
            }

        }else{
            totalPayment = 0.0
        }
        btnCheckout.text= "CHECKOUT $totalPayment"
    }

}
