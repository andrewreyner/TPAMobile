package com.tpa.HelepDoc.models

<<<<<<< HEAD
class Product(
    id: String?, type:String,
    name: String, indicator:String, attention: String, dosage:String, composition:String, price:Float, image:String){
=======
class Product(id: String?, type:String,
    name: String, indicator:String, attention: String, dosage:String, composition:String, price:Double, image:String, rate:Double){
>>>>>>> upstream/master
    var id:String? = id
    var type:String = type
    var name:String = name
    var indicator:String = indicator
    var attention:String = attention
    var dosage:String = dosage
    var composition:String = composition
    var price:Float= price
    var image:String = image
<<<<<<< HEAD
    var rating: Float = 0.0f
    var count_rate: Int = 0
    constructor() : this("", "", "","", "", "" ,"" ,0.0f, "")
=======

    // read data from firebase must be initialize the constructor
    constructor() : this("", "", "","", "", "" ,"" ,0.0, "", 0.0)
>>>>>>> upstream/master

}

