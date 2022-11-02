package com.example.ejercicio_1

class Moto(modelo:String, color:String, marca:String, maletero:Boolean, nPlazas:Int): Vehiculo(modelo, color, marca) {
    var maletero:Boolean=false
        set(newField){
            field=newField
        }

    var nPlazas:Int=1
        set(newField:Int){
            field = newField
        }
}