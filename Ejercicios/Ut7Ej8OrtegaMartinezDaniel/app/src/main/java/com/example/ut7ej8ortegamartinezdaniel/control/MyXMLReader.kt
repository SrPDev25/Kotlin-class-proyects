package com.example.ut7ej8ortegamartinezdaniel.control

import android.util.Xml
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserException
import java.io.IOException
import java.io.InputStream

//Ni idea, es un nulo que nunca cambia
private val ns: String? = null
class MyXMLReader {
    //Posibles errores los deja para quien llame a la funcion
    @Throws(XmlPullParserException::class, IOException::class)
    fun parse(inputStream: InputStream):  MutableList<Usuario> {
        //Stream
        inputStream.use { inputStream ->
            val parser: XmlPullParser = Xml.newPullParser()
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false)
            parser.setInput(inputStream, null)
            parser.nextTag()
            return readFeed(parser)
        }
    }

    private fun readFeed(parser: XmlPullParser): MutableList<Usuario> {
        //Entry es
        var entries= mutableListOf<Usuario>()
        //Marca la raiz
        parser.require(XmlPullParser.START_TAG, ns, "resources")
        //Si el siguiente no existe se rompe el bucle
        while (parser.next() != XmlPullParser.END_TAG) {
            //Si aun no estamos en el tag inicial
            if (parser.eventType != XmlPullParser.START_TAG) {
                continue//termina la iteración del bucle en la que estamos
            }
            // Starts by looking for the entry tag
            if (parser.name == "usuario") {
                entries.add(readEntry(parser))
            } else {
                //skip(parser)//No la comprendo muy bien, no la pongo
            }
        }
        return entries
    }


    private fun readEntry(parser: XmlPullParser):Usuario {
        parser.require(XmlPullParser.START_TAG, ns, "Data")
        var login: String = ""
        var contra: String = ""
        var perfil: String = ""
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.eventType != XmlPullParser.START_TAG) {
                continue
            }
            when (parser.name) {
                "login" -> login = readLogin(parser)
                "contra" -> contra = readContra(parser)
                "perfil" -> perfil = readPerfil(parser)
                //else -> skip(parser)
            }
        }
        return Usuario(login, contra, perfil)
    }

    //Final de la matriosca
    private fun readText(parser: XmlPullParser): String {
        var result = ""
        if (parser.next() == XmlPullParser.TEXT) {
            result = parser.text
            parser.nextTag()
        }
        return result
    }

    private fun readPerfil(parser: XmlPullParser): String {
        parser.require(XmlPullParser.START_TAG, ns, "perfil")
        val denominacion = readText(parser)
        parser.require(XmlPullParser.END_TAG, ns, "perfil")
        return denominacion
    }



    private fun readContra(parser: XmlPullParser): String {
        parser.require(XmlPullParser.START_TAG, ns, "contra")
        val direccion = readText(parser)
        parser.require(XmlPullParser.END_TAG, ns, "contra")
        return direccion
    }

    private fun readLogin(parser: XmlPullParser): String {
        parser.require(XmlPullParser.START_TAG, ns, "login")
        val centro = readText(parser)
        parser.require(XmlPullParser.END_TAG, ns, "login")
        return centro
    }
}

