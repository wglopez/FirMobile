package com.example.firmobile

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.content.Intent.ACTION_GET_CONTENT
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ListView
import java.io.File


/**
 * A simple [Fragment] subclass.
 * Use the [ListaDocumentos.newInstance] factory method to
 * create an instance of this fragment.
 */
class ListaDocumentos : Fragment() {

    private lateinit var listView: ListView
    private lateinit var btnAgregar:Button
    private lateinit var btnCerrarSesion:Button
    private val listaDocumentos= mutableListOf<Documento>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView= inflater.inflate(R.layout.fragment_lista_documentos, container, false)

        listView=rootView.findViewById(R.id.documentos)
        btnAgregar=rootView.findViewById(R.id.btnAgregar)
        btnCerrarSesion=rootView.findViewById(R.id.btnCerrarSesion)


        listaDocumentos.add(Documento("pruebaDoc.doc", "directorioPrueba/prueba.doc", "Doc", R.drawable.doc_icon));
        listaDocumentos.add(Documento("pruebaDocx.docx", "directorioPrueba/prueba.docx", "Docx", R.drawable.docx_icon))
        listaDocumentos.add(Documento("pruebaPDF.pdf", "directorioPrueba/prueba.pdf", "PDF", R.drawable.pdf_icon));
        listaDocumentos.add(Documento("pruebaODT.odt", "directorioPrueba/prueba.odt", "ODT", R.drawable.odt_icon))




        //        var documentos: ArrayList<Documento> = ArrayList();


//        val adaptador = AdapterDocumento(this, documentos)
//
//
//        val miLista = findViewById<View>(R.id.documentos) as ListView
//        miLista.adapter = adaptador



        btnAgregar.setOnClickListener{
            val fileIntent= Intent(ACTION_GET_CONTENT)
            fileIntent.setType("*/*")
            startActivityForResult(fileIntent, 10)
        }


        return rootView
    }



    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode ==10  && resultCode == Activity.RESULT_OK && data != null){
            val selectedFileUri: Uri? = data?.data
            val filePath: String? = selectedFileUri?.path
            val file = File(filePath)
            val fileName = file.name // Obtiene el nombre del archivo (sin la ruta)
            val fileExtension = fileName?.substringAfterLast(".")

            println(filePath)
            println(fileName)
            println(fileExtension)


        }

    }
}