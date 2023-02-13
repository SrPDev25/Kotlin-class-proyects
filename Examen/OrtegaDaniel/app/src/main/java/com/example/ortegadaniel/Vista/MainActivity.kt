package com.example.ortegadaniel.Vista

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import com.example.ej9ortegadanielbd.vistaModelo.VistaModelo
import com.example.ej9ortegadanielbd.vistaModelo.VistaModeloFactory
import com.example.ortegadaniel.OperacionesDao
import com.example.ortegadaniel.R
import com.example.ortegadaniel.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var bd: OperacionesDao
    private lateinit var fragmentManager: FragmentManager
    private lateinit var fragmentTransaction: FragmentTransaction
    private lateinit var modeloVistaControlador:VistaModelo

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val viewModelFactory = VistaModeloFactory(0)
        modeloVistaControlador = ViewModelProvider(this, viewModelFactory)
            .get(VistaModelo::class.java)
        val tipoUsuario=intent.getLongExtra("tipoLogin",-1L)
        modeloVistaControlador.setUsuario(tipoUsuario)
        chargeFragment(savedInstanceState)
    }

    private fun chargeFragment(savedInstanceState: Bundle?) {
        val fragmentCards = CategoriasFragment()
        fragmentManager = supportFragmentManager
        fragmentTransaction = fragmentManager.beginTransaction()
        if (savedInstanceState == null) {
            fragmentTransaction.add(R.id.frag_contenedor, fragmentCards)
        } else {
            fragmentTransaction.replace(R.id.frag_contenedor, fragmentCards)
        }
        fragmentTransaction.disallowAddToBackStack()
        fragmentTransaction.commit()
    }

    public fun chargeProductos(){
        val fragmentProducto=ProductoFragment()
        fragmentTransaction=fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frag_contenedor,fragmentProducto)
        //Anula el volver hacia atrás del activity
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }
}