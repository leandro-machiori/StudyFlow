package com.leandro.studyflow.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "studyflow.db";
    private static final int DATABASE_VERSION = 1;
    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        // Tabela de usuários
        db.execSQL("CREATE TABLE usuarios (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nome TEXT NOT NULL," +
                "email TEXT NOT NULL UNIQUE," +
                "senha TEXT NOT NULL)");

        // Tabela de matérias
        db.execSQL("CREATE TABLE materias (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nome TEXT NOT NULL," +
                "descricao TEXT)");
        // Tabela de cronograma
        db.execSQL("CREATE TABLE cronograma (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "materia_id INTEGER NOT NULL," +
                "data_estudo TEXT NOT NULL," +
                "horario TEXT NOT NULL," +
                "FOREIGN KEY(materia_id) REFERENCES materias(id))");

        // Tabela de lembretes
        db.execSQL("CREATE TABLE lembretes (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "titulo TEXT NOT NULL," +
                "mensagem TEXT NOT NULL," +
                "data_hora TEXT NOT NULL)");
        // Tabela de progresso
        db.execSQL("CREATE TABLE progresso (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "materia_id INTEGER NOT NULL," +
                "conteudo TEXT NOT NULL," +
                "status TEXT NOT NULL," +
                "FOREIGN KEY(materia_id) REFERENCES materias(id))");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Remove as tabelas antigas (caso mude a versão)
        db.execSQL("DROP TABLE IF EXISTS progresso");
        db.execSQL("DROP TABLE IF EXISTS lembretes");
        db.execSQL("DROP TABLE IF EXISTS cronograma");
        db.execSQL("DROP TABLE IF EXISTS materias");
        db.execSQL("DROP TABLE IF EXISTS usuarios");
        onCreate(db);
    }
}