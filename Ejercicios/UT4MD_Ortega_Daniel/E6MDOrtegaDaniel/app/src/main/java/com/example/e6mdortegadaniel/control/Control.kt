package com.example.e6mdortegadaniel.control

import android.os.Parcel
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class Control() : Parcelable
{
    var vehiculos=mutableListOf<Vehiculo>(
        Vehiculo("112233S","Juan","asdf@gmail.com","1155357CDS","Seat Panda"),
        Vehiculo("453423G","Juanito","gsdffgd@gmail.com","5858974CDS","BMW",true)
    )

}