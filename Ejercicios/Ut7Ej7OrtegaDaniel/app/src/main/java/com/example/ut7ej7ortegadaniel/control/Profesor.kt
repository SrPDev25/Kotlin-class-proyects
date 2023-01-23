package com.example.ut7ej7ortegadaniel.control

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Profesor(
    val login:String,
    val pass:String,
    val nombre:String="",
    val codigo:Int=-1
) : Parcelable {
}