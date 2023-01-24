package com.example.ut7ej8ortegamartinezdaniel.layout

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.ut7ej8ortegamartinezdaniel.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}