package com.example.ut7ej8ortegamartinezdaniel.layout

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.ut7ej8ortegamartinezdaniel.databinding.ActivityEventosBinding

class EventosActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEventosBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEventosBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}