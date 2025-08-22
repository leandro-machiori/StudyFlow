package com.leandro.studyflow.controller;

import android.content.Context;

import com.leandro.studyflow.model.DatabaseHelper;
import com.leandro.studyflow.model.Materia;

import java.util.List;

public class MateriaController {
    private DatabaseHelper db;

    public MateriaController(Context ctx) {
        db = new DatabaseHelper(ctx);
    }

    public long adicionarMateria(Materia m) {
        return db.insertMateria(m);
    }

    public List<Materia> listarMaterias() {
        return db.getAllMaterias();
    }

    public Materia buscarMateriaPorId(int id) {
        return db.getMateriaById(id);
    }

    public int atualizarMateria(Materia m) {
        return db.updateMateria(m);
    }

    public int excluirMateria(int id) {
        return db.deleteMateria(id);
    }
}