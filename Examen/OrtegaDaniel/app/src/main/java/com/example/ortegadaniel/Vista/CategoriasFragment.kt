package com.example.ortegadaniel.Vista

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ej9ortegadanielbd.vistaModelo.VistaModelo
import com.example.ej9ortegadanielbd.vistaModelo.VistaModeloFactory
import com.example.ortegadaniel.OperacionesDao
import com.example.ortegadaniel.databinding.FragmentCategoriasBinding
import com.example.ortegadaniel.tool.Events
import com.example.ortegadaniel.tool.ListenerAdapterCards

class CategoriasFragment : Fragment(), Events {

    private lateinit var binding: FragmentCategoriasBinding
    private var mActivity: MainActivity? = null
    private lateinit var linearLayout: LinearLayoutManager
    private lateinit var bd: OperacionesDao
    private lateinit var modelo: VistaModelo


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCategoriasBinding.inflate(inflater, container, false)
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
        binding.content.adapter = ListenerAdapterCards(bd.getCategorias(), mActivity!!.applicationContext, this)
        binding.content.layoutManager = linearLayout
        binding.content.setHasFixedSize(true)
    }
    override fun shortClick(codigo: Int) {
        modelo.setUsuario(codigo.toLong())

    }

    override fun longClick(codigo: Int): Boolean {

        return false
    }
}