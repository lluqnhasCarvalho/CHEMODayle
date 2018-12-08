package com.example.lluqn.chamoaplicao;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.lluqn.chamoaplicao.Adapter.RecyclerDicas;
import com.example.lluqn.chamoaplicao.modelo.Dica;

import java.util.ArrayList;

public class DicasInformacoesActivity extends AppCompatActivity {

    private final int NUM_DICAS = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dicas_informacoes);

        initViews();
    }

    private void initViews(){
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.card_recycler_view);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);

        ArrayList<Dica> listaDica = prepareData();
        RecyclerDicas adapter = new RecyclerDicas(getApplicationContext(),listaDica);
        recyclerView.setAdapter(adapter);

    }
    private ArrayList<Dica> prepareData(){

        String[] titulos = getResources().getStringArray(R.array.titulo_dica);
        String[] menssagem = getResources().getStringArray(R.array.menssagem_dica);

        ArrayList<Dica> listaDicas = new ArrayList<>();
        for(int i=0; i<NUM_DICAS; i++){
            Dica dica = new Dica(titulos[i], menssagem[i]);
            listaDicas.add(dica);
        }
        return listaDicas;
    }
}
