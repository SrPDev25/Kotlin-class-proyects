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
    private lateinit var bd:OperacionesDao
    private var usuario:Int=-1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEventosBinding.inflate(layoutInflater)
        setContentView(binding.root)
        usuario=intent.getIntExtra("usuario",-1)

        bd=OperacionesDao(this)
        chargeRecycler()


    }

    private fun chargeRecycler() {
        binding.recycler.adapter =
            ListenerAdapterAlumnos(bd.getEventosOrdenados(), usuario, this, this)
        linearLayout = LinearLayoutManager(this)
        binding.recycler.layoutManager = linearLayout
        binding.recycler.setHasFixedSize(true)
    }

    override fun shortClick(pos: Int) {
        if (bd.isEventoUsuarioExist(usuario,pos))
            bd.removeEventosUsuario(usuario,pos)
        else
            bd.addEventosUsuario(usuario,pos)

        chargeRecycler()
    }
}