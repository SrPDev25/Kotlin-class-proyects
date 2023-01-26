package com.example.ut7ej7ortegadaniel.layout

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.ut7ej7ortegadaniel.databinding.ActivityAddFaltasBinding
import com.google.android.material.snackbar.Snackbar
import com.example.ut7ej7ortegadaniel.dataBase.OperacionesDao

class AddFaltasActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddFaltasBinding
    private lateinit var bd: OperacionesDao
    private var profesor = -1
    private var hora = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddFaltasBinding.inflate(layoutInflater)
        setContentView(binding.root)
        profesor = intent.getIntExtra("Profesor", -1)
        bd = OperacionesDao(this)
        binding.dataPicker.maxDate = System.currentTimeMillis()
        binding.buttonAnadir.setOnClickListener {
            insertarFalta()
        }
        binding.btnSeleccionarAlumno.setOnClickListener {
            val myIntent = Intent(this, AlumnosActivity::class.java)
                .putExtra("Profesor", profesor)
            startForResult.launch(myIntent)
        }
        binding.button1.setOnClickListener {
            resetButtonColors()
            binding.button1.setBackgroundColor(Color.parseColor("#FFBB86FC"))
            hora = 1
        }
        binding.button2.setOnClickListener {
            resetButtonColors()
            binding.button2.setBackgroundColor(Color.parseColor("#FFBB86FC"))
            hora = 2
        }
        binding.button3.setOnClickListener {
            resetButtonColors()
            binding.button3.setBackgroundColor(Color.parseColor("#FFBB86FC"))
            hora = 3
        }
        binding.button4.setOnClickListener {
            resetButtonColors()
            binding.button4.setBackgroundColor(Color.parseColor("#FFBB86FC"))
            hora = 4
        }
        binding.button5.setOnClickListener {
            resetButtonColors()
            binding.button5.setBackgroundColor(Color.parseColor("#FFBB86FC"))
            hora = 5
        }
        binding.button6.setOnClickListener {
            resetButtonColors()
            binding.button6.setBackgroundColor(Color.parseColor("#FFBB86FC"))
            hora = 6
        }


    }

    private fun insertarFalta() {
        val justificada: Int = if (binding.checkbox.isChecked)
            1
        else
            0
        with(binding) {
            if (codigoAlumno.text != "0" && hora != 0) {
                val fecha =
                    dataPicker.dayOfMonth.toString() + "/" + dataPicker.month.toString() + "/" + dataPicker.year.toString()
                val alumno = codigoAlumno.text.toString().toInt()
                if (!bd.isFaltaExistente(alumno, fecha, hora.toString())) {
                    bd.addFalta(
                        alumno,
                        profesor,
                        fecha,
                        hora.toString(),
                        justificada
                    )
                    finish()
                } else {
                    if (bd.getCodProfesorFalta(hora.toString(), fecha, alumno) == profesor)
                        Snackbar.make(binding.root, "Falta ya creada", Snackbar.LENGTH_LONG)
                            .show()
                    else {
                        bd.addObservacion(fecha, hora.toString(), alumno, profesor)
                        finish()
                    }

                }
            }
        }

    }

    private fun resetButtonColors() {
        hora = 0
        binding.button1.setBackgroundColor(Color.parseColor("#FFFFFF"))
        binding.button2.setBackgroundColor(Color.parseColor("#FFFFFF"))
        binding.button3.setBackgroundColor(Color.parseColor("#FFFFFF"))
        binding.button4.setBackgroundColor(Color.parseColor("#FFFFFF"))
        binding.button5.setBackgroundColor(Color.parseColor("#FFFFFF"))
        binding.button6.setBackgroundColor(Color.parseColor("#FFFFFF"))
    }

    private val startForResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val intent: Int? = result.data?.getIntExtra("alumno", 0)
                if (intent != null) {
                    binding.codigoAlumno.text = intent.toInt().toString()
                }
            }
        }

}