package com.leandro.studyflow.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.leandro.studyflow.database.DBHelper;
import com.leandro.studyflow.model.Usuario;

public class UsuarioController {
    private SQLiteDatabase db;
    private DBHelper dbHelper;
    public UsuarioController(Context context) {
        dbHelper = new DBHelper(context);
    }
    public boolean cadastrarUsuario(Usuario usuario) {
        db = dbHelper.getWritableDatabase();

        if (buscarUsuarioPorEmail(usuario.getEmail()) != null) {
            return false;
        }
        ContentValues valores = new ContentValues();
        valores.put("nome", usuario.getNome());
        valores.put("email", usuario.getEmail());
        valores.put("senha", usuario.getSenha());
        long resultado = db.insert("usuarios", null, valores);
        db.close();
        return resultado != -1;
    }
    public Usuario buscarUsuarioPorEmail(String email) {
        db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM usuarios WHERE email = ?", new String[]{email});
        Usuario usuario = null;
        if (cursor.moveToFirst()) {
            usuario = new Usuario(
                    cursor.getInt(cursor.getColumnIndexOrThrow("id")),
                    cursor.getString(cursor.getColumnIndexOrThrow("nome")),
                    cursor.getString(cursor.getColumnIndexOrThrow("email")),
                    cursor.getString(cursor.getColumnIndexOrThrow("senha"))
            );
        }
        cursor.close();
        db.close();
        return usuario;
    }
    public boolean validarLogin(String email, String senha) {
        Usuario usuario = buscarUsuarioPorEmail(email);
        return usuario != null && usuario.getSenha().equals(senha);
    }
}