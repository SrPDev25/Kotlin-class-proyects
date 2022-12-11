package com.example.e5mdortegamartnezdaniel.control

import android.os.Parcel
import android.os.Parcelable


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

    constructor(codigo:Int):this(){
        this.codigo=codigo
    }

    constructor(codigo: Int, nombre: String, numPlazasDisponibles:Int) : this() {
        this.codigo=codigo
        this.nombre=nombre
        this.numPlazasDisponibles=numPlazasDisponibles
    }

    //Constructor obligatorio de Parcelable
    constructor(parcel: Parcel) : this(){

    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {

    }

    override fun describeContents(): Int {
        return 0//No hay descripcion
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Especialidad

        if (codigo != other.codigo) return false

        return true
    }

    override fun hashCode(): Int {
        return codigo
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