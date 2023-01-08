package com.example.e6mdortegadaniel.control

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Vehiculo(
    var dni:String,
    var nombre:String,
    var email:String,
    var matricula:String,
    var modelo:String,
    val observaciones:String="",
    var fecha:String="",
    var estado:Boolean=false
) : Parcelable {


}