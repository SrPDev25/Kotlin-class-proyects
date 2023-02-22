package com.example.ej9ortegadanielbd.view

import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.ej9ortegadanielbd.control.Profesional
import com.example.ej9ortegadanielbd.control.TipoProfesional
import com.example.ej9ortegadanielbd.dataBase.OperacionesDao
import com.example.ej9ortegadanielbd.databinding.FragmentAddUsuarioProfesionalesBinding
import com.example.ej9ortegadanielbd.vistaModelo.VistaModelo
import com.example.ej9ortegadanielbd.vistaModelo.VistaModeloFactory

class AddUsuarioProfesionalFragment : Fragment() {

    private lateinit var binding: FragmentAddUsuarioProfesionalesBinding
    private var mActivity: MainActivity? = null
    private lateinit var bd:OperacionesDao
    private lateinit var modelo:VistaModelo
    private lateinit var listaTipoProfesional:MutableList<TipoProfesional>
    private lateinit var listaProfesional:MutableList<Profesional>

    private var usuario=""
    private var listaUsuario= mutableListOf<Int>()
    private var currentTipo=0



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentAddUsuarioProfesionalesBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mActivity = activity as? MainActivity
        bd= OperacionesDao(mActivity!!.applicationContext)
        val viewModelFactory = VistaModeloFactory(0)
        modelo =
            ViewModelProvider(this.requireActivity(), viewModelFactory).get(VistaModelo::class.java)
        usuario = modelo.nombre.value.toString()

        listaTipoProfesional=bd.getTipoProfesional()
        if (listaTipoProfesional.size!=0) {
            val tipoProfesional=listaTipoProfesional.get(currentTipo)
            chargeSpinner(tipoProfesional.codigo)
            binding.tipoProfesionalTxt.text=tipoProfesional.denominacion
        }
        binding.buttonSiguiente.setOnClickListener(){
            chargeNextSpinner()

        }

    }

    private fun chargeNextSpinner() {
        currentTipo++
        //Se indica el profesional con el que va a tener relación
        val posicion=binding.spinnerProfesional.selectedItemPosition
        listaUsuario.add(listaProfesional.get(posicion).numColegiado)


        if (listaTipoProfesional.size>listaUsuario.size){
            val tipoProfesional=listaTipoProfesional.get(currentTipo)
            listaProfesional=bd.getProfesionales(tipoProfesional.codigo)
            binding.tipoProfesionalTxt.text=tipoProfesional.denominacion
            context?.let {
                val spinnerAdapter = ArrayAdapter(it, R.layout.simple_list_item_1, listaProfesional)
                binding.spinnerProfesional.adapter = spinnerAdapter
            }
        }else{
            bd.addRelacionesUsuario(listaUsuario,usuario)
            mActivity!!.chargeMainFragment()
        }
    }

    private fun chargeSpinner(tipoProfesional:Int) {
        listaProfesional=bd.getProfesionales(tipoProfesional)
        context?.let {
            val spinnerAdapter = ArrayAdapter(it, R.layout.simple_list_item_1, listaProfesional)
            binding.spinnerProfesional.adapter = spinnerAdapter
        }
    }
}