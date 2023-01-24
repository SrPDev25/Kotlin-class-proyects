package com.example.ut7ej8ortegamartinezdaniel.layout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ut7ej8ortegamartinezdaniel.R
import com.example.ut7ej8ortegamartinezdaniel.databinding.ActivityFirstBinding

class FirstActivity : AppCompatActivity() {
    private lateinit var binding:ActivityFirstBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityFirstBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}