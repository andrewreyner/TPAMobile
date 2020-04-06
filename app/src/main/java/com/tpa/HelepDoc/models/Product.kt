package com.tpa.HelepDoc.models

<<<<<<< HEAD
class Product(id: String?,
=======
class Product(
    id: String?, type:String,
>>>>>>> upstream/master
    name: String, indicator:String, attention: String, dosage:String, composition:String, price:Double, image:String){
    var id:String? = id
    var type:String = type
    var name:String = name
    var indicator:String = indicator
    var attention:String = attention
    var dosage:String =dosage
    var composition:String = composition
    var price:Double= price
    var image:String = image

    // read data from firebase must be initialize the constructor
    constructor() : this("", "", "","", "", "" ,"" ,0.0, "")

}

