package com.example.e3mdortegadaniel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.core.widget.addTextChangedListener
import com.example.e3mdortegadaniel.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    var primesChache = ArrayList<Int>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        primesChache.add(1)
        primesChache.add(2)
        primesChache.add(3)

        fun inCache(n: String): Int {
            var result = 0
            if (n != "" && primesChache.size >= n.toInt()) {
                result = primesChache[n.toInt() - 1]
            }
            return result
        }

        fun foundNPrime(n: String): String {
            var thePrime = inCache(n)


            if (n != "" && thePrime == 0) {
                var count = primesChache.size
                var num = primesChache[count - 1] + 1
                var flag = false
                val nUsing = n.toInt()
                while (count < nUsing) {
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

            }
            return thePrime.toString()
        }

        var watcher: TextWatcher = object : TextWatcher {
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
                val text = binding.textInput.text.toString()
                if (text != "0")
                    binding.prime.text = foundNPrime(text)
            }
        }

        binding.textInput.addTextChangedListener(watcher)


    }
}