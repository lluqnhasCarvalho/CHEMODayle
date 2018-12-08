package com.example.lluqn.chamoaplicao.modelo;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(
        tableName="consulta"
)
public class Consulta {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int id;
    @ColumnInfo(name = "titulo")
    @NonNull
    private String titulo_consulta;
    @ColumnInfo(name = "data")
    @NonNull
    private String data_consulta;
    @ColumnInfo(name = "hora")
    @NonNull
    private String hora_consulta;
    @ColumnInfo(name = "alarme")
    private boolean alarme_consulta;
    @ColumnInfo(name = "hora_alarme")
    private String hora_alarme_consulta;
    @ColumnInfo(name = "local")
    private String local_consulta;
    @ColumnInfo(name = "nome_medico")
    private String nome_medico_consulta;


    public Consulta(String titulo_consulta, String data_consulta, String hora_consulta, boolean alarme_consulta,
                    String hora_alarme_consulta,String local_consulta, String nome_medico_consulta) {

        this.titulo_consulta = titulo_consulta;
        this.data_consulta = data_consulta;
        this.hora_consulta = hora_consulta;
        this.alarme_consulta = alarme_consulta;
        this.hora_alarme_consulta = hora_alarme_consulta;
        this.nome_medico_consulta = nome_medico_consulta;
    }

    @NonNull
    public int getId() {
        return id;
    }

    public void setId(@NonNull int id) {
        this.id = id;
    }

    @NonNull
    public String getTitulo_consulta() {
        return titulo_consulta;
    }

    public void setTitulo_consulta(@NonNull String titulo_consulta) {
        this.titulo_consulta = titulo_consulta;
    }

    @NonNull
    public String getData_consulta() {
        return data_consulta;
    }

    public void setData_consulta(@NonNull String data_consulta) {
        this.data_consulta = data_consulta;
    }

    @NonNull
    public String getHora_consulta() {
        return hora_consulta;
    }

    public void setHora_consulta(@NonNull String hora_consulta) {
        this.hora_consulta = hora_consulta;
    }

    public boolean isAlarme_consulta() {
        return alarme_consulta;
    }

    public void setAlarme_consulta(boolean alarme_consulta) {
        this.alarme_consulta = alarme_consulta;
    }

    public String getHora_alarme_consulta() {
        return hora_alarme_consulta;
    }

    public void setHora_alarme_consulta(String hora_alarme_consulta) {
        this.hora_alarme_consulta = hora_alarme_consulta;
    }

    public String getLocal_consulta() {
        return local_consulta;
    }

    public void setLocal_consulta(String local_consulta) {
        this.local_consulta = local_consulta;
    }

    public String getNome_medico_consulta() {
        return nome_medico_consulta;
    }

    public void setNome_medico_consulta(String nome_medico_consulta) {
        this.nome_medico_consulta = nome_medico_consulta;
    }
}
