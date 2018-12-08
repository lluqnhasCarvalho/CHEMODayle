package com.example.lluqn.chamoaplicao.banco_dados;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.lluqn.chamoaplicao.dao.ConsultaDao;
import com.example.lluqn.chamoaplicao.dao.StatusDiarioDao;
import com.example.lluqn.chamoaplicao.dao.TratamentoDao;
import com.example.lluqn.chamoaplicao.modelo.Consulta;
import com.example.lluqn.chamoaplicao.modelo.Status_Diario;
import com.example.lluqn.chamoaplicao.modelo.Tratamento;

@Database( entities = {Consulta.class,Tratamento.class, Status_Diario.class}, version = 1)
public abstract class BancoDadosRoom extends RoomDatabase {

     private static BancoDadosRoom INSTANCE;
     private static final String NOME_BANCO = " Chemo_sql.sql";

     public abstract StatusDiarioDao statusDiarioDao();
     public abstract ConsultaDao consultaDao();
     public abstract TratamentoDao tratamentoDao();


     public static BancoDadosRoom getDatabase(final Context context) {
          if (INSTANCE == null) {
               synchronized (BancoDadosRoom.class) {
                    if (INSTANCE == null) {
                         INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                 BancoDadosRoom.class, NOME_BANCO)
                                 .allowMainThreadQueries()
                                 .build();

                    }
               }
          }
          return INSTANCE;
     }

}