package com.example.ej9ortegadanielbd.view

import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import com.example.ej9ortegadanielbd.control.Profesional
import com.example.ej9ortegadanielbd.control.TipoProfesional
import com.example.ej9ortegadanielbd.dataBase.OperacionesDao
import com.example.ej9ortegadanielbd.databinding.FragmentAddUsuarioProfesionalesBinding

class AddUsuarioProfesionalFragment : Fragment() {

    private lateinit var binding: FragmentAddUsuarioProfesionalesBinding
    private var mActivity: MainActivity? = null
    private lateinit var bd:OperacionesDao
    private lateinit var listaTipoProfesional:MutableList<TipoProfesional>
    private lateinit var listaProfesional:MutableList<Profesional>


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
        listaTipoProfesional=bd.getTipoProfesional()
        if (listaTipoProfesional.size!=0) {
            val tipoProfesional=listaTipoProfesional.get(0)
            chargeSpinner(tipoProfesional.codigo)
            binding.tipoProfesionalTxt.text=tipoProfesional.denominacion
        }
        binding.buttonSiguiente.setOnClickListener(){


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