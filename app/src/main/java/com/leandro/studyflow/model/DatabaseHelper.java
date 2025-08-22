package com.leandro.studyflow.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "studyflow.db";
    private static final int DB_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Criação da tabela de usuários
        db.execSQL("CREATE TABLE usuario (id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nome TEXT, email TEXT, senha TEXT)");

        // Criação da tabela de matérias
        db.execSQL("CREATE TABLE materia (id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nome TEXT, descricao TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Apaga as tabelas e recria em caso de atualização
        db.execSQL("DROP TABLE IF EXISTS usuario");
        db.execSQL("DROP TABLE IF EXISTS materia");
        onCreate(db);
    }

    // ---------------- Métodos para Usuário -----------------

    /**
     * Insere um novo usuário no banco de dados.
     *
     * @param u O objeto Usuario a ser inserido.
     * @return O ID da linha inserida, ou -1 em caso de erro.
     */
    public long insertUsuario(Usuario u) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("nome", u.getNome());
        cv.put("email", u.getEmail());
        cv.put("senha", u.getSenha());
        long result = db.insert("usuario", null, cv);
        db.close();
        return result;
    }

    /**
     * Busca um usuário pelo email e senha para o login.
     *
     * @param email O email do usuário.
     * @param senha A senha do usuário.
     * @return O objeto Usuario se encontrado, ou null caso contrário.
     */
    public Usuario getUsuario(String email, String senha) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM usuario WHERE email=? AND senha=?",
                new String[]{email, senha});
        Usuario u = null;
        if (c.moveToFirst()) {
            u = new Usuario(
                    c.getInt(0),
                    c.getString(1),
                    c.getString(2),
                    c.getString(3)
            );
        }
        c.close();
        db.close();
        return u;
    }

    /**
     * Verifica se um email já existe no banco de dados.
     *
     * @param email O email a ser verificado.
     * @return true se o email já existe, false caso contrário.
     */
    public boolean verificarEmailExistente(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT 1 FROM usuario WHERE email=?", new String[]{email});
        boolean exists = c.moveToFirst();
        c.close();
        db.close();
        return exists;
    }

    /**
     * NOVO MÉTODO: Busca um usuário pelo email.
     * Necessário para a tela de recuperação de senha.
     *
     * @param email O email do usuário.
     * @return O objeto Usuario se encontrado, ou null caso contrário.
     */
    public Usuario getUsuarioPorEmail(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM usuario WHERE email=?",
                new String[]{email});
        Usuario u = null;
        if (c.moveToFirst()) {
            u = new Usuario(
                    c.getInt(0),
                    c.getString(1),
                    c.getString(2),
                    c.getString(3)
            );
        }
        c.close();
        db.close();
        return u;
    }

    /**
     * Atualiza a senha de um usuário com base no email.
     *
     * @param email     O email do usuário.
     * @param novaSenha A nova senha.
     * @return O número de linhas afetadas.
     */
    public int atualizarSenhaUsuario(String email, String novaSenha) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("senha", novaSenha);
        int result = db.update("usuario", cv, "email=?", new String[]{email});
        db.close();
        return result;
    }

    // ---------------- Métodos para Matéria -----------------

    /**
     * Insere uma nova matéria no banco de dados.
     *
     * @param m O objeto Materia a ser inserido.
     * @return O ID da linha inserida, ou -1 em caso de erro.
     */
    public long insertMateria(Materia m) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("nome", m.getNome());
        cv.put("descricao", m.getDescricao());
        long result = db.insert("materia", null, cv);
        db.close();
        return result;
    }

    /**
     * Retorna todas as matérias do banco de dados.
     *
     * @return Uma lista de objetos Materia.
     */
    public List<Materia> getAllMaterias() {
        List<Materia> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM materia", null);
        while (c.moveToNext()) {
            Materia m = new Materia(
                    c.getInt(0),
                    c.getString(1),
                    c.getString(2)
            );
            list.add(m);
        }
        c.close();
        db.close();
        return list;
    }

    /**
     * Busca uma matéria pelo ID.
     *
     * @param id O ID da matéria.
     * @return O objeto Materia se encontrado, ou null.
     */
    public Materia getMateriaById(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM materia WHERE id=?", new String[]{String.valueOf(id)});
        Materia m = null;
        if (c.moveToFirst()) {
            m = new Materia(
                    c.getInt(0),
                    c.getString(1),
                    c.getString(2)
            );
        }
        c.close();
        db.close();
        return m;
    }

    /**
     * Atualiza uma matéria existente.
     *
     * @param m O objeto Materia com os dados atualizados.
     * @return O número de linhas afetadas.
     */
    public int updateMateria(Materia m) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("nome", m.getNome());
        cv.put("descricao", m.getDescricao());
        int result = db.update("materia", cv, "id=?", new String[]{String.valueOf(m.getId())});
        db.close();
        return result;
    }

    /**
     * Exclui uma matéria do banco de dados.
     *
     * @param id O ID da matéria a ser excluída.
     * @return O número de linhas afetadas.
     */
    public int deleteMateria(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        int result = db.delete("materia", "id=?", new String[]{String.valueOf(id)});
        db.close();
        return result;
    }
}
