package com.tpa.HelepDoc

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView

import android.widget.Toast
import com.bumptech.glide.Glide

import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

import java.io.File
import java.io.FileOutputStream

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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






        val intent = Intent(this@MainActivity, ChatActivity::class.java)
        startActivity(intent)
        finish()
    }
}