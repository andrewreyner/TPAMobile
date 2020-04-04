package com.tpa.HelepDoc.models

class User (id: String?, fullname: String, gender: String, dob: String, email: String, password: String, phoneNumber: String, balance: Float){
    var fullname: String = fullname
    var gender: String = gender
    var dob: String = dob
    var email: String = email
    var password: String = password
    var phoneNumber: String = phoneNumber
    var balance: Float = balance

    constructor() : this("","","","","","","",0.0f)
}