package com.example.e2mdortegadaniel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.e2mdortegadaniel.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    var numberOnOperation = "0"
    var numberLastOperation = ""
    var operation: Int = -1
    var isStarted = false
    var isCommed = false
    var control = CalculatorOperations()
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /**
         * Insert a new number into the textView
         */
        fun insertNumber(number: String) {
            if (!isStarted) {
                numberOnOperation = number
                binding.numberTextView.text = numberOnOperation
            } else {
                numberOnOperation += number
                binding.numberTextView.text = numberOnOperation
            }
            isStarted = true
        }

        /**
         * Delete de last digit
         */
        fun deleteLastNumber() {
            var numberChar = numberOnOperation.toCharArray()
            if (numberChar.size == 1) {
                numberOnOperation = "0"
            } else {
                numberOnOperation = String(numberChar.dropLast(1).toCharArray())
            }
            binding.numberTextView.text = numberOnOperation
        }

        /**
         * Reset the calculator
         */
        fun resetAll() {
            numberOnOperation = ""
            operation = -1
            binding.numberTextView.text = "0"
            isStarted = false
            isCommed= false
        }

        /**
         * Switch positive and negative numbers
         */
        fun switchSimbol() {
            var textChar = numberOnOperation.toList()
            //Comprueba si ya tiene un -
            if (textChar[0] != "-".toList()[0]) {
                numberOnOperation = "-$numberOnOperation"
            } else {
                numberOnOperation = String(textChar.drop(1).toCharArray())
            }
            binding.numberTextView.text = numberOnOperation
        }

        fun resetNumber(newOperation: Int) {
            operation = newOperation
            isStarted = false
            isCommed = false
        }

        fun startOperation(newOperation: Int) {
            if (operation != -1 && numberOnOperation != "0") {
                numberOnOperation =
                    control.operation(numberLastOperation, numberOnOperation, operation)
                binding.numberTextView.text = numberOnOperation

                numberLastOperation = numberOnOperation
                resetNumber(newOperation)
            } else if (operation == -1) {

                numberLastOperation = numberOnOperation
                resetNumber(newOperation)
            } else if (operation != -1 && numberOnOperation == "0") {
                resetNumber(newOperation)
            }

        }


        fun startEquals() {
            if (operation != -1) {
                startOperation(operation)
                operation = -1
            }
        }

        binding.button0.setOnClickListener {
            insertNumber("0")
        }
        binding.button1.setOnClickListener {
            insertNumber("1")
        }
        binding.button2.setOnClickListener {
            insertNumber("2")
        }
        binding.button3.setOnClickListener {
            insertNumber("3")
        }
        binding.button4.setOnClickListener {
            insertNumber("4")
        }
        binding.button5.setOnClickListener {
            insertNumber("5")
        }
        binding.button6.setOnClickListener {
            insertNumber("6")
        }
        binding.button7.setOnClickListener {
            insertNumber("7")
        }
        binding.button8.setOnClickListener {
            insertNumber("8")
        }
        binding.button9.setOnClickListener {
            insertNumber("9")
        }
        binding.buttonPoint.setOnClickListener {
            if (!isCommed && (numberOnOperation=="0" || !isStarted) ){
                insertNumber("0.")
                isCommed=true
            }
            else if (!isCommed) {
                insertNumber(".")
                isCommed = true
            }


        }
        binding.buttonDelete.setOnClickListener {
            deleteLastNumber()
        }
        binding.buttonC.setOnClickListener {
            resetAll()
        }
        binding.buttonSwitch.setOnClickListener {
            switchSimbol()
        }
        binding.buttonPlus.setOnClickListener {
            startOperation(control.plusOperation)
        }
        binding.buttonMinus.setOnClickListener {
            startOperation(control.minusOperation)
        }
        binding.buttonTimes.setOnClickListener {
            startOperation(control.timesOperation)
        }
        binding.buttonDivision.setOnClickListener {
            startOperation(control.divisionOperation)
        }
        binding.buttonEquals.setOnClickListener {
            startEquals()
        }
    }
}