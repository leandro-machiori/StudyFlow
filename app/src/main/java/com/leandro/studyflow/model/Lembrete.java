package com.leandro.studyflow.model;

public class Lembrete {
    private int id;
    private String titulo;
    private String mensagem;
    private String dataHora;
    public Lembrete() {}
    public Lembrete(int id, String titulo, String mensagem, String dataHora) {
        this.id = id;
        this.titulo = titulo;
        this.mensagem = mensagem;
        this.dataHora = dataHora;
    }
    // Getters e Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public String getMensagem() { return mensagem; }
    public void setMensagem(String mensagem) { this.mensagem = mensagem; }
    public String getDataHora() { return dataHora; }
    public void setDataHora(String dataHora) { this.dataHora = dataHora; }
}