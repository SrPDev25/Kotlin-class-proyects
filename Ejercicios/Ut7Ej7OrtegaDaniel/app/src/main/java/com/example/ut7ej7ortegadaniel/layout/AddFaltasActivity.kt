package com.example.ut7ej7ortegadaniel.layout

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import com.example.ut7ej7ortegadaniel.databinding.ActivityAddFaltasBinding
import com.mjpg.basedatos.dao.OperacionesDao

class AddFaltasActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddFaltasBinding
    lateinit var bd:OperacionesDao
    var profesor=-1
    var hora=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddFaltasBinding.inflate(layoutInflater)
        setContentView(binding.root)
        profesor=intent.getIntExtra("Profesor",-1)
        //todo todo
        bd=OperacionesDao(this)
        binding.dataPicker.maxDate=System.currentTimeMillis()
        binding.buttonAnadir.setOnClickListener(){
            comprobar()
        }
        binding.btnSeleccionarAlumno.setOnClickListener(){
            val myIntent = Intent(this, AlumnosActivity::class.java)
                .putExtra("Profesor",profesor)
            startForResult.launch(myIntent)
        }
        binding.button1.setOnClickListener(){
            resetButtonColors()
            binding.button1.setBackgroundColor(Color.parseColor("#FFBB86FC"))
            hora=1
        }
        binding.button2.setOnClickListener(){
            resetButtonColors()
            binding.button2.setBackgroundColor(Color.parseColor("#FFBB86FC"))
            hora=2
        }
        binding.button3.setOnClickListener(){
            resetButtonColors()
            binding.button3.setBackgroundColor(Color.parseColor("#FFBB86FC"))
            hora=3
        }
        binding.button4.setOnClickListener(){
            resetButtonColors()
            binding.button4.setBackgroundColor(Color.parseColor("#FFBB86FC"))
            hora=4
        }
        binding.button5.setOnClickListener(){
            resetButtonColors()
            binding.button5.setBackgroundColor(Color.parseColor("#FFBB86FC"))
            hora=5
        }
        binding.button6.setOnClickListener(){
            resetButtonColors()
            binding.button6.setBackgroundColor(Color.parseColor("#FFBB86FC"))
            hora=6
        }


    }

    private fun comprobar() {
        var justificada = -1
        if (binding.checkbox.isChecked)
            justificada = 1
        else
            justificada = 0
        with(binding) {
            if (codigoAlumno.text != "0" && hora != 0) {
                bd.addFalta(
                    codigoAlumno.text.toString().toInt(),
                    profesor,
                    dataPicker.dayOfMonth.toString() + "/" + dataPicker.month.toString() + "/" + dataPicker.year.toString(),
                    hora.toString(),
                    justificada
                )
            }
        }
        finish()
    }

    private fun resetButtonColors() {
        hora=0
        binding.button1.setBackgroundColor(Color.parseColor("#FFFFFF"))
        binding.button2.setBackgroundColor(Color.parseColor("#FFFFFF"))
        binding.button3.setBackgroundColor(Color.parseColor("#FFFFFF"))
        binding.button4.setBackgroundColor(Color.parseColor("#FFFFFF"))
        binding.button5.setBackgroundColor(Color.parseColor("#FFFFFF"))
        binding.button6.setBackgroundColor(Color.parseColor("#FFFFFF"))
    }

    private val startForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            result->
        if (result.resultCode == Activity.RESULT_OK) {
            val intent: Int? = result.data?.getIntExtra("alumno",0)
            if (intent!=null){
                binding.codigoAlumno.text= intent.toInt().toString()
            }
        }
    }
}