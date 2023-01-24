package com.example.ut7ej8ortegamartinezdaniel.layout

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.ut7ej8ortegamartinezdaniel.databinding.ActivityModificarEventoBinding

class ModificarEventoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityModificarEventoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityModificarEventoBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}