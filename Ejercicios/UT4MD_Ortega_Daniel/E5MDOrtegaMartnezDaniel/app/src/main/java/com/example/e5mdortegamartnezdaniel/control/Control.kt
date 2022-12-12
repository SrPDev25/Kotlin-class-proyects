package com.example.e5mdortegamartnezdaniel.control

import android.content.Intent
import android.os.Parcel
import android.os.Parcelable

/**
 * Contiene la información de las epecialidades y si fuera el caso los candidatos,
 * para mover la información entre actividades
 */

class Control():Parcelable {
    var especialidades = mutableListOf<Especialidad>()
    var usuarios = mutableListOf<String>()

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
    fun chargeEspecialidades() {
        especialidades = mutableListOf<Especialidad>()
        especialidades.add(
            Especialidad(
                12,
                "Urologo",
                5
            )
        )
        /*especialidades.get(0).start(
            12,
            "Urologo",
            5
        )*/
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
    fun eliminarEspecialidadesVacias() {
        for (i in especialidades) {
            if (i.numPlazasDisponibles == 0) {
                especialidades.remove(i)
            }
        }
    }

    //----------Control
    /**
     * Elimina la plaza indicada, si la especialidad pasa a 0 plazas, es eliminada
     */
    fun eliminarPlaza(numEspecialidad: Int) {
        val especialidadToChange = especialidades.get(
            especialidades.indexOf(
                Especialidad(numEspecialidad)
            )
        )
        especialidadToChange.numPlazasDisponibles--
        //Si se acaban las plazas se elimina la especialidad
        if (especialidadToChange.numPlazasDisponibles == 0)
            especialidades.remove(especialidadToChange)

    }

    /**
     * Comprueba que existe esa especialidad
     */
    fun isEspecialidadExist(codigo: Int): Int {
        var resultado = -1
        for (i in especialidades)
            if (i.codigo == codigo)
                resultado = 0
        return resultado
    }

    /**
     * Recibe esa especialidad
     */
    fun getEspecialidad(pos: Int) = especialidades.get(pos)


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

