package com.example.ej9ortegadanielbd.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.ej9ortegadanielbd.dataBase.OperacionesDao
import com.example.ej9ortegadanielbd.databinding.ActivityMainBinding

class MainActivity: AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var bd: OperacionesDao
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        bd = OperacionesDao(this)

    }
}