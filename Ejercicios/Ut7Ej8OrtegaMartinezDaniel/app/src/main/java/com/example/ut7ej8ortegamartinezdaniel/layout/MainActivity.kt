package com.example.ut7ej8ortegamartinezdaniel.layout

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.ut7ej8ortegamartinezdaniel.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var usuario=-1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        usuario=intent.getIntExtra("usuario",-1)
        binding.whiteLayout.background.alpha=200
        binding.buttonNewEvento.setOnClickListener(){
            var myIntent= Intent(this,AltaEventoActivity::class.java)
            startActivity(myIntent)
        }
        binding.buttonModificarEvento.setOnClickListener(){
            var myIntent= Intent(this,ModificarEventoActivity::class.java)
            startActivity(myIntent)
        }
        binding.buttonApuntarseEvento.setOnClickListener(){
            var myIntent= Intent(this,EventosActivity::class.java)
                .putExtra("usuario",usuario)
            startActivity(myIntent)
        }

    }
}