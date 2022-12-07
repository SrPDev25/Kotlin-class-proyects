package com.example.e5mdortegamartnezdaniel.control

import android.os.Parcel
import android.os.Parcelable

/**
 * Contiene la información de las epecialidades y si fuera el caso los candidatos,
 * para mover la información entre actividades
 */

class Control():Parcelable {
    //TODO map de inscritos y clase inscritos
    var especialidades=mutableListOf<Especialidad>()
    init{
        chargeEspecialidades()
        eliminarEspecialidadesVacias()
    }

    constructor(parcel: Parcel) : this() {
        chargeEspecialidades()
        eliminarEspecialidadesVacias()
    }

    /**
     * Carga las epecialidades cargadas por defecto
     */
    fun chargeEspecialidades(){
        especialidades= mutableListOf<Especialidad>()
        especialidades.add(Especialidad(12,
            "Urologo",
            5))
        /*especialidades.get(0).start(
            12,
            "Urologo",
            5
        )*/
        especialidades.add(Especialidad(
            13,
            "Cardiologo",
            1))

        especialidades.add(Especialidad(
            3,
            "Prueba3",
            1
        ))

        especialidades.add(Especialidad(
            1,
            "Prueba1",
            0
        ))

    }

    /**
     * Elimina las epecialidades vacías al cargar
     */
    fun eliminarEspecialidadesVacias(){
        for(i in especialidades){
            if (i.numPlazasDisponibles==0){
                especialidades.remove(i)
            }
        }
    }

    /**
     * Elimina la plaza indicada, si la especialidad pasa a 0 plazas, es eliminada
     */
    fun eliminarPlaza(numEspecialidad:Int){
        for(i in especialidades)
            if (i.codigo==numEspecialidad) {
                i.numPlazasDisponibles--
                //Si pasa a 0 plazas la especialidad es eliminada
                if (i.numPlazasDisponibles==0){
                    especialidades.remove(i)
                }
            }
    }

    /**
     * Comprueba que existe esa especialidad
     */
    fun isEspecialidadExist(codigo:Int):Int{
        var resultado= -1
        for (i in especialidades)
            if (i.codigo==codigo)
                resultado=0
        return resultado
    }

    /**
     * Recibe esa especialidad
     */
    fun getEspecialidad(pos:Int)=especialidades.get(pos)
    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(p0: Parcel, p1: Int) {
        p0.writeList(especialidades)

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