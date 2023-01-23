package com.example.ut7ej7ortegadaniel.layout

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.ut7ej7ortegadaniel.databinding.ActivityAddFaltasBinding

class AddFaltasActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddFaltasBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddFaltasBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //todo todo
    }
}