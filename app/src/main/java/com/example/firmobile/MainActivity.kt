package com.example.firmobile

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit

class MainActivity : AppCompatActivity(), LoginInterface {
    val inicioSesion: InicioSesion = InicioSesion()
    var cuilUsuarioActual:String=""
    val administradorDB=AdministradorDB(this)

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


    override fun onLoginButtonClicked(cuil: String) {
        cuilUsuarioActual=cuil
        administradorDB.insertUser(cuil)
        val listaDocumentos=ListaDocumentos()
        var datos = Bundle()
        datos.putString("cuilUsuario", cuilUsuarioActual)
        listaDocumentos.arguments = datos // Configurar argumentos antes de reemplazar el fragmento
        replaceFragment(listaDocumentos)
    }

    override fun onLogoutButtonClicked() {
        replaceFragment(InicioSesion())

    }

}