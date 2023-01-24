package com.example.ut7ej8ortegamartinezdaniel.layout

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.ut7ej8ortegamartinezdaniel.databinding.ActivityAltaEventoBinding

class AltaEventoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAltaEventoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAltaEventoBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}