package com.example.firmobile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ListaDocumentos.newInstance] factory method to
 * create an instance of this fragment.
 */
class ListaDocumentos : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_lista_documentos, container, false)
    }


    //        var documentos: ArrayList<Documento> = ArrayList();
//        documentos.add(Documento(1, "pruebaDoc.doc", "directorioPrueba/prueba.doc", "Doc", R.drawable.doc_icon));
//        documentos.add(Documento(2, "pruebaDocx.docx", "directorioPrueba/prueba.docx", "Docx", R.drawable.docx_icon))
//        documentos.add(Documento(3, "pruebaPDF.pdf", "directorioPrueba/prueba.pdf", "PDF", R.drawable.pdf_icon));
//        documentos.add(Documento(4, "pruebaODT.odt", "directorioPrueba/prueba.odt", "ODT", R.drawable.odt_icon))


//        val adaptador = AdapterDocumento(this, documentos)
//
//
//        val miLista = findViewById<View>(R.id.documentos) as ListView
//        miLista.adapter = adaptador



}