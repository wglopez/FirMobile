package com.example.firmobile

import android.os.Bundle

class Utils {

    fun getImage(type:String):Int{
        when(type){
            "pdf"->return R.drawable.pdf_icon
            "odt"->return R.drawable.odt_icon
            "doc"->return R.drawable.doc_icon
            "docx"->return R.drawable.docx_icon

            else->return 0
        }
    }


    fun esArchivoValido(type:String):Boolean{
        return !(type!="pdf" && type!="odt" && type!="doc" && type!="docx")
    }


    fun esCUILValido(cuil: String): Boolean {
        // Verificar si el CUIL tiene 11 dígitos
        if (cuil.length != 11)
            return false

        // Verificar si todos los caracteres son dígitos
        if (!cuil.all { it.isDigit() })
            return false

        // Verificar la condición de sufijo
        val sufijo = cuil.last().toString().toInt()
        if (sufijo < 0 || sufijo > 9)
            return false

        return true
    }


    fun fragmentInicioSesion():InicioSesion{
        val inicioSesion=InicioSesion()
        var datos = Bundle()
        datos.putString("cuilUsuario", "")
        inicioSesion.arguments = datos
        return inicioSesion
    }


    fun fragmentListaDocumentos(cuil: String?):ListaDocumentos{
        val listaDocumentos=ListaDocumentos()
        var datos = Bundle()
        datos.putString("cuilUsuario", cuil)
        listaDocumentos.arguments = datos
        return listaDocumentos
    }

}