package com.example.e4mdortegadaniel

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.e4mdortegadaniel.databinding.ActivityVictoryBinding

class VictoryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityVictoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityVictoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.restartButton.setOnClickListener {
            finish()
        }
    }
}