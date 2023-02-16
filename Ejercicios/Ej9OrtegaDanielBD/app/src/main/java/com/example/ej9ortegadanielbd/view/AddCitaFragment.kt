package com.example.ej9ortegadanielbd.view

import android.R
import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.ej9ortegadanielbd.dataBase.OperacionesDao
import com.example.ej9ortegadanielbd.databinding.FragmentAddCitaBinding
import com.example.ej9ortegadanielbd.vistaModelo.VistaModelo
import com.example.ej9ortegadanielbd.vistaModelo.VistaModeloFactory
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import java.text.SimpleDateFormat
import java.util.*


class AddCitaFragment : Fragment() {

    private lateinit var binding: FragmentAddCitaBinding
    private var mActivity: MainActivity? = null
    private lateinit var bd: OperacionesDao
    private lateinit var modelo: VistaModelo
    private lateinit var dateEdt: EditText


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

        val viewModelFactory = VistaModeloFactory(0)
        modelo =
            ViewModelProvider(this.requireActivity(), viewModelFactory)[VistaModelo::class.java]
        val observador = Observer<Long> {}
        modelo.identificador.observe(this.viewLifecycleOwner, observador)


        binding.dataPicker.setOnClickListener() {
            // on below line we are getting
            // the instance of our calendar.
            val c = Calendar.getInstance()

            // on below line we are getting
            // our day, month and year.
            val year = c.get(Calendar.YEAR)
            val month = c.get(Calendar.MONTH)
            val day = c.get(Calendar.DAY_OF_MONTH)

            // on below line we are creating a
            // variable for date picker dialog.
            val datePickerDialog = this.context?.let { it1 ->
                DatePickerDialog(
                    // on below line we are passing context.
                    it1, { view, year, monthOfYear, dayOfMonth ->
                        // on below line we are setting
                        // date to our edit text.
                        val dat = (dayOfMonth.toString() + "-" + (monthOfYear + 1) + "-" + year)
                        binding.dataPicker.setText(dat)
                    },
                    // on below line we are passing year, month
                    // and day for the selected date in our date picker.
                    year, month, day
                )

            }
            datePickerDialog!!.datePicker.minDate = System.currentTimeMillis()

            // at last we are calling show
            // to display our date picker dialog.
            datePickerDialog?.show()
        }
        binding.timePicker.setOnClickListener() {
            val cal = Calendar.getInstance()
            val timeSetListener = TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->
                cal.set(Calendar.HOUR_OF_DAY, hour)
                cal.set(Calendar.MINUTE, minute)
                binding.timePicker.setText(SimpleDateFormat("HH:mm").format(cal.time))
            }


            TimePickerDialog(
                this.context,
                timeSetListener,
                cal.get(Calendar.HOUR_OF_DAY),
                cal.get(Calendar.MINUTE),
                true
            ).show()


            /*val timePicker=this.context?.let { it1 ->
                MaterialTimePicker.Builder()
                    .setTimeFormat(TimeFormat.CLOCK_12H)
                    .setHour(12)
                    .setMinute(10)
                    .setTitleText("Select Appointment time")
                    .build()*/
        }

    }

}







