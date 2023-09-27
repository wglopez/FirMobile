package com.example.firmobile

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView



class AdapterDocumento(private val contexto: Context, private val listaDocumentos: ArrayList<Documento>) : ArrayAdapter<Documento>(contexto, 0, listaDocumentos) {
    private val inflater: LayoutInflater = LayoutInflater.from(contexto)


    override fun getCount(): Int {
        return listaDocumentos.size
    }

    override fun getItem(i: Int):Documento {
        return listaDocumentos[i]
    }

    override fun getItemId(i: Int): Long {
        return i.toLong()
    }


    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val layout=LayoutInflater.from(contexto).inflate(R.layout.documento,parent, false)

        val documento=listaDocumentos[position]
        layout.findViewById<ImageView>(R.id.imagenDocumento).setImageResource(documento.image)
        layout.findViewById<TextView>(R.id.nombreDocumento).setText(documento.name)
        layout.findViewById<TextView>(R.id.formatoDocumento).setText(documento.type)

        return layout

    }
}