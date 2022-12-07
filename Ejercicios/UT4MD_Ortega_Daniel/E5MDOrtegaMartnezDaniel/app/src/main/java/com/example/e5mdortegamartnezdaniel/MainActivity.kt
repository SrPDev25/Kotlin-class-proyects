package com.example.e5mdortegamartnezdaniel

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.e5mdortegamartnezdaniel.control.Control
import com.example.e5mdortegamartnezdaniel.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var control: Control
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        control= intent.getParcelableExtra("baseDatos")!!
        Glide.with(this)
            .load("https://digitalhospital.com.sg/wp-content/uploads/2020/05/cropped-Digital-Hospital-Logo-FavIcon-2.png")
            .into(binding.imgLogo)
        //TODO posible "error": si vuelves e inicias otra vez este activity se resetea la colección
        binding.imgLogo.setOnClickListener {finish()}
        binding.especialidadButton.setOnClickListener(){
            val myIntent = Intent(this, InformationActivity::class.java).putExtra("dataBase",control)
            startActivity(myIntent)
        }

    }
}