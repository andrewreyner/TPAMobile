package com.tpa.HelepDoc.auth

import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.tpa.HelepDoc.R
import com.tpa.HelepDoc.models.User
import kotlinx.android.synthetic.main.activity_register.*
import java.text.SimpleDateFormat
import java.util.*


class RegisterActivity : AppCompatActivity() {

    var userRef = FirebaseDatabase.getInstance().getReference("users")
    var users: Vector<User> = Vector()
    var cal = Calendar.getInstance()
    lateinit var genderRadio : RadioGroup

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        genderRadio = findViewById<RadioGroup>(R.id.genderGroup)

        resetSP()

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

        val dateSetListener = object : DatePickerDialog.OnDateSetListener {
            override fun onDateSet(view: DatePicker, year: Int, monthOfYear: Int,
                                   dayOfMonth: Int) {
                cal.set(Calendar.YEAR, year)
                cal.set(Calendar.MONTH, monthOfYear)
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                updateDateInView()
            }
        }

        changeDate!!.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View) {
                DatePickerDialog(this@RegisterActivity,
                    dateSetListener,
                    cal.get(Calendar.YEAR),
                    cal.get(Calendar.MONTH),
                    cal.get(Calendar.DAY_OF_MONTH)).show()
            }

        })

        register.setOnClickListener {
            var fullname = findViewById<EditText>(R.id.fullname).text.toString()
            var email = findViewById<EditText>(R.id.email).text.toString()
            var password = findViewById<EditText>(R.id.password).text.toString()
            var confirmpass = findViewById<EditText>(R.id.confirmpass).text.toString()
            var phone = findViewById<EditText>(R.id.phone).text.toString()
            var gender = findViewById<RadioButton>(findViewById<RadioGroup>(R.id.genderGroup).getCheckedRadioButtonId()).text.toString()
            var dob = changeDate.text.toString()

            for(u in users) {
                if(email.equals(u.email) || phone.equals(u.phoneNumber)) {
                    Toast.makeText(applicationContext, "User already exists!", Toast.LENGTH_LONG).show()
                    return@setOnClickListener
                }
            }

            if(fullname.equals("") || dob.equals("Change Date") || email.equals("") || password.equals("") || confirmpass.equals("") || phone.equals("") || gender.equals("")) {
                Toast.makeText(applicationContext, "Please fill all field!", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            if(!confirmpass.equals(password)) {
                Toast.makeText(applicationContext, "Password not match with confirm password!", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            var id: String? = userRef.push().key
            userRef.child(id!!).setValue(User(id, fullname, gender, dob, email, password, phone))

            Toast.makeText(applicationContext, "Successfully registered user!", Toast.LENGTH_LONG).show()
            resetField()
            setSP(id as String, fullname, email, password, phone, gender, dob)

            goToSignIn(findViewById(android.R.id.content))
        }

    }

    private fun resetField() {
        genderRadio.clearCheck()
        changeDate.text = "Change Date"
        findViewById<EditText>(R.id.fullname).setText("")
        findViewById<EditText>(R.id.email).setText("")
        findViewById<EditText>(R.id.password).setText("")
        findViewById<EditText>(R.id.confirmpass).setText("")
        findViewById<EditText>(R.id.phone).setText("")
    }

    private fun updateDateInView() {
        val myFormat = "MM/dd/yyyy" // mention the format you need
        val sdf = SimpleDateFormat(myFormat, Locale.US)
        changeDate!!.text = sdf.format(cal.getTime())
    }

    private fun setSP(id: String, fullname: String, email: String, password: String, phone: String, gender: String, dob: String) {
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
        auth.putFloat("balance", 0f)
        auth.putString("comeFrom", "Register")

        auth.commit()
    }

    private fun resetSP() {
        val sp = getSharedPreferences("Auth", Context.MODE_PRIVATE)

        val auth = sp.edit()

        auth.putString("id", "")
        auth.putString("fullname", "")
        auth.putString("email", "")
        auth.putString("password", "")
        auth.putString("phone", "")
        auth.putString("gender", "")
        auth.putString("dob", "")
        auth.putFloat("balance", 0.0f)
        auth.putString("comeFrom", "")

        auth.commit()
    }

    fun goToSignIn(view: View) {
        var intent = Intent(this@RegisterActivity, LoginActivity::class.java)
        finish()
        startActivity(intent)
    }

}
