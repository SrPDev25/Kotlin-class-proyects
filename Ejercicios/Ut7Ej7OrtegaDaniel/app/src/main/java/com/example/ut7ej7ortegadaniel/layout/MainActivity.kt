package com.example.ut7ej7ortegadaniel.layout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.e6mdortegadaniel.Events
import com.example.ut7ej7ortegadaniel.ListenerAdapterAlumnos
import com.example.ut7ej7ortegadaniel.databinding.ActivityFaltasBinding
import com.example.ut7ej7ortegadaniel.databinding.ActivityMainBinding
import com.mjpg.basedatos.dao.OperacionesDao

class MainActivity : AppCompatActivity(), Events {
    private lateinit var binding: ActivityMainBinding
    private lateinit var linearLayout: LinearLayoutManager
    private var profesor=-1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        chargeLayout()

        binding.buttonAdd.setOnClickListener(){

        }

    }

    //Para que esté más ordenadito
    private fun chargeLayout() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var db = OperacionesDao(this)
        profesor = intent.getIntExtra("Profesor", -1)
        chargeRecycler(db)
    }

    private fun chargeRecycler(db:OperacionesDao) {
        binding.recyclerview.adapter = ListenerAdapterAlumnos(db.getAlumnos(profesor), this)
        linearLayout = LinearLayoutManager(this)
        binding.recyclerview.layoutManager = linearLayout
        binding.recyclerview.setHasFixedSize(true)
    }



    override fun shortClick(pos: Int) {
        val myIntent=Intent(this,FaltasActivity::class.java)
            .putExtra("alumno",pos)
        startActivity(myIntent)
    }

}