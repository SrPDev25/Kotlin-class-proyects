package com.example.e2mdortegadaniel

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.e2mdortegadaniel.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var numberOnOperation = "0"
    private var numberLastOperation = "0"
    private var operation: Int = -1
    private var isStarted = false//Indica si hay algun número insertado o hay 0
    private var isCommed = false
    private var control = CalculatorOperations()
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /**
         * Insert a new number into the textView
         */
        fun insertNumber(number: String) {
            if (!isStarted) {//Comprueba si el numero es 0 o tiene que añadirse un nueveo numero
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
            val numberChar = numberOnOperation.toCharArray()
            if (numberChar.size == 1) {
                numberOnOperation = "0"
                isStarted = false
            } else {
                numberOnOperation = String(numberChar.dropLast(1).toCharArray())
            }
            binding.numberTextView.text = numberOnOperation
        }



        /**
         * Switch positive and negative numbers
         */
        fun switchSimbol() {
            val textChar = numberOnOperation.toList()
            //Comprueba si ya tiene un -
            numberOnOperation = if (textChar[0] != "-".toList()[0]) {
                "-$numberOnOperation"
            } else {
                String(textChar.drop(1).toCharArray())
            }
            binding.numberTextView.text = numberOnOperation
        }

        /**
         * Reset just the operations
         */
        fun setOperation(newOperation: Int) {
            operation = newOperation
            isStarted = false
            isCommed = false
        }

        fun markOperation() {
            if (operation == control.minusOperation) {
                binding.buttonMinus.setBackgroundColor(Color.parseColor("#41039e"))
            } else {
                binding.buttonMinus.setBackgroundColor(Color.parseColor("#1F1F1F"))
            }

            if (operation == control.plusOperation) {
                binding.buttonPlus.setBackgroundColor(Color.parseColor("#41039e"))
            } else {
                binding.buttonPlus.setBackgroundColor(Color.parseColor("#1F1F1F"))
            }

            if (operation == control.timesOperation) {
                binding.buttonTimes.setBackgroundColor(Color.parseColor("#41039e"))
            } else {
                binding.buttonTimes.setBackgroundColor(Color.parseColor("#1F1F1F"))
            }

            if (operation == control.divisionOperation) {
                binding.buttonDivision.setBackgroundColor(Color.parseColor("#41039e"))
            } else {
                binding.buttonDivision.setBackgroundColor(Color.parseColor("#1F1F1F"))
            }

        }

        /**
         * Reset the calculator
         */
        fun resetAll() {
            numberOnOperation = "0"
            numberLastOperation = "0"
            operation = -1
            isStarted = false//Indica si hay algun número insertado o hay 0
            isCommed = false
            binding.numberTextView.text="0"
            markOperation()//Solo se pueden llamar los métodos de arriba a abajo
        }

        fun startOperation(newOperation: Int) {
            val hola= binding.numberTextView.text[binding.numberTextView.text.length-1]
            //Comprueba que no acaba en "."
            if (hola!='.') {
                //Cuando tiene ya una operación lista
                if (operation != -1 && numberOnOperation != "0") {
                    numberOnOperation=binding.numberTextView.text.toString()
                    numberOnOperation =
                        control.operation(numberLastOperation, numberOnOperation, operation)
                    binding.numberTextView.text = numberOnOperation
                    numberLastOperation = numberOnOperation
                    setOperation(newOperation)
                    markOperation()
                    //Cuando se indico un numero y no se indico anteriormente una operación
                } else if (operation == -1 && numberOnOperation != "0") {
                    setOperation(newOperation)//Indica el nuevo operador
                    markOperation()//Marca el operador en uso
                    numberLastOperation=numberOnOperation
                }  else {
                    setOperation(newOperation)
                    markOperation()
                }
            }

        }


        fun startEquals() {
            if (operation != -1) {
                startOperation(operation)
                operation = -1
            }
            markOperation()
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
            if (!isCommed && (numberOnOperation == "0" || !isStarted)) {
                insertNumber("0.")
                isCommed = true
            } else if (!isCommed) {
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