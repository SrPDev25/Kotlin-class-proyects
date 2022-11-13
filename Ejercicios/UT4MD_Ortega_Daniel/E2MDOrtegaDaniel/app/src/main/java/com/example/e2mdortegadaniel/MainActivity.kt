package com.example.e2mdortegadaniel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.e2mdortegadaniel.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    var numberOnOperation: String =""
    var operations :Int = -1
    var isStarted =false
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /**
         * Insert a new number into the textView
         */
        fun insertNumber(number:String){
            if (isStarted){
                binding.numberTextView.text=number
            }else{
            numberOnOperation += number
            binding.numberTextView.text=numberOnOperation
            }
        }

        /**
         * Delete de last digit
         */
        fun deleteLastNumber(){
            var numberChar=numberOnOperation.toCharArray()
            numberChar.dropLast(2)
            numberOnOperation= String(numberChar.dropLast(1).toCharArray())
            binding.numberTextView.text=numberOnOperation
        }

        /**
         * Reset the calculator
         */
        fun resetAll(){
            numberOnOperation=""
            operations=-1
            binding.numberTextView.text=""
        }

        /**
         * Switch positive and negative numbers
         */
        fun switchSimbol(){
            var textChar=numberOnOperation.toList()
            //Comprueba si ya tiene un -
            if (textChar[0] != "-".toList()[0]){
                numberOnOperation= "-$numberOnOperation"
            }else{
                numberOnOperation=String(textChar.drop(1).toCharArray())
            }
            binding.numberTextView.text=numberOnOperation
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
            insertNumber(",")
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

    }
}