package com.example.e2mdortegadaniel

class CalculatorOperations {

    public val divisionOperation=0
    public val timesOperation=1
    public val minusOperation=2
    public val plusOperation=3

    init {

    }

    private fun sum(number1:String, number2:String):String{
        var operator1:Double=number1.toDouble()
        var operator2:Double=number2.toDouble()
        var solution:String=""

        solution= (operator1+operator2).toString()

        return solution
    }

    private fun minus(number1:String, number2:String):String{
        var operator1:Double=number1.toDouble()
        var operator2:Double=number2.toDouble()
        var solution:String=""

        solution= (operator1-operator2).toString()

        return solution
    }

    private fun times(number1:String, number2:String):String{
        var operator1:Double=number1.toDouble()
        var operator2:Double=number2.toDouble()
        var solution:String=""

        solution= (operator1*operator2).toString()

        return solution
    }

    private fun division(number1:String, number2:String):String{
        var operator1:Double=number1.toDouble()
        var operator2:Double=number2.toDouble()
        var solution:String=""

        solution= (operator1/operator2).toString()

        return solution
    }

    public fun operation(number1:String, number2:String, operator:Int):String{
        var solution=""

        if (operator==plusOperation){
            solution=sum(number1,number2)
        }else if (operator==minusOperation){
            solution=minus(number1,number2)
        }else if (operator==timesOperation){
            solution=times(number1,number2)
        }else if (operator==divisionOperation){
            solution=division(number1,number2)
        }

        return solution
    }



}