package com.example.ejercicio_1

fun main(){
    var vehiculos= arrayListOf<Vehiculo>()
    vehiculos.add(Vehiculo())
    vehiculos.get(0).marca="Nissan"
    vehiculos.add(Vehiculo())
    vehiculos.get(1).marca="Motomami"
    vehiculos.add(Vehiculo())
    vehiculos.get(2).marca="Nisu"
    for (x in vehiculos){
        x.color=""
        x.modelo=""
        mostrarVehiculo(x)
    }
}

fun mostrarVehiculo(x:Vehiculo):Unit{
    println("-------------------")
    println("marca: ${x.marca}")
    println("color: ${x.color}")
    println("modelo: ${x.modelo}")
}