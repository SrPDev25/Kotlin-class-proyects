package com.example.ejercicio_1

fun main(){
    var vehiculos= arrayListOf<Vehiculo>(
        Vehiculo("","Verde","Camion generico"),
        Vehiculo("","Negro","Nissan"),
        Vehiculo("Nisu","","Nisu"),
        Moto("","Rosa","Motomami", true, 1),
        Moto("390 Duke","Naranja-Negra","KTM", false, 2),
        Coche("Tanque","Feo","SAAB",5,5),
        Coche("Celica","Ninguno, ya que no lo tiene", "Toyota",3,4)
    )
    for (x in vehiculos){
        mostrarVehiculo(x)
    }
}

fun mostrarVehiculo(x:Vehiculo):Unit{
    if (!(x is Moto) && !(x is Coche)) {
        println("-------------------")
        println("marca: ${x.marca}")
        println("color: ${x.color}")
        println("modelo: ${x.modelo}")
    } else if (x is Moto) {
        println("-------------------")
        println("marca: ${x.marca}")
        println("color: ${x.color}")
        println("modelo: ${x.modelo}")
        println("Plazas: ${x.nPlazas}")
        println("Maletero: ${x.maletero}")
    } else if (x is Coche){
        println("-------------------")
        println("marca: ${x.marca}")
        println("color: ${x.color}")
        println("modelo: ${x.modelo}")
        println("Plazas: ${x.nPlazas}")
        println("Maletero: ${x.nPuertas}")
    }
}