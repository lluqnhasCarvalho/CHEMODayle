package com.example.lluqn.chamoaplicao.modelo;

public class Medicamento {

    private int id;
    private String nome;
    private String prescricao;
    private String posologia;
    private String contra_indicacores;
    private int imagem;


    public Medicamento(String nome, String prescricao, String posologia, String contra_indicacores, int imagem) {
        this.nome = nome;
        this.prescricao = prescricao;
        this.posologia = posologia;
        this.contra_indicacores = contra_indicacores;
        this.imagem = imagem;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPrescricao() {
        return prescricao;
    }

    public void setPrescricao(String prescricao) {
        this.prescricao = prescricao;
    }

    public String getPosologia() {
        return posologia;
    }

    public void setPosologia(String posologia) {
        this.posologia = posologia;
    }

    public String getContra_indicacores() {
        return contra_indicacores;
    }

    public void setContra_indicacores(String contra_indicacores) {
        this.contra_indicacores = contra_indicacores;
    }

    public int getImagem() {
        return imagem;
    }

    public void setImagem(int imagem) {
        this.imagem = imagem;
    }
}
