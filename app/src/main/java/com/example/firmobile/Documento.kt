package com.example.firmobile

class Documento(val name: String, val direction: String, val type: String, val image:Int) {



    fun esValido():Boolean{
        return !(type!="pdf" && type!="odt" && type!="doc" && type!="docx")
    }
}