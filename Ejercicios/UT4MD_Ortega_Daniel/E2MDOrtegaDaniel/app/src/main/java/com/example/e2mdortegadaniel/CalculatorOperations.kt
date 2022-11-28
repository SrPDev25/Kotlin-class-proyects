package com.example.e2mdortegadaniel

class CalculatorOperations {

    val divisionOperation=0
    val timesOperation=1
    val minusOperation=2
    val plusOperation=3

    /**
     * Suma
     */
    private fun sum(number1: String, number2: String): String {
        val operator1: Double = number1.toDouble()
        val operator2: Double = number2.toDouble()
        return (operator1 + operator2).toString()
    }

    /**
     * Resta
     */
    private fun minus(number1:String, number2:String):String{
        val operator1:Double=number1.toDouble()
        val operator2:Double=number2.toDouble()
        return (operator1-operator2).toString()
    }

    /**
     * Multiplicación
     */
    private fun times(number1:String, number2:String):String{
        val operator1:Double=number1.toDouble()
        val operator2:Double=number2.toDouble()
        return (operator1*operator2).toString()
    }

    /**
     * División
     */
    private fun division(number1:String, number2:String):String{
        val operator1:Double=number1.toDouble()
        val operator2:Double=number2.toDouble()
        return (operator1/operator2).toString()
    }

    /**
     * Indice de operaciones
     */
    fun operation(number1:String, number2:String, operator:Int):String{
        var solution=""

        when (operator) {
            plusOperation -> {
                solution=sum(number1,number2)
            }
            minusOperation -> {
                solution=minus(number1,number2)
            }
            timesOperation -> {
                solution=times(number1,number2)
            }
            divisionOperation -> {
                solution=division(number1,number2)
            }
        }

        return solution
    }



}