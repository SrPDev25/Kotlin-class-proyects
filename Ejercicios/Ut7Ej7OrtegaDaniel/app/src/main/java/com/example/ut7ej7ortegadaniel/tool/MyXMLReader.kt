package com.example.ut7ej7ortegadaniel.tool

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
    fun parse(inputStream: InputStream): Centro? {
        //Stream
        inputStream.use { inputStream ->
            val parser: XmlPullParser = Xml.newPullParser()
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false)
            parser.setInput(inputStream, null)
            parser.nextTag()
            return readFeed(parser)
        }
    }

    private fun readFeed(parser: XmlPullParser): Centro? {
        //Entry es
        var entries: Centro? =null
        //Marca la raiz
        parser.require(XmlPullParser.START_TAG, ns, "resources")
        //Si el siguiente no existe se rompe el bucle
        while (parser.next() != XmlPullParser.END_TAG) {
            //Si aun no estamos en el tag inicial
            if (parser.eventType != XmlPullParser.START_TAG) {
                continue//termina la iteración del bucle en la que estamos
            }
            // Starts by looking for the entry tag
            if (parser.name == "Data") {
                entries=readEntry(parser)
            } else {
                //skip(parser)//No la comprendo muy bien, no la pongo
            }
        }
        return entries
    }


    private fun readEntry(parser: XmlPullParser):Centro {
        parser.require(XmlPullParser.START_TAG, ns, "Data")
        var centro: String = ""
        var direccion: String = ""
        var denominacion: String = ""
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.eventType != XmlPullParser.START_TAG) {
                continue
            }
            when (parser.name) {
                "Telefono" -> centro = readTelefono(parser)
                "Direccion" -> direccion = readDireccion(parser)
                "Denominacion" -> denominacion = readDenominacion(parser)
                //else -> skip(parser)
            }
        }
        return Centro(centro, direccion, denominacion)
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

    private fun readDenominacion(parser: XmlPullParser): String {
        parser.require(XmlPullParser.START_TAG, ns, "Denominacion")
        val denominacion = readText(parser)
        parser.require(XmlPullParser.END_TAG, ns, "Denominacion")
        return denominacion
    }



    private fun readDireccion(parser: XmlPullParser): String {
        parser.require(XmlPullParser.START_TAG, ns, "Direccion")
        val direccion = readText(parser)
        parser.require(XmlPullParser.END_TAG, ns, "Direccion")
        return direccion
    }

    private fun readTelefono(parser: XmlPullParser): String {
        parser.require(XmlPullParser.START_TAG, ns, "Telefono")
        val centro = readText(parser)
        parser.require(XmlPullParser.END_TAG, ns, "Telefono")
        return centro
    }
}

