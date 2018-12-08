package com.example.lluqn.chamoaplicao.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.lluqn.chamoaplicao.modelo.Status_Diario;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface StatusDiarioDao {

    @Insert(onConflict = REPLACE)
    long inserir(Status_Diario status);

    @Insert(onConflict = REPLACE)
    long[] inserirAll(Status_Diario... status);

    @Delete
    int deletar(Status_Diario status);

    @Query("DELETE FROM status_diario")
    int deleteAll();

    @Update
    int atualizar(Status_Diario status);

    @Query("SELECT * FROM status_diario")
    List<Status_Diario> listAll();

    @Query("SELECT * FROM status_diario WHERE id = :id")
    Status_Diario[] listById(long id);

    @Query("SELECT COUNT(id) FROM status_diario")
    int getNumberOfRows();

}
