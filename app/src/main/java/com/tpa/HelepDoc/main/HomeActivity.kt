package com.tpa.HelepDoc.main

import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import com.tpa.HelepDoc.R
import com.tpa.HelepDoc.adapters.DoctorRecommendAdaptor
import com.tpa.HelepDoc.models.Doctor
import java.util.*


class HomeActivity : AppCompatActivity() {

    private lateinit var rvDoctor: RecyclerView
    private lateinit var doctorAdapter: DoctorRecommendAdaptor
    private var doctors: Vector<Doctor> = Vector()
    private var rec: Vector<Doctor> = Vector()
    val docRef =  FirebaseDatabase.getInstance().getReference("doctors")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        rvDoctor = findViewById(R.id.rv_doctor_rec)

        docRef.addChildEventListener(object: ChildEventListener {
            override fun onCancelled(dataSnapShot: DatabaseError) {
            }

            override fun onChildMoved(p0: DataSnapshot, p1: String?) {
                updateView()
                rec.clear()
                initDoctor()
            }

            override fun onChildChanged(p0: DataSnapshot, p1: String?) {
                val doc = p0.getValue(Doctor::class.java)
                doctors.set(getIndex(doc as Doctor), doc)
                updateView()
                rec.clear()
                initDoctor()
            }

            override fun onChildAdded(p0: DataSnapshot, p1: String?) {
                val doc = p0.getValue(Doctor::class.java)
                doctors.add(doc!!)
                updateView()
                rec.clear()
                initDoctor()
            }

            override fun onChildRemoved(p0: DataSnapshot) {
                val doc = p0.getValue(Doctor::class.java)
                doctors.removeAt(getIndex(doc as Doctor))
                updateView()
                rec.clear()
                initDoctor()
            }

        })

        doctorAdapter = DoctorRecommendAdaptor(rec, this)
    }

    public fun updateView() {
        rvDoctor.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@HomeActivity, LinearLayoutManager.HORIZONTAL, false)
            adapter = doctorAdapter
        }
    }

    private fun initDoctor() {
        Collections.sort(doctors, DoctorComparator())
        var i = 0
        for(d in doctors) {
            if(i == 5) break
            rec.add(d)
            i++
        }
    }

    class DoctorComparator : Comparator<Doctor?> {
        override fun compare(p0: Doctor?, p1: Doctor?): Int {
            return ((p1 as Doctor).rating/(p1 as Doctor).count_rate).compareTo(((p0 as Doctor).rating/(p0 as Doctor).count_rate))
        }
    }

    private fun getIndex(doc: Doctor): Int {
        var i = 0
        for(d in doctors) {
            if(d.equals(doc)) return i
            i++
        }
        return 0
    }
}
