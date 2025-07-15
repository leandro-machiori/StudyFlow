package com.leandro.studyflow.model;

public class Cronograma {
    private int id;
    private int materiaId;
    private String dataEstudo;
    private String horario;
    public Cronograma() {}
    public Cronograma(int id, int materiaId, String dataEstudo, String horario) {
        this.id = id;
        this.materiaId = materiaId;
        this.dataEstudo = dataEstudo;
        this.horario = horario;
    }
    // Getters e Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getMateriaId() { return materiaId; }
    public void setMateriaId(int materiaId) { this.materiaId = materiaId; }
    public String getDataEstudo() { return dataEstudo; }
    public void setDataEstudo(String dataEstudo) { this.dataEstudo = dataEstudo; }
    public String getHorario() { return horario; }
    public void setHorario(String horario) { this.horario = horario; }
}