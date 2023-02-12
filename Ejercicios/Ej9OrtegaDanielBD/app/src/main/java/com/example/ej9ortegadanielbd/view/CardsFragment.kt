package com.example.ej9ortegadanielbd.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.ui.AppBarConfiguration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ej9ortegadanielbd.Events
import com.example.ej9ortegadanielbd.ListenerAdapterCards
import com.example.ej9ortegadanielbd.dataBase.OperacionesDao
import com.example.ej9ortegadanielbd.databinding.FragmentCardsBinding
import com.example.ej9ortegadanielbd.vistaModelo.VistaModelo
import com.example.ej9ortegadanielbd.vistaModelo.VistaModeloFactory

class CardsFragment : Fragment(), Events {

    private lateinit var binding: FragmentCardsBinding
    private var mActivity: MainActivity? = null
    private lateinit var linearLayout: LinearLayoutManager
    private lateinit var bd: OperacionesDao
    private lateinit var modelo: VistaModelo


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCardsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        /**Es necesario el contexto tanto para la base de datos como para el recycler
         *Por ello como el fragment no tiene el contexto de una activity se llama al MainActivity de la siguiente manera
         */
        //Cast seguro
        mActivity = activity as? MainActivity
        bd =OperacionesDao(mActivity!!.applicationContext)

        val viewModelFactory = VistaModeloFactory(0)
        modelo =
            ViewModelProvider(this.requireActivity(), viewModelFactory)[VistaModelo::class.java]
        val observador = Observer<Long> {

        }
        chargeRecycler()
        modelo.identificador.observe(this.viewLifecycleOwner, observador)

    }

    private fun chargeRecycler() {
        //!! evita que sea nulo
        linearLayout = LinearLayoutManager(mActivity!!.applicationContext)
        //Contexto del activity y listener del fragment
        binding.recycler.adapter = ListenerAdapterCards(bd.getUsuarios(), mActivity!!.applicationContext, this)
        binding.recycler.layoutManager = linearLayout
        binding.recycler.setHasFixedSize(true)
    }
    override fun shortClick(codigo: Int) {
        modelo.setUsuario(codigo.toLong())
        mActivity?.mostrarCitas()
    }

    override fun longClick(codigo: Int): Boolean {

        return false
    }
}