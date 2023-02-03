package com.example.ej9ortegadanielbd.view

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.ej9ortegadanielbd.databinding.ActivityCardsBinding

class CardsActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
private lateinit var binding: ActivityCardsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCardsBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}