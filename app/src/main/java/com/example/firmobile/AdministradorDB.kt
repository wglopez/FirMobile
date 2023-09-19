package com.example.firmobile


import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class AdministradorDB(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "FirMobile.db"
        private const val DATABASE_VERSION = 1
    }

    override fun onCreate(db: SQLiteDatabase) {
        // Crear la estructura de la base de datos
        db.execSQL(
            "CREATE TABLE Usuarios (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "CUIL TEXT) "
        )

        db.execSQL(
            "CREATE TABLE Documentos (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "nombre TEXT, " +
                    "direccion TEXT, " +
                    "tipo TEXT, " +
                    "usuario_id INTEGER," +
                    "FOREIGN KEY (usuario_id) REFERENCES Usuarios (id))"
        )
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // Manejar las actualizaciones de la base de datos aquí
    }
}


