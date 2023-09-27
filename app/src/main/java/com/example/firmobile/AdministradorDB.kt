package com.example.firmobile


import android.content.ContentValues
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
        //Se elimina la versión anterior de la tabla

        //Se elimina la versión anterior de la tabla
        db.execSQL("DROP TABLE IF EXISTS Usuarios")
        db.execSQL("DROP TABLE IF EXISTS Documentos")
        //Se crea la nueva versión de la tabla
        onCreate(db)
    }


    fun insertUser(cuil:String):Int{
        return writableDatabase.use { db ->
            val registro = ContentValues()
            registro.put("CUIL", cuil)
            db.insert("Usuarios", null, registro).toInt()
        }
    }

    fun insertDocumento(name:String, direction: String, type:String, idUsuario: Int) {
        try {
            val db = writableDatabase
            val registro = ContentValues()
            registro.put("nombre", name)
            registro.put("direccion", direction)
            registro.put("tipo",type)
            registro.put("usuario_id",idUsuario)
            db.insert("Documentos", null, registro)
            db.close()
        } catch (e: Exception) {
            e.toString()
        }
    }

    fun insertDocumento(documento: Documento, idUsuario:Int) {
        insertDocumento(documento.name, documento.direction, documento.type, idUsuario)
    }


    fun deleteDocument(where: String) {
        val db = writableDatabase
        db.delete("Documentos", where, null)
    }



    fun getAllDocuments(idUsuario: Int?): ArrayList<Documento>? {
        val db = writableDatabase
        val cursor = db.rawQuery(" SELECT * FROM Documentos " + "WHERE usuario_id="+idUsuario, null)
        var listaDocumentos= ArrayList<Documento>()
        var document: Documento
        var name: String
        var id: Int
        var direction: String
        var type:String


        // Nos aseguramos de que existe al menos un registro:
        if (cursor.moveToFirst()) {
            // Recorremos el cursor hasta que no haya mas registros:
            do {
                // Voy obteniendo los datos:
                id = cursor.getInt(0)
                name = cursor.getString(1)
                direction = cursor.getString(2)
                type = cursor.getString(3)
                document = Documento(name, direction, type, getImage(type))
                document.id=id
                listaDocumentos.add(document)
            } while (cursor.moveToNext())
            cursor.close()
            db.close()
            return listaDocumentos
        }
        else return null

    }

    fun deleteUsers(){
        val db = writableDatabase
        db.execSQL("DELETE FROM Usuarios")
    }

    fun getImage(type:String):Int{
        when(type){
            "pdf"->return R.drawable.pdf_icon
            "odt"->return R.drawable.odt_icon
            "doc"->return R.drawable.doc_icon
            "docx"->return R.drawable.docx_icon

            else->return 0
        }
    }

}


