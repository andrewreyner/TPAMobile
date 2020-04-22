package com.tpa.HelepDoc.adapters

import android.app.AlertDialog
import android.content.Context
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.FirebaseDatabase
import com.tpa.HelepDoc.R
import com.tpa.HelepDoc.adapters.viewHolders.DoctorViewHolder
import com.tpa.HelepDoc.models.Doctor
import kotlinx.android.synthetic.main.activity_register.view.*
import kotlinx.android.synthetic.main.doctor_dialog.view.*
import java.math.BigDecimal
import java.math.RoundingMode
import java.util.*


class DoctorAdapter(val doctors: Vector<Doctor>, val cont: Context): RecyclerView.Adapter<DoctorViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DoctorViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.doctor_card, parent, false)
        return DoctorViewHolder(view)
    }

    override fun getItemCount(): Int {
        return doctors.size
    }

    override fun onBindViewHolder(holder: DoctorViewHolder, position: Int) {
        val doctor: Doctor = doctors[position]
        if(doctor.picture.equals(""))
            holder.ivDoctorPicture.setImageResource(R.drawable.doctor)
        else
            holder.ivDoctorPicture.setImageURI(Uri.parse(doctor.picture))
        holder.tvDoctorFullname.text = "Fullname: " + doctor.fullname
        holder.tvDoctorSpecialist.text = "Specialist: " + doctor.specialist
        holder.tvDoctorRating.text = "Rating: " + (doctor.rating/doctor.count_rate).toString() + " stars (" + doctor.count_rate + ")"
        holder.tvDoctorFee.text = "Fee: IDR " + doctor.fee.toString()

        holder.itemView.setOnClickListener {
            var dialog = LayoutInflater.from(cont).inflate(R.layout.doctor_dialog, null)
            var builder = AlertDialog.Builder(cont).setView(dialog)
            var alert = builder.show()
            if(doctor.picture.equals(""))
                dialog.doctor_picture.setImageResource(R.drawable.doctor)
            else
                dialog.doctor_picture.setImageURI(Uri.parse(doctor.picture))
            dialog.doctor_fullname.text = "Fullname: " + doctor.fullname
            dialog.doctor_gender.text = "Gender: " + doctor.gender
            dialog.doctor_specialist.text = "Specialist: " + doctor.specialist
            dialog.doctor_fee.text = "Fee: IDR " + doctor.fee
            dialog.doctor_email.text = "Email: " + doctor.email
            dialog.doctor_phoneNumber.text = "Phone Number: " + doctor.phoneNumber
            var rate = Math.round(doctor.rating/doctor.count_rate * 10) / 10
            dialog.doctor_rating.text = "Rating: " + (rate).toString() + " stars (" + doctor.count_rate + " review(s))"
            dialog.return_from_dialog.setOnClickListener {
                alert.dismiss()
                if(dialog.doctor_star.rating != 0f) {
                    var doctorRef = FirebaseDatabase.getInstance().getReference("doctors")
                    doctor.rating += dialog.doctor_star.rating
                    doctor.count_rate++
                    doctorRef.child(doctor.id as String).child("rating").setValue(doctor.rating)
                    doctorRef.child(doctor.id as String).child("count_rate").setValue(doctor.count_rate)
                }
            }
        }
    }

}
