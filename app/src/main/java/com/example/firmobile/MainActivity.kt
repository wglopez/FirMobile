package com.example.firmobile

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit

class MainActivity : AppCompatActivity(), InicioSesion.OnBotonClickListener {
    val inicioSesion: InicioSesion = InicioSesion()
    val listaDocumentos: ListaDocumentos=ListaDocumentos()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        replaceFragment(inicioSesion)

        val CUIL= inicioSesion.arguments?.getInt("CUIL")

    }

    fun replaceFragment(fragment: Fragment){
        supportFragmentManager.commit {
            replace(R.id.frameContainer, fragment)
            setReorderingAllowed(true)
            addToBackStack("replacement")
        }
    }


    override fun onBotonClick(data: String) {
        replaceFragment(listaDocumentos)

    }

}