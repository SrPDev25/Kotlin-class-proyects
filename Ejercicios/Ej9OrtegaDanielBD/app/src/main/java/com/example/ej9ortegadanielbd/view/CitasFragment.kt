package com.example.ej9ortegadanielbd.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ej9ortegadanielbd.Events
import com.example.ej9ortegadanielbd.ListenerAdapterCitas
import com.example.ej9ortegadanielbd.R
import com.example.ej9ortegadanielbd.dataBase.OperacionesDao
import com.example.ej9ortegadanielbd.databinding.FragmentCitaBinding
import com.example.ej9ortegadanielbd.vistaModelo.VistaModelo
import com.example.ej9ortegadanielbd.vistaModelo.VistaModeloFactory
import com.google.android.material.snackbar.Snackbar

class CitasFragment : Fragment(),Events {

    private lateinit var binding: FragmentCitaBinding
    private var mActivity: MainActivity? = null
    private lateinit var linearLayout: LinearLayoutManager
    private lateinit var bd: OperacionesDao
    private lateinit var modelo:VistaModelo

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentCitaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mActivity=activity as? MainActivity
        bd=OperacionesDao(mActivity!!.applicationContext)

        linearLayout= LinearLayoutManager(mActivity!!.applicationContext)

        val viewModelFactory = VistaModeloFactory(0)
        modelo =
            ViewModelProvider(this.requireActivity(), viewModelFactory).get(VistaModelo::class.java)
        chargeRecycler()
        binding.btnNuevaCita.setOnClickListener(){
            mActivity!!.addCitas()
        }
    }

    private fun chargeRecycler() {
        val codUsuario= modelo.identificador.value?.toInt()
        //!! evita que sea nulo
        linearLayout = LinearLayoutManager(mActivity!!.applicationContext)
        //Contexto del activity y listener del fragment
        binding.container.adapter = ListenerAdapterCitas(bd.getCitas(codUsuario!!), mActivity!!.applicationContext, this)
        binding.container.layoutManager = linearLayout
        binding.container.setHasFixedSize(true)
    }

    override fun shortClick(codigo: Int) {
        view?.let {
            Snackbar.make(
                it, "¿Quiere eliminar la cita?",
                Snackbar.LENGTH_LONG
            )
                //.setAnchorView(binding.fab)
                .setAction("Eliminar") {//Añade un boton
                    bd.deleteCita(codigo)
                    chargeRecycler()
                }
                .show()
        }
    }


}