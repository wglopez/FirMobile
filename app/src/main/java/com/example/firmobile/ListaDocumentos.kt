package com.example.firmobile

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.Intent.ACTION_GET_CONTENT
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemClickListener
import android.widget.Button
import android.widget.ListAdapter
import android.widget.ListView
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import java.io.File


/**
 * A simple [Fragment] subclass.
 * Use the [ListaDocumentos.newInstance] factory method to
 * create an instance of this fragment.
 */
class ListaDocumentos() : Fragment(), AdapterView.OnItemClickListener {

    private lateinit var listView: ListView
    private lateinit var btnAgregar:Button
    private lateinit var btnCerrarSesion:Button
    private var listaDocumentos = arrayListOf<Documento>()
    private lateinit var administradorDB:AdministradorDB
    private lateinit var adaptador:AdapterDocumento
    private var switchFragment:SwitchFragment?=null


    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is SwitchFragment) {
            switchFragment = context
        }
    }

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView= inflater.inflate(R.layout.fragment_lista_documentos, container, false)
        val cuilUsuario= requireArguments().getString("cuilUsuario")


        //Instanciamos la Base de Datos
        administradorDB=AdministradorDB(requireContext())


        //Se cargan los documentos asociados al usuario actual
        listaDocumentos = administradorDB.getAllDocuments(cuilUsuario.toString()) ?: ArrayList()

        //Se asocian elementos del View
        btnAgregar=rootView.findViewById(R.id.btnAgregar)
        btnCerrarSesion=rootView.findViewById(R.id.btnCerrarSesion)
        listView=rootView.findViewById(R.id.documentos)

        //Se rellena el Listview
        adaptador = AdapterDocumento(requireContext(), listaDocumentos)
        listView.adapter = adaptador
        listView.setOnItemClickListener(this)


        //Funcion del boton Agregar
        btnAgregar.setOnClickListener{
            val fileIntent= Intent(ACTION_GET_CONTENT)
            fileIntent.setType("*/*")
            startActivityForResult(fileIntent, 10)
        }


        //Funcion del boton Cerrar sesion
        btnCerrarSesion.setOnClickListener{
            switchFragment?.replaceFragment(Utils().fragmentInicioSesion())
        }


        return rootView
    }


    //Es la funcion que se llama al cargar un archivo
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode ==10  && resultCode == Activity.RESULT_OK && data != null){
            val selectedFileUri: Uri? = data?.data
            val filePath: String? = selectedFileUri?.path
            val file = File(filePath)
            val fileName = file.name // Obtiene el nombre del archivo (sin la ruta)
            val fileExtension = fileName?.substringAfterLast(".").toString()

            //Se consulta si el formato del archivo es valido
            if(Utils().esArchivoValido(fileExtension)){

                //Se obtiene la imagen con la que se debe mostrar en la aplicacion
                val image=Utils().getImage(fileExtension)

                //Se crea el objeto documento
                val document=Documento(fileName, filePath.toString(), fileExtension,image)
                document.id=listaDocumentos.size

                //Se agrega el documento nuevo en la base de datos asociado al usuario actual
                administradorDB.insertDocumento(document, arguments?.getString("cuilUsuario"))

                //Se agrega el documento nuevo en la lista de documentos pendientes por firmar
                listaDocumentos.add(document)

                //Se actualiza el listview
                adaptador.notifyDataSetChanged()

            }
        }
    }

    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long){
        val documento = adaptador.getItem(position) as Documento
        val detalleDocumento=DetalleDocumento()
        var datos = Bundle()
        datos.putInt("id", documento.id)
        datos.putString("nombre", documento.name)
        datos.putString("tipo", documento.type)
        datos.putString("direccion", documento.direction)
        datos.putInt("imagen", documento.image)
        datos.putString("cuilUsuario", arguments?.getString("cuilUsuario"))
        detalleDocumento.arguments = datos

        switchFragment?.replaceFragment(detalleDocumento)

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                // Aquí puedes manejar el botón de retroceso en el fragmento de detalle de documento
                // Puedes realizar alguna acción o simplemente permitir que el evento siga su curso
                // Si no necesitas hacer nada especial, puedes eliminar este método
            }
        }

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)
    }


    fun onDocumentoBorrado() {
        // Actualizar la lista de documentos después de borrar
        listaDocumentos = administradorDB.getAllDocuments(arguments?.getString("cuilUsuario") ?: "") ?: ArrayList()
        adaptador = AdapterDocumento(requireContext(), listaDocumentos)
        adaptador.notifyDataSetChanged()
        listView.adapter = adaptador
    }

}






