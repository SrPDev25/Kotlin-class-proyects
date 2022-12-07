package com.example.e5mdortegamartnezdaniel.control

import android.os.Parcel
import android.os.Parcelable
import kotlinx.android.parcel.Parceler


class Especialidad(): Parcelable {



    var codigo=0
        get(){
            return field
        }
    var nombre=""
        get(){
            return field
        }
    var numPlazasDisponibles=0
        get(){
            return field
        }



    init{

    }
    constructor(parcel: Parcel) : this(){

    }
    fun start(codigo: Int, nombre: String, numPlazasDisponibles:Int){
        this.codigo=codigo
        this.nombre=nombre
        this.numPlazasDisponibles=numPlazasDisponibles
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Especialidad> {
        override fun createFromParcel(parcel: Parcel): Especialidad {
            return Especialidad(parcel)
        }

        override fun newArray(size: Int): Array<Especialidad?> {
            return arrayOfNulls(size)
        }
    }




}