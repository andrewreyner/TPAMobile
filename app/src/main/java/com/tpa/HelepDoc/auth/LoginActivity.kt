package com.tpa.HelepDoc.auth

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.tpa.HelepDoc.R
import com.tpa.HelepDoc.main.HomeActivity
import com.tpa.HelepDoc.main.ProfileActivity
import com.tpa.HelepDoc.models.User
import kotlinx.android.synthetic.main.activity_login.*
import java.util.*


class LoginActivity : AppCompatActivity() {

    var userRef = FirebaseDatabase.getInstance().getReference("users")
    var users: Vector<User> = Vector()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        var sp = getSharedPreferences("Auth",
            Context.MODE_PRIVATE);

        if(!sp.getString("comeFrom", "").equals("Register")) {
            resetSP()
        }

        var fullname = sp.getString("fullname", "");
        findViewById<EditText>(R.id.emailOrPhone).setText(fullname)

        userRef.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onDataChange(p0: DataSnapshot) {
                if(p0.exists()) {
                    for(temp in p0.children) {
                        val user = temp.getValue(User::class.java)
                        users.add(user!!)
                    }
                }
            }
        })

        login.setOnClickListener {
            var emailOrPhone = findViewById<EditText>(R.id.emailOrPhone).text.toString()
            var password = findViewById<EditText>(R.id.password).text.toString()

            if(emailOrPhone.equals("") || password.equals("")) {
                Toast.makeText(applicationContext, "Please fill all field!", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            for(u in users) {
                if((emailOrPhone.equals(u.email) || emailOrPhone.equals(u.phoneNumber)) && password.equals(u.password)) {
                    Toast.makeText(applicationContext, "Login success!", Toast.LENGTH_LONG).show()
                    setSP(u.id as String, u.fullname, u.email, u.password, u.phoneNumber, u.gender, u.dob, u.balance, u.picture)
                    var intent = Intent(this@LoginActivity, ProfileActivity::class.java)
                    finish()
                    startActivity(intent)
                    return@setOnClickListener
                }
            }

            Toast.makeText(applicationContext, "User doesn't exists or wrong password!", Toast.LENGTH_LONG).show()
            findViewById<EditText>(R.id.password).setText("")
        }

    }

    fun goToSignUp(view: View) {
        var intent = Intent(this@LoginActivity, RegisterActivity::class.java)
        finish()
        startActivity(intent)
    }

    private fun setSP(id: String, fullname: String, email: String, password: String, phone: String, gender: String, dob: String, balance: Float, picture: String) {
        val sp = getSharedPreferences(
            "Auth",
            Context.MODE_PRIVATE
        )

        val auth = sp.edit()

        auth.putString("id", id)
        auth.putString("fullname", fullname)
        auth.putString("email", email)
        auth.putString("password", password)
        auth.putString("phone", phone)
        auth.putString("gender", gender)
        auth.putString("dob", dob)
        auth.putFloat("balance", balance)
        auth.putString("picture", picture)

        auth.commit()
    }

    private fun resetSP() {
        val sp = getSharedPreferences(
            "Auth",
            Context.MODE_PRIVATE
        )

        val auth = sp.edit()

        auth.putString("id", "")
        auth.putString("fullname", "")
        auth.putString("email", "")
        auth.putString("password", "")
        auth.putString("phone", "")
        auth.putString("gender", "")
        auth.putString("dob", "")
        auth.putFloat("balance", 0.0f)
        auth.putString("picture", "")
        auth.putString("comeFrom", "")

        auth.commit()
    }
}
