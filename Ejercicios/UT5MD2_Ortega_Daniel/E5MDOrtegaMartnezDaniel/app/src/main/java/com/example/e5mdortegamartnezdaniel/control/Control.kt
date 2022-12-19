package com.example.e5mdortegamartnezdaniel.control

import android.os.Parcel
import android.os.Parcelable

/**
 * Contiene la información de las epecialidades y si fuera el caso los candidatos,
 * para mover la información entre actividades
 */

class Control():Parcelable {
    var especialidades = mutableListOf<Especialidad>    ()
    private var usuarios = mutableListOf<String>()

    init {
        chargeEspecialidades()
        eliminarEspecialidadesVacias()
    }

    constructor(parcel: Parcel) : this() {
        chargeEspecialidades()
        eliminarEspecialidadesVacias()
    }
    //-----------------------------------Usuario
    /**
     * Comprueba que el usuairo existe
     */
    fun checkUsuario(dni: String): Int {
        return usuarios.indexOf(dni)
    }

    fun addUsuario(dni: String) {
        usuarios.add(dni)
    }

    //----------------------------------Especialidades
    //-------------Carga
    /**
     * Carga las epecialidades cargadas por defecto
     */
    private fun chargeEspecialidades() {
        especialidades = mutableListOf()
        especialidades.add(
            Especialidad(
                12,
                "Urologo",
                5
            )
        )
        especialidades.add(
            Especialidad(
                13,
                "Cardiologo",
                1
            )
        )

        especialidades.add(
            Especialidad(
                3,
                "Prueba3",
                1
            )
        )

        especialidades.add(
            Especialidad(
                1,
                "Prueba1",
                0
            )
        )

    }

    /**
     * Elimina las epecialidades vacías al cargar
     */
    private fun eliminarEspecialidadesVacias() {
        for (i in especialidades) {
            if (i.numPlazasDisponibles == 0) {
                especialidades.remove(i)
            }
        }
    }


    //-------------------------------Parcelamiento
    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(p0: Parcel, p1: Int) {
        p0.writeList(especialidades)
        p0.writeList(usuarios)
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

