package com.leandro.studyflow.model;

public class Progresso {
    private int id;
    private int materiaId;
    private String conteudo;
    private String status;
    public Progresso() {}
    public Progresso(int id, int materiaId, String conteudo, String status) {
        this.id = id;
        this.materiaId = materiaId;
        this.conteudo = conteudo;
        this.status = status;
    }
    // Getters e Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getMateriaId() { return materiaId; }
    public void setMateriaId(int materiaId) { this.materiaId = materiaId; }
    public String getConteudo() { return conteudo; }
    public void setConteudo(String conteudo) { this.conteudo = conteudo; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}