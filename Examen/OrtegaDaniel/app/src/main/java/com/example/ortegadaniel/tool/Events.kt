package com.example.ortegadaniel.tool

interface Events {

    fun shortClick(codigo:Int)
    fun longClick(codigo:Int):Boolean
}