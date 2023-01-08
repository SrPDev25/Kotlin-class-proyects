package com.example.e6mdortegadaniel.activitys

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import com.example.e6mdortegadaniel.control.Vehiculo
import com.example.e6mdortegadaniel.databinding.ActivityAddCocheBinding

class AddCocheActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddCocheBinding

    companion object {
        const val NO_ERROR = -1
        const val ERROR_NO_NUMBERS = 0
        const val ERROR_LETTER = 1
    }

    private var errors = arrayListOf(true, true, true, true, true, true)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddCocheBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.addButton.isEnabled = false
        binding.datePicker.minDate= System.currentTimeMillis()
        binding.addButton.setOnClickListener() {
            val date="${binding.datePicker.dayOfMonth}/${binding.datePicker.month+1}/${binding.datePicker.year}"
            val myIntent = Intent()
                .putExtra(
                    "vehiculo",
                    Vehiculo(
                        binding.dniText.toString(), binding.nombreText.toString(),
                        binding.emailText.toString(), binding.matriculaText.toString(),
                        binding.modeloText.toString(),binding.observacionesText.toString(),
                        date
                    )
                )
            setResult(Activity.RESULT_OK, myIntent)
            finish()

        }

        binding.dniText.addTextChangedListener(watcherDNI)
        binding.nombreText.addTextChangedListener(watcherNombre)
        binding.emailText.addTextChangedListener(watcherEmail)
        binding.modeloText.addTextChangedListener(watcherModelo)
        binding.matriculaText.addTextChangedListener(watcherMatricula)
        binding.observacionesText.addTextChangedListener(watcherObservaciones)


    }

    private fun checkErrors() {
        var i = 0
        while (i != -1 && i < errors.size) {
            if (errors[i])
                i = -2
            i++
        }
        binding.addButton.isEnabled = i != -1
    }

    /**
     * Calcula si el DNI es válido
     */
    fun calcularDNI(dni: String): Int {
        var isFine = NO_ERROR
        val letters = arrayOf(
            'T',
            'R',
            'W',
            'A',
            'G',
            'Y',
            'F',
            'O',
            'D',
            'X',
            'B',
            'N',
            'J',
            'Z',
            'S',
            'Q',
            'V',
            'H',
            'L',
            'C',
            'K'
        )
        var count = 0
        //Comprueba que los 8 primeros caracteres no sean letras
        while (count < 8 && isFine == NO_ERROR) {
            if (dni[count].isLetter()) {
                isFine = ERROR_NO_NUMBERS
            }
            count++
        }
        //Comprueba que la letra es la que devería ser
        if (isFine != ERROR_NO_NUMBERS) {
            val dniLetter = dni[8].uppercaseChar()
            var dniNumber = ""
            for (i in 0..7)
                dniNumber += dni[i]
            val number: Int = (dniNumber.toInt() % 23) - 1
            if (letters[number] != dniLetter)
                isFine = ERROR_LETTER
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
            val text = binding.dniText.text.toString()

            if (text.length == 9) {
                when (calcularDNI(text)) {
                    ERROR_NO_NUMBERS -> {
                        binding.dniLayout.error = "No se cumple el formato 11111111A"
                        errors[0] = true
                    }
                    ERROR_LETTER -> {
                        binding.dniLayout.error = "DNI incorrecto"
                        errors[0] = true
                    }
                    NO_ERROR -> {
                        binding.dniLayout.error = ""
                        errors[0] = false

                    }
                }

            }
            checkErrors()

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
            val text = binding.nombreText.text.toString()

            if (text.isBlank()) {
                binding.nombreLayout.error = "Dato obligatório"
                errors[1] = true
            } else {
                binding.nombreLayout.error = ""
                errors[1] = false

            }
            checkErrors()

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
            val text = binding.emailText.text.toString()
            var check = text.split("@")
            if (check.size != 2) {
                binding.emailLayout.error = "Formato no válido"
                errors[2] = true
            } else {
                check = check[1].split(".")
                if (check.size != 2) {
                    binding.emailLayout.error = "Formato no válido"
                    errors[2] = true
                } else {
                    binding.emailLayout.error = ""
                    errors[2] = false

                }

            }
            checkErrors()

        }

    }
    private val watcherMatricula: TextWatcher = object : TextWatcher {
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
            val text = binding.matriculaText.text.toString()

            if (text.isBlank()) {
                binding.matriculaLayout.error = "Dato obligatório"
                errors[3] = true
            } else {
                binding.matriculaLayout.error = ""
                errors[3] = false

            }
            checkErrors()
        }
    }
    private val watcherModelo: TextWatcher = object : TextWatcher {
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
            val text = binding.modeloText.text.toString()

            if (text.isBlank()) {
                binding.modeloLayout.error = "Dato obligatório"
                errors[4] = true
            } else {
                binding.modeloLayout.error = ""
                errors[4] = false

            }
            checkErrors()
        }
    }
    private val watcherObservaciones: TextWatcher = object : TextWatcher {
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
            val text = binding.observacionesText.text.toString()

            if (text.isBlank()) {
                binding.observacionesLayout.error = "Dato obligatório"
                errors[5] = true
            } else {
                binding.observacionesLayout.error = ""
                errors[5] = false

            }
            checkErrors()
        }
    }
}
