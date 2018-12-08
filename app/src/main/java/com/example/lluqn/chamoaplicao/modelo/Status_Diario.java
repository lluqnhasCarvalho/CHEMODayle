package com.example.lluqn.chamoaplicao.modelo;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName="status_diario")
public class Status_Diario {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int id;
    @ColumnInfo(name="como_me_sinto")
    @NonNull
    private String como_me_sinto;
    @ColumnInfo(name="data")
    @NonNull
    private String data_status;
    @ColumnInfo(name="hora")
    @NonNull
    private String hora_status;
    @ColumnInfo(name="intensidade_dor")
    @NonNull
    private int intensidade_dor;
    @ColumnInfo(name="uso_medicamento")
    @NonNull
    private boolean uso_medicamento;
    @ColumnInfo(name="nome_medicamento")
    @NonNull
    private String nome_medicamento;

    public Status_Diario(@NonNull String como_me_sinto, @NonNull String data_status, @NonNull String hora_status,
                         @NonNull int intensidade_dor, @NonNull boolean uso_medicamento, @NonNull String nome_medicamento) {
        this.como_me_sinto = como_me_sinto;
        this.data_status = data_status;
        this.hora_status = hora_status;
        this.intensidade_dor = intensidade_dor;
        this.uso_medicamento = uso_medicamento;
        this.nome_medicamento = nome_medicamento;
    }

    @NonNull
    public int getId() {
        return id;
    }

    public void setId(@NonNull int id) {
        this.id = id;
    }

    @NonNull
    public String getComo_me_sinto() {
        return como_me_sinto;
    }

    public void setComo_me_sinto(@NonNull String como_me_sinto) {
        this.como_me_sinto = como_me_sinto;
    }

    @NonNull
    public String getData_status() {
        return data_status;
    }

    public void setData_status(@NonNull String data_status) {
        this.data_status = data_status;
    }

    @NonNull
    public String getHora_status() {
        return hora_status;
    }

    public void setHora_status(@NonNull String hora_status) {
        this.hora_status = hora_status;
    }

    @NonNull
    public int getIntensidade_dor() {
        return intensidade_dor;
    }

    public void setIntensidade_dor(@NonNull int intensidade_dor) {
        this.intensidade_dor = intensidade_dor;
    }

    @NonNull
    public boolean isUso_medicamento() {
        return uso_medicamento;
    }

    public void setUso_medicamento(@NonNull boolean uso_medicamento) {
        this.uso_medicamento = uso_medicamento;
    }

    @NonNull
    public String getNome_medicamento() {
        return nome_medicamento;
    }

    public void setNome_medicamento(@NonNull String nome_medicamento) {
        this.nome_medicamento = nome_medicamento;
    }
}
