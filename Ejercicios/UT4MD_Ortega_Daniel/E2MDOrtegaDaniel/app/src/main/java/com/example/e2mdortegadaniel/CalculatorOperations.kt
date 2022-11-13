package com.example.e2mdortegadaniel

class CalculatorOperations {

    val divisionOperation=0
    val timesOperation=1
    val minusOperation=2
    val plusOperation=3

    public fun sum(number1:String, number2:String):String{
        var operator1:Double=number1.toDouble()
        var operator2:Double=number2.toDouble()
        var solution:String=""

        solution=number1+number2

        return solution
    }

    public fun operation(number1:String, number2:String, operator:Int):String{
        var solution=""

        if (operator==plusOperation){
            solution=sum(number1,number2)

        }

        return solution
    }



}