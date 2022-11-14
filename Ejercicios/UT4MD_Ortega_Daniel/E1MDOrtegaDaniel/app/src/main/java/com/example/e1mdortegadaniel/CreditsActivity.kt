package com.example.e1mdortegadaniel

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.e1mdortegadaniel.databinding.CredistMainBinding

class CreditsActivity: AppCompatActivity() {
    private lateinit var binding: CredistMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = CredistMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Glide.with(this)
            .load("https://librosking.com/wp-content/uploads/2022/04/Logo-Libros-king-2022.1.png")
            .into(binding.imgLogo)
        binding.whiteLayout.background.alpha = 200

        binding.buttonReturn.setOnClickListener {
            finishAndRemoveTask()
        }

    }
}