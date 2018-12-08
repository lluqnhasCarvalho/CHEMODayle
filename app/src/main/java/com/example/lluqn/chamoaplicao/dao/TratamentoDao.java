package com.example.lluqn.chamoaplicao.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.lluqn.chamoaplicao.modelo.Consulta;
import com.example.lluqn.chamoaplicao.modelo.Tratamento;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface TratamentoDao {


    @Insert(onConflict = REPLACE)
    long inserir(Tratamento tratamento);

    @Insert(onConflict = REPLACE)
    long[] inserirAll(Tratamento... tratamento);

    @Delete
    int deletar(Tratamento tratamento);

    @Query("DELETE FROM tratamento")
    int deleteAll();

    @Update
    int atualizar(Tratamento tratamento);

    @Query("SELECT * FROM tratamento")
    List<Tratamento> listAll();

    @Query("SELECT * FROM tratamento WHERE id = :id")
    Tratamento[] listById(long id);

    @Query("SELECT COUNT(id) FROM tratamento")
    int getNumberOfRows();
}
