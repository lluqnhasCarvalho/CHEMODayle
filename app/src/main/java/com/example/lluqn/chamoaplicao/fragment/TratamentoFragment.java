package com.example.lluqn.chamoaplicao.fragment;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.MultiAutoCompleteTextView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.lluqn.chamoaplicao.R;
import com.example.lluqn.chamoaplicao.banco_dados.BancoDadosRoom;
import com.example.lluqn.chamoaplicao.modelo.Consulta;
import com.example.lluqn.chamoaplicao.modelo.Tratamento;

import java.util.ArrayList;
import java.util.List;

public class TratamentoFragment extends Fragment implements View.OnClickListener{

    EditText titulo;
    EditText data1;
    EditText data2;
    EditText hora;

    MultiAutoCompleteTextView nome_medicamento;
    Spinner hora_alerta;
    Switch alerta;
    String valor_hora;

    Button salvar;
    Button cancelar;

    Context context;
    String[] msg = new String[]{" SALVO COM SUCESSO!", "OCORREU UM ERRO", "CANCELADO", "CAMPO OBRIGATORIO!"};
    int[] data_calendar;
    List<String> List_medicamento;
    DatePickerDialog datePickerDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_tratamento, container, false);

        titulo = (EditText) view.findViewById(R.id.evento_titulo);

        data1 = (EditText) view.findViewById(R.id.datainicial_evento);
        data1.setOnClickListener(this);

        data2 = (EditText) view.findViewById(R.id.datafinal_evento);
        data2.setOnClickListener(this);

        hora = (EditText) view.findViewById(R.id.hora_evento);
        hora.setOnClickListener(this);

        nome_medicamento = (MultiAutoCompleteTextView) view.findViewById(R.id.nome_medicamento_evento);
        carregarMultiAutoComplet();

        hora_alerta = (Spinner) view.findViewById(R.id.spinner_hora_alarm);

        alerta = (Switch) view.findViewById(R.id.switch_alert);
        alerta.setOnClickListener(this);

        salvar = (Button) view.findViewById(R.id.tratamento_salvar);
        salvar.setOnClickListener(this);

        cancelar = (Button) view.findViewById(R.id.tratamento_cancelar);
        cancelar.setOnClickListener(this);


        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.switch_alert:
                if (alerta.isChecked()) {
                    hora_alerta.setVisibility(View.VISIBLE);

                    ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, carregarSpinner());
                    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    hora_alerta.setAdapter(dataAdapter);
                    hora_alerta.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                        @Override
                        public void onItemSelected(AdapterView<?> parent, View v, int posicao, long id) {
                            valor_hora = parent.getItemAtPosition(posicao).toString();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                        }
                    });
                } else {
                    hora_alerta.setVisibility(View.GONE);
                }
                break;

            case R.id.datainicial_evento:

                data_calendar = new int[3];

                datePickerDialog = new DatePickerDialog(context, android.R.style.Theme_Holo_Light,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                data_calendar[0] = dayOfMonth;
                                data_calendar[1] = monthOfYear + 1;
                                data_calendar[2] = year;

                                data1.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                            }
                        }, data_calendar[2], data_calendar[1], data_calendar[0]);
                datePickerDialog.show();
                break;

            case R.id.datafinal_evento:

                data_calendar = new int[3];

                datePickerDialog = new DatePickerDialog(context, android.R.style.Theme_Holo_Light,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                data_calendar[0] = dayOfMonth;
                                data_calendar[1] = monthOfYear + 1;
                                data_calendar[2] = year;

                                data2.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                            }
                        }, data_calendar[2], data_calendar[1], data_calendar[0]);
                datePickerDialog.show();
                break;

            case R.id.hora_evento:

                final int[] hora_calendar = new int[2];

                TimePickerDialog mTimePicker = new TimePickerDialog(context, android.R.style.Theme_Holo_Light,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker timePicker, int Hour, int Minute) {
                                hora_calendar[0] = Hour;
                                hora_calendar[1] = Minute;

                                hora.setText(Hour + ":" + Minute);
                            }
                        }, hora_calendar[0], hora_calendar[1], true);
                mTimePicker.show();

                break;

            case R.id.tratamento_cancelar:

                getActivity().getFragmentManager().popBackStack();
                Toast.makeText(context, msg[2], Toast.LENGTH_SHORT).show();
                break;

            case R.id.tratamento_salvar:

                try{
                    if(validaCampoTratamento()){
                        BancoDadosRoom bancoDadosRoom = BancoDadosRoom.getDatabase(context);

                        bancoDadosRoom.tratamentoDao().inserir(new Tratamento(
                                titulo.getText().toString(),
                                data1.getText().toString(),
                                data2.getText().toString(),
                                hora.getText().toString(),
                                alerta.isChecked(),
                                valor_hora,
                                nome_medicamento.getText().toString()));

                        Toast.makeText(getContext(), msg[0], Toast.LENGTH_SHORT).show();
                        getActivity().getFragmentManager().popBackStack();
                    }
                }catch (Exception e){
                    Toast.makeText(context, msg[1], Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    @Override
    public void onAttach(Context context) {
        this.context = context;
        super.onAttach(context);
    }

    public List<String> carregarSpinner() {
        List<String> List_alarme = new ArrayList<String>();

        List_alarme.add("Na hora");
        List_alarme.add("5 min antes");
        List_alarme.add("10 min antes");
        List_alarme.add("15 min antes");
        List_alarme.add("30 min antes");
        List_alarme.add("1 hora antes");
        List_alarme.add("1 dia antes");

        return List_alarme;
    }

    public void carregarMultiAutoComplet() {

        List_medicamento = new ArrayList<>();

        List_medicamento.add("ABIRATERONA 250MG COMP.");
        List_medicamento.add("ANAGRELIDA 0,5 MG COMP.");
        List_medicamento.add("ANASTRAZOL 1MG COMP.");
        List_medicamento.add("BICALUTAMIDA 50MG COMP.");
        List_medicamento.add("BUSSULFANO 2MG COMP.");
        List_medicamento.add("CAPECITABINA 150MG COMP.");
        List_medicamento.add("CAPECITABINA 500MG COMP.");
        List_medicamento.add("CICLOFOSFAMIDA 50MG COMP.");
        List_medicamento.add("CICLOSPORINA 100MG COMP.");
        List_medicamento.add("CICLOSPORINA 50MG COMP.");
        List_medicamento.add("CIPROTERONA 50MG COMP.");
        List_medicamento.add("CLORAMBUCILA 2MG COMP.");
        List_medicamento.add("DASATINIBE 100MG COMP.");
        List_medicamento.add("DASATINIBE 140MG COMP.");
        List_medicamento.add("DASATINIBE 50MG COMP.");
        List_medicamento.add("DASATINIBE 20MG COMP.");
        List_medicamento.add("DIETILESTILBESTROL 1MG COMP.");
        List_medicamento.add("ENZALUTAMIDA 40MG COMP.");
        List_medicamento.add("ERLOTINIBE 100MG COMP.");
        List_medicamento.add("ERLOTINIBE 150MG COMP.");
        List_medicamento.add("ETOPOSÃƒÆ’Ã‚ÂDEO 100MG COMP.");
        List_medicamento.add("ETOPOSÃƒÆ’Ã‚ÂDEO 50MG COMP.");
        List_medicamento.add("EVEROLIMO 10MG COMP.");
        List_medicamento.add("EVEROLIMO 5MG COMP.");
        List_medicamento.add("EXEMESTANO 25MG COMP.");
        List_medicamento.add("FLUDARABINA 10MG COMP.");
        List_medicamento.add("FLUTAMIDA 250MG COMP.");
        List_medicamento.add("FOLINATO de CALCIO  15MG COMP.");
        List_medicamento.add("GEFITINIBE 250MG COMP.");
        List_medicamento.add("HIDROXIURÃƒÆ’Ã¢â‚¬Â°IA 500MG COMP.");
        List_medicamento.add("IMATINIBE 100MG COMP.");
        List_medicamento.add("IMATINIBE 400MG COMP.");
        List_medicamento.add("LAPATINIBE 250MG COMP.");
        List_medicamento.add("LETROZOL 2,5MG COMP.");
        List_medicamento.add("LOMUSTINA  10MG COMP.");
        List_medicamento.add("LOMUSTINA  40MG COMP.");
        List_medicamento.add("MEGESTROL 160MG COMP.");
        List_medicamento.add("MELFALANA 2MG COMP.");
        List_medicamento.add("MERCAPITOPURINA 50MG COMP.");
        List_medicamento.add("METOTREXATO 2,5MG COMP.");
        List_medicamento.add("MITOTANA 500MG COMP.");
        List_medicamento.add("NILOTIIBE 200MG COMP.");
        List_medicamento.add("PAZOPANIBE 400MG COMP.");
        List_medicamento.add("PROCARBAZINA 50MG COMP.");
        List_medicamento.add("SORAFENIBE 200MG COMP.");
        List_medicamento.add("SUNITINIBE 12,5MG COMP.");
        List_medicamento.add("SUNITINIBE 25MG COMP.");
        List_medicamento.add("SUNITINIBE 50MG COMP.");
        List_medicamento.add("TAMOXIFENO 10MG COMP.");
        List_medicamento.add("TAMOXIFENO 20MG COMP.");
        List_medicamento.add("TEMOZOLAMIDA 100MG COMP.");
        List_medicamento.add("TEMOZOLAMIDA 20MG COMP.");
        List_medicamento.add("TEMOZOLAMIDA 250MG COMP.");
        List_medicamento.add("TEMOZOLAMIDA 5MG COMP.");
        List_medicamento.add("TIOGUANINA 40MG COMP.");
        List_medicamento.add("TRETINOINA 10MG COMP.");
        List_medicamento.add("VEMURAFENIBE 240MG COMP.");
        List_medicamento.add("VINORELBINA 20MG COMP.");
        List_medicamento.add("VINORELBINA 30MG COMP.");
        List_medicamento.add("MESNA 400MG COMP.");

        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(context, android.R.layout.simple_dropdown_item_1line, List_medicamento);
        nome_medicamento.setAdapter(dataAdapter2);
        nome_medicamento.setTokenizer (new MultiAutoCompleteTextView.CommaTokenizer());

    }

    public boolean validaCampoTratamento(){
        if(titulo.getText().toString().equals("") || titulo.getText().toString() == null){
            titulo.setError(msg[3]);
            return  false;
        }

        if(data1.getText().toString().equals("") || data1.getText().toString() == null){
            data1.setError(msg[3]);
            return  false;
        }

        if(data2.getText().toString().equals("") || data2.getText().toString() == null){
            data2.setError(msg[3]);
            return  false;
        }

        if(hora.getText().toString().equals("") || hora.getText().toString() == null){
            hora.setError(msg[3]);
            return  false;
        }

        if(nome_medicamento.getText().toString().equals("") || nome_medicamento.getText().toString() == null){
            nome_medicamento.setError(msg[3]);
            return  false;
        }
        return true;
    }
}


