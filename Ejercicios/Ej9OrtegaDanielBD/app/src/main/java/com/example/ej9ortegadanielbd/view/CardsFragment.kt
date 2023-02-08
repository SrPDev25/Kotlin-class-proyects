package com.example.ej9ortegadanielbd.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ej9ortegadanielbd.Events
import com.example.ej9ortegadanielbd.ListenerAdapterCards
import com.example.ej9ortegadanielbd.dataBase.OperacionesDao
import com.example.ej9ortegadanielbd.databinding.FragmentCardsBinding
import com.example.ej9ortegadanielbd.vistaModelo.VistaModelo

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
        chargeRecycler()
        /** TODO invesigar como funciona
        val observador2 = Observer<Long> {
            if (it == -2L) {
                configurarRecycler()
            }
        }
        modelo.identificador.observe(this.viewLifecycleOwner, observador2)
        */
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
        //TODO("Not yet implemented")
    }

    override fun longClick(codigo: Int): Boolean {
        //TODO("Not yet implemented")
        return false
    }
}