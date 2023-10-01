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

    private var loginInterface:LoginInterface?=null
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

                loginInterface?.onLoginButtonClicked(cuil)
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
        if (context is LoginInterface) {
            loginInterface = context
        } else {
            throw ClassCastException("$context debe implementar OnBotonClickListener")
        }
    }




}