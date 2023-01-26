package com.example.ut7ej8ortegamartinezdaniel.layout

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.ut7ej7ortegadaniel.dataBase.OperacionesDao
import com.example.ut7ej8ortegamartinezdaniel.databinding.ActivityModificarEventoBinding
import com.google.android.material.snackbar.Snackbar
import java.util.*

class ModificarEventoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityModificarEventoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityModificarEventoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var bd= OperacionesDao(this)
        binding.dataPicker.minDate=System.currentTimeMillis()

        binding.btnSeleccionarEvento.setOnClickListener(){
            val myIntent = Intent(this, EventosActivity::class.java)
                .putExtra("opcion", 1)
            startForResult.launch(myIntent)
        }
        binding.buttonSalir.setOnClickListener(){
            finish()
        }
        binding.buttonGuardar.setOnClickListener(){
            val fecha= Date(binding.dataPicker.year,
                binding.dataPicker.month,
                binding.dataPicker.dayOfMonth,
                binding.timePicker.hour,
                binding.timePicker.minute)
            val codigo=binding.codigoEvento.text.toString()
            if(fecha.time>System.currentTimeMillis()&&codigo!="0"){
                val fechaString="${binding.dataPicker.dayOfMonth}/${binding.dataPicker.month+1}/${binding.dataPicker.year}"
                val horaString="${binding.timePicker.hour}:${binding.timePicker.minute}"
                bd.changeEvento(fechaString,horaString,codigo.toInt())
                Snackbar.make(binding.root,"Nueva fecha guardada", Snackbar.LENGTH_LONG).show()
            }else
                Snackbar.make(binding.root,"Datos erroneos", Snackbar.LENGTH_LONG).show()

        }
    }

    private val startForResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val intent: Int? = result.data?.getIntExtra("evento", 0)
                if (intent != null) {
                    binding.codigoEvento.text = intent.toInt().toString()
                }
            }
        }
}