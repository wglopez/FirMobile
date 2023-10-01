package com.example.firmobile

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit

class MainActivity : AppCompatActivity(), SwitchFragment {
    val inicioSesion: InicioSesion = InicioSesion()
    var cuilUsuarioActual:String=""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        replaceFragment(Utils().fragmentInicioSesion())

    }

    override fun replaceFragment(fragment: Fragment){

        cuilUsuarioActual= fragment.arguments?.getString("cuilUsuario").toString()

        supportFragmentManager.beginTransaction()
            .replace(R.id.frameContainer, fragment)
            .addToBackStack(null)
            .commit()
    }


}