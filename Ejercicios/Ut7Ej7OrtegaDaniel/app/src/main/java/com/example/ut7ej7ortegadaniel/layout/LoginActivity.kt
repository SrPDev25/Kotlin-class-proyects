package com.example.ut7ej7ortegadaniel.layout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ut7ej7ortegadaniel.databinding.ActivityLoginBinding


class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.whiteLayout.background.alpha = 200
        binding.buttonLogin.setOnClickListener() {
            val user = binding.userText.text.toString()
            val pass = binding.passText.text.toString()
        }

    }

}