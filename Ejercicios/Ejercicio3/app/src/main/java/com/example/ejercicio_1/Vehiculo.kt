package com.example.ejercicio_1

open class Vehiculo() {
    var marca:String=""
        get() =field
        set(newField:String){
            field = (if (newField.isEmpty()) "Sin marca" else newField)
        }
    var modelo:String=""
        get() =field
        set(newField:String){
            field = (if (newField.isEmpty()) "Sin modelo" else newField)
        }
    var color:String=""
        get() =field
        set(newField:String){
            field = (if (newField.isEmpty()) "Sin color" else newField)
        }

    constructor(modelo:String, color:String, marca:String): this() {
        this.marca = marca
        this.color = color
        this.modelo = modelo
    }


}