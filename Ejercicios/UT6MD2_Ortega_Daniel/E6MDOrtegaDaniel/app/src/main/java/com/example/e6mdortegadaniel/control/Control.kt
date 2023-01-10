package com.example.e6mdortegadaniel.control

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class Control(
    var  vehiculos:MutableList<Vehiculo>
) : Parcelable
{

}