package com.example.ut7ej7ortegadaniel.layout

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.e6mdortegadaniel.Events
import com.example.ut7ej7ortegadaniel.ListenerAdapterFaltas
import com.example.ut7ej7ortegadaniel.databinding.ActivityFaltasBinding
import com.mjpg.basedatos.dao.OperacionesDao

class FaltasActivity : AppCompatActivity(), Events {
    private lateinit var binding: ActivityFaltasBinding
    private lateinit var linearLayout: LinearLayoutManager
    private lateinit var db:OperacionesDao
    private var alumno=-1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        chargeLayout()
    }

    private fun chargeLayout() {
        binding = ActivityFaltasBinding.inflate(layoutInflater)
        setContentView(binding.root)
        db = OperacionesDao(this)
        alumno = intent.getIntExtra("alumno", -1)
        chargeRecycler()
    }

    private fun chargeRecycler() {
        binding.recyclerview.adapter = ListenerAdapterFaltas(db.getFaltas(alumno), this)
        linearLayout = LinearLayoutManager(this)
        binding.recyclerview.layoutManager = linearLayout
        binding.recyclerview.setHasFixedSize(true)
    }

    override fun shortClick(pos: Int) {
        db.switchJustificada(pos)
        chargeLayout()
    }
}