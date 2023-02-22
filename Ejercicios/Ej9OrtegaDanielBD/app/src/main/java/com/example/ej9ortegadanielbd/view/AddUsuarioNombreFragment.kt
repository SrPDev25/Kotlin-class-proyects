package com.example.ej9ortegadanielbd.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.ej9ortegadanielbd.dataBase.OperacionesDao
import com.example.ej9ortegadanielbd.databinding.FragmentAddUsuarioNombreBinding

class AddUsuarioNombreFragment : Fragment() {

    private lateinit var binding:FragmentAddUsuarioNombreBinding
    private var mActivity: MainActivity? = null
    private lateinit var bd: OperacionesDao

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentAddUsuarioNombreBinding.inflate(inflater,container,false)
        return binding.root
    }




}