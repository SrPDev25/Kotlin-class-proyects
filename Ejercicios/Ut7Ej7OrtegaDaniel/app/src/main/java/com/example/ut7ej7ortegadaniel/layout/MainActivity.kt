package com.example.ut7ej7ortegadaniel.layout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ut7ej7ortegadaniel.R
import com.example.ut7ej7ortegadaniel.databinding.ActivityFirstBinding
import com.example.ut7ej7ortegadaniel.databinding.ActivityMainBinding
import com.example.ut7ej7ortegadaniel.tool.MyXMLReader

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        

    }

}