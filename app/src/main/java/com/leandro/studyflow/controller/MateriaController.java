package com.leandro.studyflow.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.leandro.studyflow.database.DBHelper;
import com.leandro.studyflow.model.Materia;
import java.util.ArrayList;
import java.util.List;

public class MateriaController {
    private SQLiteDatabase db;
    private DBHelper dbHelper;
    public MateriaController(Context context) {
        dbHelper = new DBHelper(context);
    }
    public long salvarMateria(Materia materia) {
        db = dbHelper.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put("nome", materia.getNome());
        valores.put("descricao", materia.getDescricao());
        long id = db.insert("materias", null, valores);
        db.close();
        return id;
    }
    public List<Materia> listarMaterias() {
        List<Materia> lista = new ArrayList<>();
        db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM materias", null);
        if (cursor.moveToFirst()) {
            do {
                Materia materia = new Materia(
                        cursor.getInt(cursor.getColumnIndexOrThrow("id")),
                        cursor.getString(cursor.getColumnIndexOrThrow("nome")),
                        cursor.getString(cursor.getColumnIndexOrThrow("descricao"))
                );
                lista.add(materia);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return lista;
    }
    public void atualizarMateria(Materia materia) {
        db = dbHelper.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put("nome", materia.getNome());
        valores.put("descricao", materia.getDescricao());
        db.update("materias", valores, "id = ?", new String[]{String.valueOf(materia.getId())});
        db.close();
    }
    public void excluirMateria(int id) {
        db = dbHelper.getWritableDatabase();
        db.delete("materias", "id = ?", new String[]{String.valueOf(id)});
        db.close();
    }
}