package com.example.ej9ortegadanielbd.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.ui.AppBarConfiguration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ej9ortegadanielbd.Events
import com.example.ej9ortegadanielbd.ListenerAdapterCards
import com.example.ej9ortegadanielbd.dataBase.OperacionesDao
import com.example.ej9ortegadanielbd.databinding.ActivityCardsBinding

class CardsActivity : AppCompatActivity(), Events {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityCardsBinding
    private lateinit var linearLayout: LinearLayoutManager
    private lateinit var bd: OperacionesDao
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCardsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        bd = OperacionesDao(this)
        linearLayout = LinearLayoutManager(this)
        binding.recycler.adapter = ListenerAdapterCards(bd.getUsuarios(), this, this)
        binding.recycler.layoutManager = linearLayout
        binding.recycler.setHasFixedSize(true)


    }

    override fun shortClick(codigo: Int) {
        TODO("Not yet implemented")
    }

    override fun longClick(codigo: Int): Boolean {
        TODO("Not yet implemented")
    }
}