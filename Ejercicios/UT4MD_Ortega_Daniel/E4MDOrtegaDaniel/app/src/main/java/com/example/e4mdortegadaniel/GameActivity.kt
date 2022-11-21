package com.example.e4mdortegadaniel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.e4mdortegadaniel.databinding.ActivityMainBinding

class GameActivity : AppCompatActivity() {
    lateinit var binding:ActivityMainBinding
    var control =HangmanControl()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        chargeLines()

        //binding.buttonB.visibility = View.INVISIBLE//Arapaho
    }

    fun chargeLines(){
        binding.textToResolve.text=control.startGame()
    }
}