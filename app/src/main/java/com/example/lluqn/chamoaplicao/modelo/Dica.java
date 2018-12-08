package com.example.lluqn.chamoaplicao.modelo;

public class Dica {

    private String titulo;
    private String menssagem;

    public Dica(String titulo, String menssagem) {
        this.titulo = titulo;
        this.menssagem = menssagem;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getMenssagem() {
        return menssagem;
    }

    public void setMenssagem(String menssagem) {
        this.menssagem = menssagem;
    }
}
