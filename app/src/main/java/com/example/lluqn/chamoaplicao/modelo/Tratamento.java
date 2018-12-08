package com.example.lluqn.chamoaplicao.modelo;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(
        tableName = "tratamento"
)
public class Tratamento {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int id;
    @ColumnInfo(name = "titulo")
    @NonNull
    private String titulo_tratamento;
    @ColumnInfo(name = "data_inicial")
    @NonNull
    private String data_inicial_tratamento;
    @ColumnInfo(name = "data_final")
    @NonNull
    private String data_final_tratamento;
    @ColumnInfo(name = "hora")
    @NonNull
    private String hora_tratamento;
    @ColumnInfo(name = "alarme")
    private boolean alarme_tratamento;
    @ColumnInfo(name = "hora_alarme")
    private String hora_alarme_tratamento;
    @ColumnInfo(name = "nome_medicamento")
    private String nome_medicamento_tratamento;


    public Tratamento(@NonNull String titulo_tratamento, @NonNull String data_inicial_tratamento,
                      @NonNull String data_final_tratamento, @NonNull String hora_tratamento,
                      boolean alarme_tratamento, String hora_alarme_tratamento, String nome_medicamento_tratamento) {

        this.titulo_tratamento = titulo_tratamento;
        this.data_inicial_tratamento = data_inicial_tratamento;
        this.data_final_tratamento = data_final_tratamento;
        this.hora_tratamento = hora_tratamento;
        this.alarme_tratamento = alarme_tratamento;
        this.hora_alarme_tratamento = hora_alarme_tratamento;
        this.nome_medicamento_tratamento = nome_medicamento_tratamento;
    }

    @NonNull
    public int getId() {
        return id;
    }

    public void setId(@NonNull int id) {
        this.id = id;
    }

    @NonNull
    public String getTitulo_tratamento() {
        return titulo_tratamento;
    }

    public void setTitulo_tratamento(@NonNull String titulo_tratamento) {
        this.titulo_tratamento = titulo_tratamento;
    }

    @NonNull
    public String getData_inicial_tratamento() {
        return data_inicial_tratamento;
    }

    public void setData_inicial_tratamento(@NonNull String data_inicial_tratamento) {
        this.data_inicial_tratamento = data_inicial_tratamento;
    }

    @NonNull
    public String getData_final_tratamento() {
        return data_final_tratamento;
    }

    public void setData_final_tratamento(@NonNull String data_final_tratamento) {
        this.data_final_tratamento = data_final_tratamento;
    }

    @NonNull
    public String getHora_tratamento() {
        return hora_tratamento;
    }

    public void setHora_tratamento(@NonNull String hora_tratamento) {
        this.hora_tratamento = hora_tratamento;
    }

    public boolean isAlarme_tratamento() {
        return alarme_tratamento;
    }

    public void setAlarme_tratamento(boolean alarme_tratamento) {
        this.alarme_tratamento = alarme_tratamento;
    }

    public String getHora_alarme_tratamento() {
        return hora_alarme_tratamento;
    }

    public void setHora_alarme_tratamento(String hora_alarme_tratamento) {
        this.hora_alarme_tratamento = hora_alarme_tratamento;
    }

    public String getNome_medicamento_tratamento() {
        return nome_medicamento_tratamento;
    }

    public void setNome_medicamento_tratamento(String nome_medicamento_tratamento) {
        this.nome_medicamento_tratamento = nome_medicamento_tratamento;
    }
}
