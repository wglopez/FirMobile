package com.example.firmobile

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit

class MainActivity : AppCompatActivity(), SwitchFragment {
    val inicioSesion: InicioSesion = InicioSesion()
    var cuilUsuarioActual:String=""
    val administradorDB=AdministradorDB(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        replaceFragment(inicioSesion)

    }

    override fun replaceFragment(fragment: Fragment){

        cuilUsuarioActual= fragment.arguments?.getString("cuilUsuario").toString()

        supportFragmentManager.commit {
            replace(R.id.frameContainer, fragment)
            setReorderingAllowed(true)
            addToBackStack("replacement")
        }
    }

}