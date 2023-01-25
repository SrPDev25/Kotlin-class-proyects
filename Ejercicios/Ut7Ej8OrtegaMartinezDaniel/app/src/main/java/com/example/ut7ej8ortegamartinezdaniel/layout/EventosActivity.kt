package com.example.ut7ej8ortegamartinezdaniel.layout

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.e6mdortegadaniel.Events
import com.example.ut7ej7ortegadaniel.ListenerAdapterAlumnos
import com.example.ut7ej7ortegadaniel.dataBase.OperacionesDao
import com.example.ut7ej8ortegamartinezdaniel.databinding.ActivityEventosBinding

class EventosActivity : AppCompatActivity(), Events {
    private lateinit var binding: ActivityEventosBinding
    private lateinit var linearLayout: LinearLayoutManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEventosBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val bd=OperacionesDao(this)
        binding.recycler.adapter= ListenerAdapterAlumnos(bd.getEventosOrdenados(),this)
        linearLayout=LinearLayoutManager(this)
        binding.recycler.layoutManager=linearLayout
        binding.recycler.setHasFixedSize(true)
    }

    override fun shortClick(pos: Int) {
        TODO("Not yet implemented")
    }
}