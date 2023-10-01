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
import androidx.activity.OnBackPressedCallback


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
                //Se instancia la base de datos
                val administradorDB=AdministradorDB(requireContext())

                //Se inserta el usuario en caso que no este
                administradorDB.insertUser(cuil)

                //Se llama a la interfaz para mostrar la lista de documentos asociados a ese usuario
                switchFragment?.replaceFragment(Utils().fragmentListaDocumentos(cuil))

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

}