package com.example.ej9ortegadanielbd.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.ej9ortegadanielbd.R
import com.example.ej9ortegadanielbd.dataBase.OperacionesDao
import com.example.ej9ortegadanielbd.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var bd: OperacionesDao
    private lateinit var fragmentCards: CardsFragment
    private lateinit var fragmentManager: FragmentManager
    private lateinit var fragmentTransaction: FragmentTransaction

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        bd= OperacionesDao(this)

        chargeFragment(savedInstanceState)
    }

    private fun chargeFragment(savedInstanceState: Bundle?) {
        fragmentCards = CardsFragment()
        fragmentManager = supportFragmentManager
        fragmentTransaction = fragmentManager.beginTransaction()
        //Si el fragmento no existia anteriormente se añade
        if (savedInstanceState == null) {
            fragmentTransaction.add(R.id.frag_contenedor, fragmentCards)
        } else {//Si el fragmente ya existia se sustituye el anterior por el nuevo
            fragmentTransaction.replace(R.id.frag_contenedor, fragmentCards)
        }
        fragmentTransaction.commit()
    }
}