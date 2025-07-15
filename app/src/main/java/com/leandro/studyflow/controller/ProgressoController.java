package com.leandro.studyflow.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.leandro.studyflow.database.DBHelper;
import com.leandro.studyflow.model.Progresso;
import java.util.ArrayList;
import java.util.List;

public class ProgressoController {
    private SQLiteDatabase db;
    private DBHelper dbHelper;
    public ProgressoController(Context context) {
        dbHelper = new DBHelper(context);
    }
    public long salvarProgresso(Progresso progresso) {
        db = dbHelper.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put("materia_id", progresso.getMateriaId());
        valores.put("conteudo", progresso.getConteudo());
        valores.put("status", progresso.getStatus());
        long id = db.insert("progresso", null, valores);
        db.close();
        return id;
    }
    public List<Progresso> listarProgresso() {
        List<Progresso> lista = new ArrayList<>();
        db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM progresso", null);
        if (cursor.moveToFirst()) {
            do {
                Progresso p = new Progresso(
                        cursor.getInt(cursor.getColumnIndexOrThrow("id")),
                        cursor.getInt(cursor.getColumnIndexOrThrow("materia_id")),
                        cursor.getString(cursor.getColumnIndexOrThrow("conteudo")),
                        cursor.getString(cursor.getColumnIndexOrThrow("status"))
                );
                lista.add(p);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return lista;
    }
    public void atualizarStatus(int id, String novoStatus) {
        db = dbHelper.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put("status", novoStatus);
        db.update("progresso", valores, "id = ?", new String[]{String.valueOf(id)});
        db.close();
    }
}