package com.example.e1mdortegadaniel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import com.example.e1mdortegadaniel.databinding.ActivityMainBinding
//añadir: https://www.google.com/url?sa=i&url=https%3A%2F%2Flibrosking.com%2Ftienda%2F&psig=AOvVaw26YJd-F5opP9kYG4jt_SKS&ust=1667465628639000&source=images&cd=vfe&ved=0CAoQjRxqFwoTCIC1qtGPj_sCFQAAAAAdAAAAABAV
class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}