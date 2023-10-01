package com.example.firmobile

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast


class InicioSesion : Fragment(){

    private var switchFragment:SwitchFragment?=null
    private lateinit var cuilView: TextView
    private lateinit var btnInicioSesion: Button
    private var cuil:String=""


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val rootView=inflater.inflate(R.layout.fragment_inicio_sesion, container, false)

        //Se asocian elementos del View
        cuilView= rootView.findViewById(R.id.CUIL)
        btnInicioSesion= rootView.findViewById(R.id.btnIniciarSesion)


        //Se definen las acciones del boton Iniciar Sesion
        btnInicioSesion.setOnClickListener{
            cuil = cuilView.text.toString()

            if (Utils().esCUILValido(cuil)){
                val administradorDB=AdministradorDB(requireContext())
                administradorDB.insertUser(cuil)
                val listaDocumentos=ListaDocumentos()
                var datos = Bundle()
                datos.putString("cuilUsuario", cuil)
                listaDocumentos.arguments = datos // Configurar argumentos antes de reemplazar el fragmento
                switchFragment?.replaceFragment(listaDocumentos)

            }
            else{
                Toast.makeText(activity, "CUIL Invalido", Toast.LENGTH_LONG).show()
            }

        }
        // Inflate the layout for this fragment
        return rootView
    }

    //Se asocia la interfaz con el Contexto o la Actividad que tiene cargado el actual fragmento
    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is SwitchFragment) {
            switchFragment = context
        } else {
            throw ClassCastException("$context debe implementar OnBotonClickListener")
        }
    }




}