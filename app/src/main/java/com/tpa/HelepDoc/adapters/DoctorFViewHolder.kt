package com.tpa.HelepDoc.adapters

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tpa.HelepDoc.R

class DoctorFViewHolder(view: View): RecyclerView.ViewHolder(view) {
    var ivDoctor:ImageView = view.findViewById(R.id.iv_doctor)
    var tvName: TextView = view.findViewById(R.id.tv_name)
    var tvSpecialist:TextView = view.findViewById(R.id.tv_specialist)





}
