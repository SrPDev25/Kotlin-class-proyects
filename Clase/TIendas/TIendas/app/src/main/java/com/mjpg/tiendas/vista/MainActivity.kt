package com.mjpg.tiendas.vista


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mjpg.tiendas.*
import com.mjpg.tiendas.databinding.ActivityMainBinding
import com.mjpg.tiendas.interfaces.Comunicar

class MainActivity : AppCompatActivity(), Comunicar {

    private lateinit var binding: ActivityMainBinding

private lateinit var fragmentEditar: EditStoreFragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (binding.fragmentLista == null)
            launchFragment(savedInstanceState)
        else{
            // tablet
        fragmentEditar = EditStoreFragment()
            val args = Bundle()
            args.putLong(getString(R.string.arg_id),-1)
            val fragmentManager = supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.add(R.id.fragmentdetalle, fragmentEditar)
                 fragmentTransaction.addToBackStack(null)
                   fragmentTransaction.commit()


        }
    }

    private fun launchFragment(savedInstanceState: Bundle?) {
        val fragment = Consulta()
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        if (savedInstanceState == null) {
            fragmentTransaction.add(R.id.frag_contenedor, fragment)
        } else {
            fragmentTransaction.replace(R.id.frag_contenedor, fragment)
        }
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }

    override fun editar(id: Long) {
        val fragmentManager = supportFragmentManager
        val args = Bundle()
        args.putLong(getString(R.string.arg_id), id)
        val fragment = EditStoreFragment()
        fragment.arguments = args
        if (binding.fragmentdetalle == null) {
            val fragmentTransaction = fragmentManager.beginTransaction()

            fragmentTransaction.replace(R.id.frag_contenedor, fragment)
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
        }
        else{
            fragmentEditar.cambio(id)
        }
    }

    override fun anadir() {
        val args = Bundle()
        args.putLong(getString(R.string.arg_id), -1)

        val fragmentManager = supportFragmentManager
        val fragment = EditStoreFragment()
        fragment.arguments = args
        val fragmentTransaction = fragmentManager.beginTransaction()
        if (binding.fragmentdetalle == null) {
            fragmentTransaction.replace(R.id.frag_contenedor, fragment)
        } else {
            fragmentTransaction.replace(R.id.fragmentdetalle, fragment)
        }
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }
}