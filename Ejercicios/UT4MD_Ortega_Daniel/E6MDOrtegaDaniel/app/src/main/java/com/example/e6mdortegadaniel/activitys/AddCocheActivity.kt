package com.example.e6mdortegadaniel.activitys

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.e6mdortegadaniel.control.Control
import com.example.e6mdortegadaniel.control.Vehiculo
import com.example.e6mdortegadaniel.databinding.ActivityAddCocheBinding
import java.util.Date

class AddCocheActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddCocheBinding
    private var control = Control()
    companion object {
        const val NO_ERROR = -1
        const val ERROR_NO_NUMBERS=0
        const val ERROR_LETTER=1
        const val EMPTY_TEXT=5
    }
    private var errors= arrayListOf<Boolean>(true,true,true,true,true,true,true)
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

        binding.dniText.addTextChangedListener(watcherDNI)
        binding.nombreText.addTextChangedListener(watcherNombre)
        binding.emailText.addTextChangedListener(watcherEmail)


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

    private val watcherDNI: TextWatcher = object : TextWatcher {
        override fun beforeTextChanged(
            s: CharSequence?,
            start: Int,
            count: Int,
            after: Int
        ) {

        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

        }

        override fun afterTextChanged(s: Editable?) {
            val text=binding.dniText.text.toString()

            if (text.length==9) {
                when (calcularDNI(text)) {
                    ERROR_NO_NUMBERS -> {
                        binding.nombreLayout.error = "No se cumple el formato 11111111A"
                        errors[1]=true
                    }
                    ERROR_LETTER -> {
                        binding.nombreLayout.error = "DNI incorrecto"
                        errors[1]=true
                    }
                    NO_ERROR -> {
                        binding.nombreLayout.error = ""
                        errors[1]=false
                    }
                }

            }else{
                errors[1]=true
            }

        }

    }

    private val watcherNombre: TextWatcher = object : TextWatcher {
        override fun beforeTextChanged(
            s: CharSequence?,
            start: Int,
            count: Int,
            after: Int
        ) {

        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

        }

        override fun afterTextChanged(s: Editable?) {
            val text=binding.nombreText.text.toString()

            if(text.isBlank()){
                binding.dniLayout.error = "Dato obligatório"
                errors[0]=true
            }else{
                binding.dniLayout.error = ""
                errors[0]=false
            }



        }

    }

    private val watcherEmail: TextWatcher = object : TextWatcher {
        override fun beforeTextChanged(
            s: CharSequence?,
            start: Int,
            count: Int,
            after: Int
        ) {

        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

        }

        override fun afterTextChanged(s: Editable?) {
            val text=binding.emailText.text.toString()
            var check=text.split("@")
            if(check.size!=2){
                binding.emailLayout.error = "Formato no válido"
                errors[2]=true
            }else{
                check=check[1].split(".")
                if (check.size!=2){
                    binding.emailLayout.error = "Formato no válido"
                    errors[2]=true
                }else{
                    binding.emailLayout.error = ""
                    errors[2]=false
                }

            }



        }

    }
}