package com.example.e5mdortegamartnezdaniel

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.e5mdortegamartnezdaniel.control.Control
import com.example.e5mdortegamartnezdaniel.control.Especialidad
import com.example.e5mdortegamartnezdaniel.databinding.ActivityInformationBinding

class InformationActivity: AppCompatActivity(),Events {
    lateinit var binding: ActivityInformationBinding
    lateinit var linearLayout: LinearLayoutManager
    lateinit var control: Control//TODO
    var especialidades=mutableListOf<Especialidad>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInformationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        control=intent.getParcelableExtra("dataBase")!!
        binding.recyclerview.adapter = ListenerAdapter(control.especialidades,this)
        linearLayout = LinearLayoutManager(this)
        binding.recyclerview.layoutManager = linearLayout
        binding.recyclerview.setHasFixedSize(true)

    }



    override fun shortKeyStroke(code: Int) {
        TODO("Not yet implemented")
    }


}