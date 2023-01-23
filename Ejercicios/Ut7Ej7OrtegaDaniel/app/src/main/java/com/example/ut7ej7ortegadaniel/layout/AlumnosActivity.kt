package com.example.ut7ej7ortegadaniel.layout

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.e6mdortegadaniel.Events
import com.example.ut7ej7ortegadaniel.ListenerAdapterAlumnos
import com.example.ut7ej7ortegadaniel.databinding.ActivityAlumnosBinding
import com.mjpg.basedatos.dao.OperacionesDao

class AlumnosActivity: AppCompatActivity(), Events {
    private lateinit var binding: ActivityAlumnosBinding
    private lateinit var linearLayout: LinearLayoutManager
    private var profesor=-1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        profesor=intent.getIntExtra("Profesor",-1)
        chargeLayout()

    }

    //Para que esté más ordenadito
    private fun chargeLayout() {
        binding = ActivityAlumnosBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var db = OperacionesDao(this)
        profesor = intent.getIntExtra("Profesor", -1)
        chargeRecycler(db)
    }

    private fun chargeRecycler(db: OperacionesDao) {
        binding.recyclerview.adapter = ListenerAdapterAlumnos(db.getAlumnos(profesor), this)
        linearLayout = LinearLayoutManager(this)
        binding.recyclerview.layoutManager = linearLayout
        binding.recyclerview.setHasFixedSize(true)
    }



    override fun shortClick(pos: Int) {
        var myIntent=Intent()
            .putExtra("alumno",pos)
        setResult(Activity.RESULT_OK, myIntent)
        finish()
    }
}