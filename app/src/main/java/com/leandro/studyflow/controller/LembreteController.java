package com.leandro.studyflow.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.leandro.studyflow.database.DBHelper;
import com.leandro.studyflow.model.Lembrete;

import java.util.ArrayList;
import java.util.List;

public class LembreteController {
    private SQLiteDatabase db;
    private DBHelper dbHelper;
    public LembreteController(Context context) {
        dbHelper = new DBHelper(context);
    }
    public long salvarLembrete(Lembrete lembrete) {
        db = dbHelper.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put("titulo", lembrete.getTitulo());
        valores.put("mensagem", lembrete.getMensagem());
        valores.put("data_hora", lembrete.getDataHora());

        long id = db.insert("lembretes", null, valores);
        db.close();
        return id;
    }
    public List<Lembrete> listarLembretes() {
        List<Lembrete> lista = new ArrayList<>();
        db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM lembretes", null);
        if (cursor.moveToFirst()) {
            do {
                Lembrete l = new Lembrete(
                        cursor.getInt(cursor.getColumnIndexOrThrow("id")),
                        cursor.getString(cursor.getColumnIndexOrThrow("titulo")),
                        cursor.getString(cursor.getColumnIndexOrThrow("mensagem")),
                        cursor.getString(cursor.getColumnIndexOrThrow("data_hora"))
                );
                lista.add(l);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return lista;
    }
    public void excluirLembrete(int id) {
        db = dbHelper.getWritableDatabase();
        db.delete("lembretes", "id = ?", new String[]{String.valueOf(id)});
        db.close();
    }
}