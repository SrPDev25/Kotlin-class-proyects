package com.example.e5mdortegamartnezdaniel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.e5mdortegamartnezdaniel.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Glide.with(this)
            .load("https://digitalhospital.com.sg/wp-content/uploads/2020/05/cropped-Digital-Hospital-Logo-FavIcon-2.png")
            .into(binding.imgLogo)
    }
}