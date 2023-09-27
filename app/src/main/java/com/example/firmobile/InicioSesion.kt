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

    interface OnBotonClickListener {
        fun onBotonClick(data: String)
    }

    private var botonClickListener: OnBotonClickListener? = null
    private lateinit var cuilView: TextView
    private lateinit var btnInicioSesion: Button
    private var cuil:String=""



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val rootView=inflater.inflate(R.layout.fragment_inicio_sesion, container, false)

        cuilView= rootView.findViewById(R.id.CUIL)
        btnInicioSesion= rootView.findViewById(R.id.btnIniciarSesion)
        btnInicioSesion.setOnClickListener{
            cuil = cuilView.text.toString()

            if (esCUILValido(cuil)){

                botonClickListener?.onBotonClick(cuil)
            }
            else{
                Toast.makeText(activity, "CUIL Invalido", Toast.LENGTH_LONG).show()
            }

        }
        // Inflate the layout for this fragment
        return rootView
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnBotonClickListener) {
            botonClickListener = context
        } else {
            throw ClassCastException("$context debe implementar OnBotonClickListener")
        }
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

        // Realizar cualquier otra verificación necesaria, como el formato del prefijo y el número de documento

        // Si todas las verificaciones pasan, el CUIL es válido
        return true
    }

}