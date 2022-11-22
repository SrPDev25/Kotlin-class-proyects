package com.example.e4mdortegadaniel

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.e4mdortegadaniel.databinding.ActivityLostBinding

class LostActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLostBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityLostBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.restartButton.setOnClickListener {
            finish()
        }
    }
}