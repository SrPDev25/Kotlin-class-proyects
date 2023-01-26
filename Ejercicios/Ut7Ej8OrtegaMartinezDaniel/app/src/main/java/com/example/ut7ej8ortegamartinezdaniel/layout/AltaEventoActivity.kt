package com.example.ut7ej8ortegamartinezdaniel.layout

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.ut7ej7ortegadaniel.dataBase.OperacionesDao
import com.example.ut7ej8ortegamartinezdaniel.databinding.ActivityAltaEventoBinding
import com.google.android.material.snackbar.Snackbar
import java.util.*

class AltaEventoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAltaEventoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAltaEventoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var bd=OperacionesDao(this)
        binding.dataPicker.minDate=System.currentTimeMillis()

        binding.buttonAnnadir.setOnClickListener(){
            val titulo=binding.nombreTxt.text.toString()
            val descripcion=binding.descripcionTxt.text.toString()
            val fecha= Date(binding.dataPicker.year,
                            binding.dataPicker.month,
                            binding.dataPicker.dayOfMonth,
                            binding.timePicker.hour,
                            binding.timePicker.minute)
            if(titulo!="" && descripcion!=""&&fecha.time>System.currentTimeMillis()){
                val fechaString="${binding.dataPicker.dayOfMonth}/${fecha.month+1}/${fecha.year}"
                bd.addEvento(fechaString,fecha.hours.toString()+":"+fecha.minutes.toString(),titulo,descripcion)
                finish()
            }else
                Snackbar.make(binding.root,"Datos erroneos",Snackbar.LENGTH_LONG).show()

        }


    }
}