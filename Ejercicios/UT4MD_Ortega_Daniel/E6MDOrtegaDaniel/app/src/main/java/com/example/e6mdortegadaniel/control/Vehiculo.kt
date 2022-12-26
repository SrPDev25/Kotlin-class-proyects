package com.example.e6mdortegadaniel.control

//Todo hacer parcelable el vehiculo
data class Vehiculo(
    var dni:String,
    var nombre:String,
    var email:String,
    var matricula:String,
    var modelo:String,
    var estado:Boolean=false,
    val observaciones:String="",
    var fecha:String=""
) {


    /**
     * Cambia el estado, pero si ya está en true devuelve -1 y no cambia nada
     * @return 0>cambiado 1>es true
     */
    fun switchEstado():Int{
        if (!estado){
            estado=true
            return 0
        }else{
            return -1
        }

    }
}