package com.example.ej9ortegadanielbd.view

import android.R
import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.app.TimePickerDialog
import android.os.Bundle
import android.os.Looper
import android.os.SystemClock
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.ej9ortegadanielbd.control.TipoProfesional
import com.example.ej9ortegadanielbd.dataBase.OperacionesDao
import com.example.ej9ortegadanielbd.databinding.FragmentAddCitaBinding
import com.example.ej9ortegadanielbd.vistaModelo.VistaModelo
import com.example.ej9ortegadanielbd.vistaModelo.VistaModeloFactory
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.ForkJoinTask


class AddCitaFragment : Fragment() {

    private lateinit var binding: FragmentAddCitaBinding
    private var mActivity: MainActivity? = null
    private lateinit var bd: OperacionesDao
    private lateinit var modelo: VistaModelo
    private lateinit var lista: MutableList<TipoProfesional>
    private var usuario = 0
    private var error = false


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddCitaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /**Es necesario el contexto tanto para la base de datos como para el recycler
         *Por ello como el fragment no tiene el contexto de una activity se llama al MainActivity de la siguiente manera
         */
        //Cast seguro
        mActivity = activity as? MainActivity
        bd = OperacionesDao(mActivity!!.applicationContext)
        chargeViewModel()

        lista = bd.getTipoProgesional()
        val viewModelFactory = VistaModeloFactory(0)
        modelo =
            ViewModelProvider(this.requireActivity(), viewModelFactory).get(VistaModelo::class.java)
        usuario = modelo.identificador.value!!.toInt()


        //Creo que es un poco guarro, ya que le esconde al adapter que puede no haber contexto
        context?.let {
            val spinnerAdapter = ArrayAdapter(it, R.layout.simple_list_item_1, lista)
            binding.comboTipoProfesional.adapter = spinnerAdapter
        }



        binding.dataPicker.setOnClickListener() {
            chargeDataPicker()

        }

        binding.timePicker.setOnClickListener() {
            chargeTimePicker()
        }

        binding.buttonAddCita.setOnClickListener {
            buttonAction()
        }
    }

    private fun buttonAction() {
        comprobarHora()
        if (!this.error) {
            bd.addCita(
                binding.dataPicker.text.toString(),
                binding.timePicker.text.toString(),
                lista.get(binding.comboTipoProfesional.selectedItemPosition).codigo,
                usuario
            )
            view?.let {
                Snackbar.make(it, "Cita creada exitosamente", Snackbar.LENGTH_LONG).show()
            }

            mActivity?.mostrarCitas()
        }
    }

    private fun chargeViewModel() {
        val viewModelFactory = VistaModeloFactory(0)
        modelo =
            ViewModelProvider(this.requireActivity(), viewModelFactory)[VistaModelo::class.java]
        val observador = Observer<Long> {}
        modelo.identificador.observe(this.viewLifecycleOwner, observador)
    }

    @SuppressLint("SimpleDateFormat")
    private fun chargeTimePicker() {
        val cal = Calendar.getInstance()
        val timeSetListener = TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->
            cal.set(Calendar.HOUR_OF_DAY, hour)
            cal.set(Calendar.MINUTE, minute)
            var hourText = SimpleDateFormat("HH:mm").format(cal.time)
            var minutes = hourText.split(":")[1].toInt()
            minutes -= minutes % 10
            hourText = if (minutes != 0)
                hourText.split(":")[0] + ":" + minutes
            else
                hourText.split(":")[0] + ":00"
            binding.timePicker.setText(hourText)
        }


        TimePickerDialog(
            this.context,
            timeSetListener,
            cal.get(Calendar.HOUR_OF_DAY),
            cal.get(Calendar.MINUTE),
            true
        ).show()
    }

    fun comprobarHora() {
        val hour = binding.timePicker.text.toString()
        val hourSplited = hour.split(":")
        val date = Date(System.currentTimeMillis())
        this.error = false
        val fecha = binding.dataPicker.text.toString()
        val fechaHoy = "${date.date}/${date.month + 1}/${date.year + 1900}"

        if (fecha == fechaHoy) {
            if (hourSplited[0].toInt() < date.hours) {
                binding.timePickerLayout.error = "Hora pasada"
                this.error = true
            } else if (hourSplited[1].toInt() < date.minutes && hourSplited[0].toInt() == date.hashCode()) {
                binding.timePickerLayout.error = "Hora pasada"
                this.error = true
            }
        }

        //Comprueba si el profesional del usuario está ocupado
        if (!fecha.isBlank() && bd.isProfesionalOcupado(
                fecha,
                hour,
                usuario,
                lista.get(binding.comboTipoProfesional.selectedItemPosition).codigo
            )
        ) {
            binding.timePickerLayout.error = "Profesional ocupado"
            this.error = true
        } else if (binding.dataPicker.text.toString() == "" || binding.timePicker.text.toString() == "") {
            binding.timePickerLayout.error = "Faltan datos"
            this.error = true
        } else if(!error)
            binding.timePickerLayout.error = ""
    }

    private fun chargeDataPicker() {
        //Dia de hoy
        val c = Calendar.getInstance()
        //El día de hoy
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)


        val datePickerDialog = this.context?.let { it1 ->
            DatePickerDialog(
                // it se refiere al mismo objeto, a partir de aquí se indicarán el:
                it1, { view, year, monthOfYear, dayOfMonth ->
                    // Acción del view
                    val dat = (dayOfMonth.toString() + "/" + (monthOfYear + 1) + "/" + year)
                    binding.dataPicker.setText(dat)
                },
                // Insertar datos del dia, mes y año actual
                year, month, day
            )

        }

        datePickerDialog!!.datePicker.minDate = System.currentTimeMillis()

        // Muestra el picker
        datePickerDialog.show()

    }

}







