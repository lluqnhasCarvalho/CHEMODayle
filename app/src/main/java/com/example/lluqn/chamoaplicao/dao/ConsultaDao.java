package com.example.lluqn.chamoaplicao.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.lluqn.chamoaplicao.modelo.Consulta;
import com.example.lluqn.chamoaplicao.modelo.Status_Diario;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface ConsultaDao {

    @Insert(onConflict = REPLACE)
    long inserir(Consulta consulta);

    @Insert(onConflict = REPLACE)
    long[] inserirAll(Consulta... consulta);

    @Delete
    int deletar(Consulta consulta);

    @Query("DELETE FROM consulta")
    int deleteAll();

    @Update
    int atualizar(Consulta consulta);

    @Query("SELECT * FROM consulta")
    List<Consulta> listAll();

    @Query("SELECT * FROM consulta WHERE id = :id")
    Consulta[] listById(long id);

    @Query("SELECT COUNT(id) FROM consulta")
    int getNumberOfRows();
}
