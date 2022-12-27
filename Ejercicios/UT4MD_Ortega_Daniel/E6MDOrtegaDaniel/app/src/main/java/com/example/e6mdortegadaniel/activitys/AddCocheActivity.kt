package com.example.e6mdortegadaniel.activitys

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.e6mdortegadaniel.control.Control
import com.example.e6mdortegadaniel.control.Vehiculo
import com.example.e6mdortegadaniel.databinding.ActivityAddCocheBinding
import java.util.Date

class AddCocheActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddCocheBinding
    private lateinit var linearLayout: LinearLayoutManager
    private var control = Control()
    companion object {
        const val NO_ERROR = -1
        const val ERROR_NO_NUMBERS=0
        const val ERROR_LETTER=1
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddCocheBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.addButton.setOnClickListener(){
            val myIntent= Intent()
                .putExtra("vehiculo", Vehiculo("112233S","Pepa","fdsa@gmail.com","118545237CDS","Stopa"))
            setResult(Activity.RESULT_OK,myIntent)
            finish()
        }



    }

    private fun clean(){
        binding.dniText.text?.clear()
        binding.nombreText.text?.clear()
        binding.modeloText.text?.clear()
        binding.matriculaText.text?.clear()
        binding.observacionesText.text?.clear()

    }

    /**
     * Calcula si el DNI es válido
     */
    fun calcularDNI(dni:String):Int{
        var isFine = NO_ERROR
        val letters= arrayOf('T','R','W','A','G','Y','F','O','D','X','B','N','J','Z','S','Q','V','H','L','C','K')
        var count=0
        //Comprueba que los 8 primeros caracteres no sean letras
        while(count<8&&isFine== NO_ERROR) {
            if (dni[count].isLetter()) {
                isFine = ERROR_NO_NUMBERS
            }
            count++
        }
        //Comprueba que la letra es la que devería ser
        if (isFine!= ERROR_NO_NUMBERS){
            val dniLetter= dni[8].uppercaseChar()
            var dniNumber=""
            for (i in 0..7)
                dniNumber+=dni[i]
            val number:Int =(dniNumber.toInt()%23)-1
            if (letters[number] != dniLetter)
                isFine= ERROR_LETTER
        }

        return isFine
    }
}