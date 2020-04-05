package com.tpa.HelepDoc

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage
import com.tpa.HelepDoc.adapters.ProductAdapter
import com.tpa.HelepDoc.models.Cart
import com.tpa.HelepDoc.models.Product
import java.io.File
import java.io.FileOutputStream

class ProductPage : AppCompatActivity() {
    private lateinit var rvProduct:RecyclerView
    private lateinit var productAdapter: ProductAdapter
    private lateinit var products:ArrayList<Product>

    private val drugDatabaseRef=  FirebaseDatabase.getInstance().getReference("drugs")
    private val drugStorageReference = FirebaseStorage.getInstance().getReference("drugs")

    companion object{
        var carts :ArrayList<Cart>  = ArrayList()
    }




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_page)

        rvProduct  =  findViewById(R.id.rv_product)
        products = ArrayList<Product>()
        // WRITE
//        initProducts()
        // !! -> means data non null/ promise that never null

        // READ DATA
               drugDatabaseRef.addValueEventListener(object: ValueEventListener{
                   override fun onCancelled(dataSnapShot: DatabaseError) {
                   }

                   override fun onDataChange(dataSnapshot: DataSnapshot) {
                       if(dataSnapshot.exists()){
                           for(p in dataSnapshot.children){
                               // MAKE SURE THE DRUG Constructor is Initialize -> check on the models
                               val product = p.getValue(Product::class.java)
                               products.add(product!!)
                           }
                           productAdapter = ProductAdapter(products,this@ProductPage)

                           rvProduct.apply {
                               setHasFixedSize(true)
                               layoutManager = GridLayoutManager(this@ProductPage, 2)
                               adapter = productAdapter
                           }
                       }
                   }
               })
    }

    private fun insertProduct(p: Product, image: Int){
        val storageRef = drugStorageReference.child(p.id.toString())
        val bitmap:Bitmap = BitmapFactory.decodeResource(resources, image)
        var fos: FileOutputStream?
        val file2:File= applicationContext.filesDir
        val imageFile = File(file2, "test.jpg") // just let the child parameter empty

        fos = FileOutputStream(imageFile)

        bitmap.compress(Bitmap.CompressFormat.JPEG,100,fos)

        fos.close()
        val uri:Uri = Uri.fromFile(imageFile)
        storageRef.putFile(uri).addOnSuccessListener {
            storageRef.downloadUrl.addOnCompleteListener {
                taskSnapshot ->
                    val imageSrc:String= taskSnapshot.result.toString()
                    p.image = imageSrc
                    products.add(p)
                    drugDatabaseRef.child(p.id!!).setValue(p)
            }

        }
    }

    fun initProducts(){
        var id:String? = drugDatabaseRef.push().key    // generateID
        var p: Product = Product(id,
            "Per tablet" ,
            "FLUCONAZOLE 150 MG KAPSUL",
            "INFORMASI OBAT INI HANYA UNTUK KALANGAN MEDIS. Menganitis kriptokokal, Kandidiasis sistemik, kandidiasis orofaringeal, kandidiasis vagina akul atau relaps, infeksi kandida superfisial, infeksi kandida, iskemik atau infeksi kriptokokal" ,
            "HARUS DENGAN RESEP DOKTER. AIDS. Hamil & laktasi. Anak < 18 tahun Kategori kehamilan: C, D (pada trimester 2 dan 3)\n" ,
            "PENGGUNAAN OBAT INI HARUS SESUAI DENGAN PETUNJUK DOKTER.\n" +
                    "Dewasa menginitis kriptokokal : hari ke-1 : 400 mg sebagai dosis tunggal; hari ke-2 dan seterusnya 200 - 400 mg per hari. Lama terapi : 6 - 8 minggu. \n" +
                    "Kandidiasis mukosal: 50 mg/hari selama 14 hari.\n" +
                    "Kandidiasis vagian: 150 mg sebagai dosis tunggal oral." ,
            "Fluconazole 150 mg" ,
            20700.00 ,
            "")
        insertProduct(p, R.drawable.fluconazole)


        id = drugDatabaseRef.push().key
        var p2 = Product(
            id,
            "Per strip",
            "KETOCONAZOLE 200 MG 10 TABLET",
            "INFORMASI OBAT INI HANYA UNTUK KALANGAN MEDIS. Infeksi jamur sistemik, kandidiasis mukokutan kronis yang tidak responsif terhadap nistatin & obat-obat lainnya\n",
            "HARUS DENGAN RESEP DOKTER. Wanita hamil dan menyusui. Penderita dengan gangguan fungsi hati dan Insufisiensi adrenal. Kategori Kehamilan: C",
            "PENGGUNAAN OBAT INI HARUS SESUAI DENGAN PETUNJUK DOKTER. Infeksi mikosis: Dewasa 1 tablet per hari selama 14 hari. Jika respon tidak ada, dapat ditingkatkan menjadi 400 mg. Kandidiasis vaginal: 2 tablet selama 5 hari.",
            "Ketoconazole 200 mg",
            5200.00,
            ""
        )
        insertProduct(p2, R.drawable.ketoconazole)

        id=drugDatabaseRef.push().key
        var p3 = Product(
            id,
            "Per strip",
            "MECOBALAMIN 500 MCG 10 KAPSUL",
            "INFORMASI OBAT INI HANYA UNTUK KALANGAN MEDIS. Neuropati perifer, tinitus, vertigo, anemia megalobastik karena defisiensi vitamin B12\n",
            "HARUS DENGAN RESEP DOKTER. Hipersensitif komponen",
            "PENGGUNAAN OBAT INI HARUS SESUAI DENGAN PETUNJUK DOKTER. 3 x sehari 1 kapsul\n",
            "Mecobalamin 500 mg\n",
            8800.00,
            ""
        )
        insertProduct(p3, R.drawable.mecobalamin)
    }


}