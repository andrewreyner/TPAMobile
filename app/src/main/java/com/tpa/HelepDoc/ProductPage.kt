package com.tpa.HelepDoc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.tpa.HelepDoc.adapters.ProductAdapter
import com.tpa.HelepDoc.models.Product

class ProductPage : AppCompatActivity() {
    private lateinit var rvProduct:RecyclerView
    private lateinit var productAdapter: ProductAdapter
    private lateinit var products:ArrayList<Product>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_page)

        rvProduct  =  findViewById(R.id.rv_product)
        products = ArrayList<Product>()

        val drugRef=  FirebaseDatabase.getInstance().getReference("drugs")

        // WRITE


        var id:String? = drugRef.push().key    // generateID
//        products.add(Drug(id, "hueuhe", "mahal" , "murah" , "pakai saja" ,"Air putih" ,200000.0 ,""))
//        drugRef.child(id!!).setValue(products[0]).addOnCompleteListener {
//            Toast.makeText(this@ProductPage, "heuhe", Toast.LENGTH_LONG).show()
//        }


        // !! -> means data non null/ promise that never null

        // READ DATA
            drugRef.addValueEventListener(object: ValueEventListener{
                override fun onCancelled(dataSnapShot: DatabaseError) {
                }

                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    if(dataSnapshot.exists()){
                        for(p in dataSnapshot.children){
                            // MAKE SURE THE DRUG Constructor is Initialize -> check on the models
                            val product = p.getValue(Product::class.java)
                            products.add(product!!)
                        }
                        productAdapter = ProductAdapter(products)

                        rvProduct.apply {
                            setHasFixedSize(true)
                            layoutManager = GridLayoutManager(this@ProductPage, 2)
                            adapter = productAdapter
                        }
                    }
                }
            })






    }
}