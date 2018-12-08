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
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.lluqn.chamoaplicao.R;
import com.example.lluqn.chamoaplicao.banco_dados.BancoDadosRoom;
import com.example.lluqn.chamoaplicao.modelo.Consulta;

import java.util.ArrayList;
import java.util.List;


public class ConsultaFragment extends Fragment implements View.OnClickListener {

    EditText titulo;
    EditText data;
    EditText hora;
    EditText local;
    EditText nome_med;

    Spinner hora_alerta;
    Switch alerta;
    String valor_hora;

    Button salvar;
    Button cancelar;

    private Context context;

    String[] msg = new String[]{" SALVO COM SUCESSO!", "OCORREU UM ERRO", "CANCELADO", "CAMPO OBRIGATORIO!"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_consulta, container, false);
        setHasOptionsMenu(true);

        //componentes da view
        titulo = (EditText) view.findViewById(R.id.evento_titulo);
        local = (EditText) view.findViewById(R.id.local_evento);
        nome_med = (EditText) view.findViewById(R.id.nome_medico_evento);
        hora_alerta = (Spinner) view.findViewById(R.id.spinner_hora_alarm);
        alerta = (Switch) view.findViewById(R.id.switch_alert);

        data = (EditText) view.findViewById(R.id.data_evento);
        data.setOnClickListener(this);

        hora = (EditText) view.findViewById(R.id.hora_evento);
        hora.setOnClickListener(this);

        salvar = (Button) view.findViewById(R.id.consulta_salvar);
        salvar.setOnClickListener(this);

        cancelar = (Button) view.findViewById(R.id.consulta_cancelar);
        cancelar.setOnClickListener(this);

        alerta.setChecked(true);
        alerta.setOnClickListener(this);


        return view;
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

    public void carregarData() {

        final int[] data_calendar = new int[3];
        DatePickerDialog datePickerDialog = new DatePickerDialog(context, android.R.style.Theme_DeviceDefault_Dialog_NoActionBar,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        data_calendar[0] = dayOfMonth;
                        data_calendar[1] = monthOfYear;
                        data_calendar[2] = year;

                        data.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                    }
                }, data_calendar[2], data_calendar[1], data_calendar[0]);
        datePickerDialog.show();
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

            case R.id.data_evento:

                final int[] data_calendar = new int[3];

                DatePickerDialog datePickerDialog = new DatePickerDialog(context, android.R.style.Theme_Holo_Light,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                data_calendar[0] = dayOfMonth;
                                data_calendar[1] = monthOfYear + 1;
                                data_calendar[2] = year;

                                data.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
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

            case R.id.consulta_cancelar:

                getActivity().getFragmentManager().popBackStack();
                Toast.makeText(getContext(), msg[2], Toast.LENGTH_SHORT).show();
                break;

            case R.id.consulta_salvar:

                try {
                    if (validaCampoConsulta()) {
                        BancoDadosRoom bancoDadosRoom = BancoDadosRoom.getDatabase(context);

                        bancoDadosRoom.consultaDao().inserir(new Consulta(
                                titulo.getText().toString(),
                                data.getText().toString(),
                                hora.getText().toString(),
                                alerta.isChecked(),
                                valor_hora,
                                local.getText().toString(),
                                nome_med.getText().toString()));

                        Toast.makeText(getContext(), msg[0], Toast.LENGTH_SHORT).show();
                        getActivity().getFragmentManager().popBackStack();
                    }
                } catch (Exception e) {
                    Toast.makeText(getContext(), msg[1], Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    @Override
    public void onAttach(Context context) {
        this.context = context;
        super.onAttach(context);
    }

    public boolean validaCampoConsulta() {

        if (titulo.getText().toString().equals("") || titulo.getText().toString() == null) {
            titulo.setError(msg[3]);
            return false;
        }

        if (data.getText().toString().equals("") || data.getText().toString() == null) {
            data.setError(msg[3]);
            return false;
        }

        if (hora.getText().toString().equals("") || hora.getText().toString() == null) {
            hora.setError(msg[3]);
            return false;
        }

        if (local.getText().toString().equals("") || local.getText().toString() == null) {
            local.setError(msg[3]);
            return false;
        }
        return true;
    }
}