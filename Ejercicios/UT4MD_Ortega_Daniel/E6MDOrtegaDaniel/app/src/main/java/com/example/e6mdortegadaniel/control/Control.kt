package com.example.e6mdortegadaniel.control

import android.os.Parcel
import android.os.Parcelable
//TODO, cambiar a plugin
class Control() : Parcelable
{
    var vehiculos=mutableListOf<Vehiculo>(
        Vehiculo("","","","","")
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