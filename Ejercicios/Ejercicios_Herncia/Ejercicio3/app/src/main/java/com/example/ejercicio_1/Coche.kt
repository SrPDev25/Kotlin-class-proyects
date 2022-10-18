package com.example.ejercicio_1


class Coche(modelo:String, color:String, marca:String, nPuertas: Int, nPlazas:Int): Vehiculo(modelo, color, marca) {
    var nPuertas:Int=3
        set(newField:Int){
            field = newField
        }
    var nPlazas:Int=1
        set(newField:Int){
            field = newField
        }


}