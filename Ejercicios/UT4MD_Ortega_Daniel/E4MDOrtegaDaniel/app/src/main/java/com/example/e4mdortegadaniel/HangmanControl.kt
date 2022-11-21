package com.example.e4mdortegadaniel

import java.util.*
import kotlin.collections.ArrayList

class HangmanControl {
    var random=Random()
    var palabras=ArrayList<String>()
    var wordInGame:String=""
    var wordInProgress=ArrayList<Char>()

    init {
        chargeStrings()
    }

    /**
     * Resetea la partida e inicia con una palabra nueva
     */
    fun startGame():String{
        wordInGame=palabras.get((0..palabras.size-1).random().toInt())
        var lines=""
        wordInProgress= ArrayList<Char>()
        for (i in wordInGame){
            lines+="_ "
            wordInProgress.add('_')
        }
        return lines
    }

    /**
     * Comprueba si una letra está en la palabra y la guarda en el progreso
     */
    fun checkWord(letter:Char):Int{
        var result=-1
        for (i in 0..wordInGame.length-1){
            if (wordInGame.get(i)==letter){
                result=0
                wordInProgress.set(i,letter)
            }
        }
        return result
    }

    /**
     * Devuelve un String para ser implementado en el texto de la partida
     */
    fun resetLines():String{
        var word=""
        for (i in wordInProgress){
            word+= i+" "
        }
        return word
    }

    /**
     * genera las palabras con las que se ván a jugar
     */
    fun chargeStrings(){
        palabras.add("meseta")
        palabras.add("interludio")
        palabras.add("perrito")
        palabras.add("gatete")
        palabras.add("hojaldre")
        palabras.add("mansalva")
        palabras.add("mesopotamia")
        palabras.add("sap")
        palabras.add("otorrino")
    }
}