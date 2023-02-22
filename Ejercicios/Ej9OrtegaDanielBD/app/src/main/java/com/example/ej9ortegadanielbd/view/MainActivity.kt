package com.example.ej9ortegadanielbd.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
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
    private lateinit var modeloVistaControlador: VistaModelo
    private lateinit var savedInstanceState: Bundle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        bd = OperacionesDao(this)
        //Se inicializan ambos en el constructor pero el viewModel es una variable del contenedor de fragments
        val viewModelFactory = VistaModeloFactory(0)
        modeloVistaControlador = ViewModelProvider(this, viewModelFactory)[VistaModelo::class.java]
        chargeStartFragment(savedInstanceState)
        title="Gestor de citas"
    }

    private fun chargeStartFragment(savedInstanceState: Bundle?) {
        val fragmentCards = CardsFragment()
        fragmentManager = supportFragmentManager
        fragmentTransaction = fragmentManager.beginTransaction()
        //Si el fragmento no existia anteriormente se añade
        if (savedInstanceState == null) {
            fragmentTransaction.add(R.id.frag_contenedor, fragmentCards,"Main")
        } else {//Si el fragmente ya existia se sustituye el anterior por el nuevo
            fragmentTransaction.replace(R.id.frag_contenedor, fragmentCards,"Main")
        }
        //Si es el frgament principal hay que deshabilitar el backstack, ya que eliminaria el fragment
        fragmentTransaction.disallowAddToBackStack()
        fragmentTransaction.commit()
    }

    public fun chargeMainFragment() {
        val fragmentCards = CardsFragment()
        fragmentManager = supportFragmentManager
        fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frag_contenedor, fragmentCards,"Main")

        fragmentTransaction.disallowAddToBackStack()
        fragmentTransaction.commit()
    }

    fun mostrarCitas() {
        title="Citas"
        val fragmentCita = CitasFragment()
        fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frag_contenedor, fragmentCita,"Citas")
        //Anula el volver hacia atrás del activity
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }

    fun addCitas() {
        title="Nueva cita"
        val fragmentCita = AddCitaFragment()
        fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frag_contenedor, fragmentCita,"Add citas")
        //Anula el volver hacia atrás del activity
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }

    fun addUsuarioNombre() {
        title="Nuevo usuario"
        val fragment = AddUsuarioNombreFragment()
        fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frag_contenedor, fragment)

        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }

    fun addUsuarioProfesional() {
        val fragment = AddUsuarioProfesionalFragment()
        fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frag_contenedor, fragment)

        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        val myFragment: Fragment? = getSupportFragmentManager().findFragmentByTag("Main")
        if(myFragment!=null && !myFragment.isVisible)
            super.onBackPressed()

    }


}