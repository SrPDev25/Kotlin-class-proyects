package com.example.e1mdortegadaniel

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.e1mdortegadaniel.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}


