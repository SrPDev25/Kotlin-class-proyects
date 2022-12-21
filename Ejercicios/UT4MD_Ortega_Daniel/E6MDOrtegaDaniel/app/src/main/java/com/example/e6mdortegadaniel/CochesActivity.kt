package com.example.e6mdortegadaniel

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.e6mdortegadaniel.control.Control
import com.example.e6mdortegadaniel.databinding.ActivityCochesBinding

class CochesActivity : AppCompatActivity(),Events {
    private lateinit var binding: ActivityCochesBinding
    private lateinit var linearLayout: LinearLayoutManager
    private var control= Control()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCochesBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.recyclerview.adapter = ListenerAdapter(control.vehiculos,this)
        linearLayout = LinearLayoutManager(this)
        binding.recyclerview.layoutManager = linearLayout
        binding.recyclerview.setHasFixedSize(true)

    }

    override fun longClick(pos: Int): Boolean {
        return true
    }
}