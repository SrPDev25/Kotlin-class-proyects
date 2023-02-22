package com.example.ej9ortegadanielbd.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.ej9ortegadanielbd.databinding.FragmentAddUsuarioNombreBinding
import com.example.ej9ortegadanielbd.vistaModelo.VistaModelo
import com.example.ej9ortegadanielbd.vistaModelo.VistaModeloFactory

class AddUsuarioNombreFragment : Fragment() {

    private lateinit var binding:FragmentAddUsuarioNombreBinding
    private var mActivity: MainActivity? = null
    private lateinit var modelo: VistaModelo

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentAddUsuarioNombreBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mActivity = activity as? MainActivity
        val viewModelFactory = VistaModeloFactory(0)
        modelo =
            ViewModelProvider(this.requireActivity(), viewModelFactory).get(VistaModelo::class.java)
        binding.buttonSiguiente.setOnClickListener(){
            val nombre=binding.inputNombreTxt.text.toString()
            if (!nombre.isBlank()) {
                modelo.setNombre(nombre)
                mActivity!!.addUsuarioProfesional()
            }else{
                binding.inputNombreLayout.error="Campo en blanco"
            }
        }

    }



}