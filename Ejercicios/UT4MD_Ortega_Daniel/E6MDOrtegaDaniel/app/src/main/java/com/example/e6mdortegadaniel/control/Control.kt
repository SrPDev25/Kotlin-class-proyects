package com.example.e6mdortegadaniel.control

import android.os.Parcel
import android.os.Parcelable
//TODO, cambiar a plugin
class Control() : Parcelable
{
    var vehiculos=mutableListOf<Vehiculo>(
        Vehiculo("112233S","Juan","asdf@gmail.com","1155357CDS","Seat Panda"),
        Vehiculo("453423G","Juanito","gsdffgd@gmail.com","5858974CDS","BMW",true)
    )
    get() = field

    constructor(parcel: Parcel) : this() {
    }

    init {

    }


    //
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeList(vehiculos)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Control> {
        override fun createFromParcel(parcel: Parcel): Control {
            return Control(parcel)
        }

        override fun newArray(size: Int): Array<Control?> {
            return arrayOfNulls(size)
        }
    }
}