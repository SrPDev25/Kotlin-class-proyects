package com.example.e5mdortegamartnezdaniel

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.e5mdortegamartnezdaniel.databinding.ActivityStartBinding

class StartActivity : AppCompatActivity(),Events {
    lateinit var binding: ActivityStartBinding
    lateinit var linearLayout: LinearLayoutManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityStartBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.recyclerLogo.adapter=ListenerAdapter(this)
        linearLayout = LinearLayoutManager(this)//this, se
        binding.recyclerLogo.layoutManager = linearLayout//
        binding.recyclerLogo.setHasFixedSize(true)//no se, supongo que es para prevenir bugs visuales
        //Cargar imagen
        Glide.with(this)
            .load("https://digitalhospital.com.sg/wp-content/uploads/2020/05/cropped-Digital-Hospital-Logo-FavIcon-2.png")
            .into(binding.imgLogo)
        binding.whiteLayout.background.alpha=200

    }

    override fun shortKeyStroke() {

    }

    override fun longKeyStroke(): Boolean {
        return false
    }

}
