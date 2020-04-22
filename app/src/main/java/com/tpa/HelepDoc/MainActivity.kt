package com.tpa.HelepDoc

import android.content.Intent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.ImageView

import android.widget.Toast
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.bumptech.glide.Glide
import com.google.android.material.navigation.NavigationView

import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.tpa.HelepDoc.auth.LoginActivity

import java.io.File
import java.io.FileOutputStream

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener{
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationView: NavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        drawerLayout = findViewById(R.id.drawer_layout)
        navigationView = findViewById(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener(this)

//        actionBar!!.setDisplayHomeAsUpEnabled(true);
//        actionBar!!.setHomeButtonEnabled(true);

        val intent = Intent(this@MainActivity, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onNavigationItemSelected(p0: MenuItem): Boolean {
        when(p0.itemId){
            R.id.item_chat->{

                val intent = Intent(this@MainActivity, ChatActivity::class.java)
                startActivity(intent)
            }
            R.id.item_product->{
                val intent = Intent(this@MainActivity, ProductPage::class.java)
                startActivity(intent)
            }
        }
        drawerLayout.closeDrawer(GravityCompat.START)
        p0.isChecked = false
//        finish()
        Log.e("test", "test")

        return false
    }


    private fun test(){




//        val storageRef:StorageReference = FirebaseStorage.getInstance().getReference("images2").child("hehe2")
//
//        val bitmap:Bitmap = BitmapFactory.decodeResource(resources, R.drawable.test)
//
//        var fos: FileOutputStream?
//        val file2:File= applicationContext.filesDir
//        val imageFile = File(file2, "test.jpg") // just let the child parameter empty
//
//        fos = FileOutputStream(imageFile)
//
//        bitmap.compress(Bitmap.CompressFormat.JPEG,100,fos)
//
//        fos.close()



//        val file:File = File("", "")
//        if(imageFile.exists()){
//            Toast.makeText(this, "gotcha", Toast.LENGTH_LONG).show()
//        }else{
//            Toast.makeText(this, "Nope", Toast.LENGTH_LONG).show()
//
//        }
//        val uri:Uri = Uri.fromFile(imageFile)
//        storageRef.putFile(uri).addOnSuccessListener {
//            taskSnapshot ->
//            storageRef.downloadUrl.addOnCompleteListener {
//                taskSnapshot ->
//                // get image uri FOR PATH OF IMAGE
//                    Log.i("IMAGEHERE:", taskSnapshot.result.toString())
//            }
//
//        }



        ///READ IMAGEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE
//        var iv:ImageView= findViewById(R.id.testImage)
//        storageRef.downloadUrl.addOnSuccessListener {
//            uri ->
//            Glide.with(this).load(uri).into(iv)
//            Log.i("IMAGEHERE:", uri.toString())
//        }




    }
}