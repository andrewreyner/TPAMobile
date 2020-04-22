package com.tpa.HelepDoc.models

class Doctor (id: String?, fullname: String, email: String, phoneNumber: String, password: String, gender: String, specialist: String, fee: Float){
    var id:String? = id
    var fullname: String = fullname
    var email: String = email
    var phoneNumber: String = phoneNumber
    var password: String = password
    var gender: String = gender
    var specialist: String = specialist
    var fee: Float = fee
    var picture: String = ""
    var rating: Float = 0.0f
    var count_rate: Int = 0
    constructor() : this("","","","", "","","",0.0f)
}
