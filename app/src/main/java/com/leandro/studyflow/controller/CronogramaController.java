package com.leandro.studyflow.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.leandro.studyflow.database.DBHelper;
import com.leandro.studyflow.model.Cronograma;
import java.util.ArrayList;
import java.util.List;

public class CronogramaController {
    private SQLiteDatabase db;
    private DBHelper dbHelper;
    public CronogramaController(Context context) {
        dbHelper = new DBHelper(context);
    }
    public long salvarCronograma(Cronograma cronograma) {
        db = dbHelper.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put("materia_id", cronograma.getMateriaId());
        valores.put("data_estudo", cronograma.getDataEstudo());
        valores.put("horario", cronograma.getHorario());
        long id = db.insert("cronograma", null, valores);
        db.close();
        return id;
    }
    public List<Cronograma> listarCronogramas() {
        List<Cronograma> lista = new ArrayList<>();
        db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM cronograma", null);
        if (cursor.moveToFirst()) {
            do {
                Cronograma c = new Cronograma(
                        cursor.getInt(cursor.getColumnIndexOrThrow("id")),
                        cursor.getInt(cursor.getColumnIndexOrThrow("materia_id")),
                        cursor.getString(cursor.getColumnIndexOrThrow("data_estudo")),
                        cursor.getString(cursor.getColumnIndexOrThrow("horario"))
                );
                lista.add(c);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return lista;
    }
    public void excluirCronograma(int id) {
        db = dbHelper.getWritableDatabase();
        db.delete("cronograma", "id = ?", new String[]{String.valueOf(id)});
        db.close();
    }
}