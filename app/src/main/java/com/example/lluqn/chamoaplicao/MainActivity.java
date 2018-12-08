package com.example.lluqn.chamoaplicao;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lluqn.chamoaplicao.banco_dados.BancoDadosRoom;
import com.example.lluqn.chamoaplicao.modelo.Status_Diario;
import com.hsalf.smilerating.BaseRating;
import com.hsalf.smilerating.SmileRating;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    //componentes status
    CheckBox uso_medicame;
    EditText nome_medicamentos;
    EditText como_se_sente;

    SmileRating status_emoji;

    Button salvar;
    Button cancelar;

    //variaveis auxiliares
    boolean cb_uso_medicamento;
    String ed_nome_medicamentos;
    String ed_como_se_sente;
    int num_status_emoji;

    String[] msg = new String[5];
    String[] string_dor = new String[5];

    Handler handler;
    Runnable mRun;

    private final String PREFS_NAME = "instacia_activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        msg = getResources().getStringArray(R.array.msg);
        string_dor = getResources().getStringArray(R.array.smile_rating);

        //componentes status
        uso_medicame = (CheckBox) findViewById(R.id.status_uso_medicamento);
        uso_medicame.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked){
                    nome_medicamentos.setVisibility(View.VISIBLE);
                    cb_uso_medicamento = true;
                }else{
                    nome_medicamentos.setVisibility(View.INVISIBLE);
                    cb_uso_medicamento = false;
                }
            }
        });
        nome_medicamentos = (EditText) findViewById(R.id.status_nome_medicamento);
        como_se_sente = (EditText) findViewById(R.id.status_descricao);

        salvar = (Button) findViewById(R.id.status_salvar);
        cancelar = (Button) findViewById(R.id.status_cancelar);

        status_emoji = (SmileRating) findViewById(R.id.status_emoji);
        status_emoji.setNameForSmile(BaseRating.GREAT, string_dor[0]);
        status_emoji.setNameForSmile(BaseRating.GOOD, string_dor[1]);
        status_emoji.setNameForSmile(BaseRating.OKAY, string_dor[2]);
        status_emoji.setNameForSmile(BaseRating.BAD, string_dor[3]);
        status_emoji.setNameForSmile(BaseRating.TERRIBLE, string_dor[4]);

    }

    public void clickStatus(View view) {

        switch (view.getId()) {
            case R.id.status_cancelar:

                Toast.makeText(this, msg[2], Toast.LENGTH_SHORT).show();
                limparTela();
                break;

            case R.id.status_salvar:
                try{
                    BancoDadosRoom bancoDadosRoom = BancoDadosRoom.getDatabase(MainActivity.this);

                    Date dataHoraAtual = new Date();
                    String data = new SimpleDateFormat("dd-MM-yyyy").format(dataHoraAtual);
                    String hora = new SimpleDateFormat("HH:mm").format(dataHoraAtual);

                    if(validaCampoStatus()){
                        bancoDadosRoom.statusDiarioDao().inserir(new Status_Diario( ed_como_se_sente, data, hora, num_status_emoji,
                                cb_uso_medicamento, ed_nome_medicamentos));
                    }

                    Toast.makeText(MainActivity.this, msg[1],Toast.LENGTH_SHORT).show();

                    startActivity(new Intent(this, Main2Activity.class));

                }catch (Exception e){

                    Toast.makeText(MainActivity.this, msg[0],Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    public void limparTela(){

        como_se_sente.setText("");
        uso_medicame.setChecked(false);
        status_emoji.setSelectedSmile(0);

        nome_medicamentos.setText("");
        nome_medicamentos.setVisibility(View.INVISIBLE);
    }

    public boolean validaCampoStatus(){

        ed_como_se_sente = como_se_sente.getText().toString();
        ed_nome_medicamentos = nome_medicamentos.getText().toString();
        num_status_emoji = status_emoji.getRating();


        if(cb_uso_medicamento == true) {
            if (ed_nome_medicamentos.equals("") || ed_nome_medicamentos == null) {
                nome_medicamentos.setError(msg[4]);
                return false;
            }
        }

        if(ed_como_se_sente.equals("") || ed_como_se_sente == null){
            como_se_sente.setError(msg[4]);
            return false;

        }

        if(num_status_emoji == 0){
            Toast.makeText(this, "Selecione um Emoji", Toast.LENGTH_SHORT).show();
            return false;

        }

        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onPause(){
        super.onPause();

        SharedPreferences sharedPreferences;
        sharedPreferences = this.getSharedPreferences(PREFS_NAME,MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        Date dataHoraAtual = new Date();
        String data = new SimpleDateFormat("dd-MM-yyyy").format(dataHoraAtual);


        editor.putString("data", data);
        editor.commit();
    }

}