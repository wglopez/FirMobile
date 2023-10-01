package com.example.firmobile

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


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView=inflater.inflate(R.layout.fragment_detalle_documento, container, false)

        var name=rootView.findViewById<TextView>(R.id.nameDocument)
        var type=rootView.findViewById<TextView>(R.id.formatoDocumento)
        var direction=rootView.findViewById<TextView>(R.id.directionDocument)
        var image=rootView.findViewById<ImageView>(R.id.imagenDocumento)
        var btnBorrar=rootView.findViewById<Button>(R.id.btnBorrar)
        var btnFirmar=rootView.findViewById<Button>(R.id.btnFirmar)

        name.setText(arguments?.getString("nombre"))
        type.setText(arguments?.getString("tipo"))
        direction.setText(arguments?.getString("direccion"))
        val imageType=Utils().getImage(type.text.toString())
        image.setImageResource(imageType)


        return rootView
    }

}