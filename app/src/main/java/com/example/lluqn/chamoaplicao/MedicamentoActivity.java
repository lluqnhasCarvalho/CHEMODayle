package com.example.lluqn.chamoaplicao;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.lluqn.chamoaplicao.Adapter.RecyclerMedicamentosAdapter;
import com.example.lluqn.chamoaplicao.modelo.Medicamento;

import java.util.ArrayList;

public class MedicamentoActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<Medicamento> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_medicamento );

        recyclerView = findViewById( R.id.reciclerViewFarma );
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager( this );
        recyclerView.setLayoutManager( linearLayoutManager );

        list = new ArrayList<>(  );

        list.add(new Medicamento("ABIRATERONA 250MG COMP.","","","",R.drawable.ic_med));
        list.add(new Medicamento("ANAGRELIDA 0,5 MG COMP.","","","",R.drawable.ic_med));
        list.add(new Medicamento("ANASTRAZOL 1MG COMP.","","","",R.drawable.ic_med));
        list.add(new Medicamento("BICALUTAMIDA 50MG COMP.","","","",R.drawable.ic_med));
        list.add(new Medicamento("BUSSULFANO 2MG COMP.","","","",R.drawable.ic_med));
        list.add(new Medicamento("CAPECITABINA 150MG COMP.","","","",R.drawable.ic_med));
        list.add(new Medicamento("CAPECITABINA 500MG COMP.","","","",R.drawable.ic_med));
        list.add(new Medicamento("CICLOFOSFAMIDA 50MG COMP.","","","",R.drawable.ic_med));
        list.add(new Medicamento("CICLOSPORINA 100MG COMP.","","","",R.drawable.ic_med));
        list.add(new Medicamento("CICLOSPORINA 50MG COMP.","","","",R.drawable.ic_med));
        list.add(new Medicamento("CIPROTERONA 50MG COMP.","","","",R.drawable.ic_med));
        list.add(new Medicamento("CLORAMBUCILA 2MG COMP.","","","",R.drawable.ic_med));
        list.add(new Medicamento("DASATINIBE 100MG COMP.","","","",R.drawable.ic_med));
        list.add(new Medicamento("DASATINIBE 140MG COMP.","","","",R.drawable.ic_med));
        list.add(new Medicamento("DASATINIBE 50MG COMP.","","","",R.drawable.ic_med));
        list.add(new Medicamento("DASATINIBE 20MG COMP.","","","",R.drawable.ic_med));
        list.add(new Medicamento("DIETILESTILBESTROL 1MG COMP.","","","",R.drawable.ic_med));
        list.add(new Medicamento("ENZALUTAMIDA 40MG COMP.","","","",R.drawable.ic_med));
        list.add(new Medicamento("ERLOTINIBE 100MG COMP.","","","",R.drawable.ic_med));
        list.add(new Medicamento("ERLOTINIBE 150MG COMP.","","","",R.drawable.ic_med));
        list.add(new Medicamento("ETOPOSÃDEO 100MG COMP.","","","",R.drawable.ic_med));
        list.add(new Medicamento("ETOPOSÃDEO 50MG COMP.","","","",R.drawable.ic_med));
        list.add(new Medicamento("EVEROLIMO 10MG COMP.","","","",R.drawable.ic_med));
        list.add(new Medicamento("EVEROLIMO 5MG COMP.","","","",R.drawable.ic_med));
        list.add(new Medicamento("EXEMESTANO 25MG COMP.","","","",R.drawable.ic_med));
        list.add(new Medicamento("FLUDARABINA 10MG COMP.","","","",R.drawable.ic_med));
        list.add(new Medicamento("FLUTAMIDA 250MG COMP.","","","",R.drawable.ic_med));
        list.add(new Medicamento("FOLINATO de CALCIO  15MG COMP.","","","",R.drawable.ic_med));
        list.add(new Medicamento("GEFITINIBE 250MG COMP.","","","",R.drawable.ic_med));
        list.add(new Medicamento("HIDROXIURÃ‰IA 500MG COMP.","","","",R.drawable.ic_med));
        list.add(new Medicamento("IMATINIBE 100MG COMP.","","","",R.drawable.ic_med));
        list.add(new Medicamento("IMATINIBE 400MG COMP.","","","",R.drawable.ic_med));
        list.add(new Medicamento("LAPATINIBE 250MG COMP.","","","",R.drawable.ic_med));
        list.add(new Medicamento("LETROZOL 2,5MG COMP.","","","",R.drawable.ic_med));
        list.add(new Medicamento("LOMUSTINA  10MG COMP.","","","",R.drawable.ic_med));
        list.add(new Medicamento("LOMUSTINA  40MG COMP.","","","",R.drawable.ic_med));
        list.add(new Medicamento("MEGESTROL 160MG COMP.","","","",R.drawable.ic_med));
        list.add(new Medicamento("MELFALANA 2MG COMP.","","","",R.drawable.ic_med));
        list.add(new Medicamento("MERCAPITOPURINA 50MG COMP.","","","",R.drawable.ic_med));
        list.add(new Medicamento("METOTREXATO 2,5MG COMP.","","","",R.drawable.ic_med));
        list.add(new Medicamento("MITOTANA 500MG COMP.","","","",R.drawable.ic_med));
        list.add(new Medicamento("NILOTIIBE 200MG COMP.","","","",R.drawable.ic_med));
        list.add(new Medicamento("PAZOPANIBE 400MG COMP.","","","",R.drawable.ic_med));
        list.add(new Medicamento("PROCARBAZINA 50MG COMP.","","","",R.drawable.ic_med));
        list.add(new Medicamento("SORAFENIBE 200MG COMP.","","","",R.drawable.ic_med));
        list.add(new Medicamento("SUNITINIBE 12,5MG COMP.","","","",R.drawable.ic_med));
        list.add(new Medicamento("SUNITINIBE 25MG COMP.","","","",R.drawable.ic_med));
        list.add(new Medicamento("SUNITINIBE 50MG COMP.","","","",R.drawable.ic_med));
        list.add(new Medicamento("TAMOXIFENO 10MG COMP.","","","",R.drawable.ic_med));
        list.add(new Medicamento("TAMOXIFENO 20MG COMP.","","","",R.drawable.ic_med));
        list.add(new Medicamento("TEMOZOLAMIDA 100MG COMP.","","","",R.drawable.ic_med));
        list.add(new Medicamento("TEMOZOLAMIDA 20MG COMP.","","","",R.drawable.ic_med));
        list.add(new Medicamento("TEMOZOLAMIDA 250MG COMP.","","","",R.drawable.ic_med));
        list.add(new Medicamento("TEMOZOLAMIDA 5MG COMP.","","","",R.drawable.ic_med));
        list.add(new Medicamento("TIOGUANINA 40MG COMP.","","","",R.drawable.ic_med));
        list.add(new Medicamento("TRETINOINA 10MG COMP.","","","",R.drawable.ic_med));
        list.add(new Medicamento("VEMURAFENIBE 240MG COMP.","","","",R.drawable.ic_med));
        list.add(new Medicamento("VINORELBINA 20MG COMP.","","","",R.drawable.ic_med));
        list.add(new Medicamento("VINORELBINA 30MG COMP.","","","",R.drawable.ic_med));
        list.add(new Medicamento("MESNA 400MG COMP.","","","",R.drawable.ic_med));

        RecyclerMedicamentosAdapter farmaciaAdapter = new RecyclerMedicamentosAdapter( this,list );
        recyclerView.setAdapter( farmaciaAdapter );
    }
}
