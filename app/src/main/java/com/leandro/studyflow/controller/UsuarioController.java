package com.leandro.studyflow.controller;

import android.content.Context;

import com.leandro.studyflow.model.DatabaseHelper;
import com.leandro.studyflow.model.Usuario;

public class UsuarioController {
    private DatabaseHelper db;

    public UsuarioController(Context ctx) {
        db = new DatabaseHelper(ctx);
    }

    public long cadastrar(Usuario u) {
        // Verifica se o email já está em uso
        if (db.verificarEmailExistente(u.getEmail())) {
            return -1; // Retorna -1 para indicar que o email já existe
        }
        return db.insertUsuario(u);
    }

    public Usuario login(String email, String senha) {
        return db.getUsuario(email, senha);
    }

    public boolean atualizarSenha(String email, String novaSenha) {
        return db.atualizarSenhaUsuario(email, novaSenha) > 0;
    }
    public Usuario getUsuarioPorEmail(String email) {
        return db.getUsuarioPorEmail(email);
    }
}