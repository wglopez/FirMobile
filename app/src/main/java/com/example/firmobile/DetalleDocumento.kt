package com.example.firmobile

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [DetalleDocumento.newInstance] factory method to
 * create an instance of this fragment.
 */
class DetalleDocumento : Fragment() {
    private var switchFragment:SwitchFragment?=null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is SwitchFragment) {
            switchFragment = context
        }

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Se infla la vista del fragment actual
        val rootView=inflater.inflate(R.layout.fragment_detalle_documento, container, false)

        //Se asocian los Views
        var name=rootView.findViewById<TextView>(R.id.nameDocument)
        var type=rootView.findViewById<TextView>(R.id.formatoDocumento)
        var direction=rootView.findViewById<TextView>(R.id.directionDocument)
        var image=rootView.findViewById<ImageView>(R.id.imagenDocumento)
        var btnBorrar=rootView.findViewById<Button>(R.id.btnBorrar)
        var btnFirmar=rootView.findViewById<Button>(R.id.btnFirmar)

        //Se setea la informacion del documento seleccionado
        name.setText(arguments?.getString("nombre"))
        type.setText(arguments?.getString("tipo"))
        direction.setText(arguments?.getString("direccion"))
        arguments?.getInt("imagen")?.let { image.setImageResource(it) }


        //Se define la funcion del boton Borrar

        btnBorrar.setOnClickListener {
            val administradorDB = AdministradorDB(requireContext())
            administradorDB.deleteDocument("id=" + arguments?.getInt("id").toString())

            // Notificar a ListaDocumentos que un documento ha sido borrado
            (activity as? ListaDocumentos)?.onDocumentoBorrado()
            switchFragment?.replaceFragment(Utils().fragmentListaDocumentos(arguments?.getString("cuilUsuario")))
        }


        return rootView
    }

}