package com.example.e3mdortegadaniel

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import com.example.e3mdortegadaniel.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private var primesChache = ArrayList<Int>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //1 is not prime
        primesChache.add(2)
        primesChache.add(3)

        /**
         * Comprueba si el numero primo requerido existe
         * @return devuelve le número primo
         */
        fun inCache(number: Int): Int {
            var result = 0
            if (primesChache.size >= number && number != 0) {
                result = primesChache[number - 1]
            }

            return result
        }

        /**
         * Busca los numeros primos
         * @return devuelve el numero primo
         */
        fun foundNPrime(n: Int): String {
            var thePrime = inCache(n)

            if (thePrime == 0) {
                var count = primesChache.size
                var num = primesChache[count - 1] +1
                var flag: Boolean
                while (count < n) {
                    flag = false
                    for (i in 2..num / 2) {
                        // condition for nonprime number
                        if (num % i == 0) {
                            flag = true
                            break
                        }
                    }
                    if (!flag) {
                        count++
                        thePrime = num
                        primesChache.add(thePrime)
                    }
                    num++
                }
                primesChache.add(n, thePrime)
            }
            return thePrime.toString()
        }

        /**
         * Un listener que comprueba en este casi si el texto cambia
         */
        val watcher: TextWatcher = object : TextWatcher {
            override fun beforeTextChanged(
                s: CharSequence?,
                start: Int,
                count: Int,
                after: Int
            ) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                val text=binding.textInput.text.toString()

                if (text!="") {
                    val pos: Int = text.toInt()
                    if (pos != 0)
                        binding.prime.text = foundNPrime(pos)
                }else{
                    binding.prime.text="0"
                }
            }
        }

        binding.textInput.addTextChangedListener(watcher)


    }
}