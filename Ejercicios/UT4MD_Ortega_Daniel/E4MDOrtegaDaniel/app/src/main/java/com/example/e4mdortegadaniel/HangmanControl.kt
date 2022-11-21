package com.example.e4mdortegadaniel

import java.util.*
import kotlin.collections.ArrayList

class HangmanControl {
    var random=Random()
    var palabra=ArrayList<String>()
    var wordInGame:String=""
    var wordInProgress=ArrayList<Char>()

    init {
        chargeStrings()
    }

    fun startGame():String{
        wordInGame=palabra.get((0..palabra.size-1).random().toInt())
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

    fun chargeStrings(){
        palabra.add("meseta")
        palabra.add("interludio")
        palabra.add("perrito")
        palabra.add("gatete")
        palabra.add("hojaldre")
        palabra.add("mansalva")
        palabra.add("mesopotamia")
        palabra.add("sap")
        palabra.add("otorrino")
    }
}