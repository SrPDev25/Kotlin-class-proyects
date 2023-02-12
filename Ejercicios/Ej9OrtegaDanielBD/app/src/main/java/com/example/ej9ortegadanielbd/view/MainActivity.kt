package com.example.ej9ortegadanielbd.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.replace
import androidx.lifecycle.ViewModelProvider
import com.example.ej9ortegadanielbd.R
import com.example.ej9ortegadanielbd.dataBase.OperacionesDao
import com.example.ej9ortegadanielbd.databinding.ActivityMainBinding
import com.example.ej9ortegadanielbd.vistaModelo.VistaModelo
import com.example.ej9ortegadanielbd.vistaModelo.VistaModeloFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var bd: OperacionesDao
    private lateinit var fragmentManager: FragmentManager
    private lateinit var fragmentTransaction: FragmentTransaction
    private lateinit var modeloVistaControlador:VistaModelo

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        bd= OperacionesDao(this)
        //Se inicializan ambos en el constructor pero el viewModel es una variable del contenedor de fragments
        val viewModelFactory = VistaModeloFactory(0)
        modeloVistaControlador = ViewModelProvider(this, viewModelFactory)
            .get(VistaModelo::class.java)
        chargeFragment(savedInstanceState)
    }

    private fun chargeFragment(savedInstanceState: Bundle?) {
        val fragmentCards = CardsFragment()
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



    fun mostrarCitas() {
        val fragmentCita=CitasFragment()
        fragmentTransaction=fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frag_contenedor,fragmentCita)
        fragmentTransaction.commit()
    }




}