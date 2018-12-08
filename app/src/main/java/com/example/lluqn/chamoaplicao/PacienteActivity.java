package com.example.lluqn.chamoaplicao;

import android.Manifest;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.DialogFragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.Calendar;

public class PacienteActivity extends AppCompatActivity {

    private static final int RESULT_LOAD_IMAGE = 1;
    private static final int PERMISSAO_REQUEST = 2;
    private final String PREFS_NAME = "dados_paciente";

    private String imagem;

    private TextView txtNome;
    private TextView txtDescricao;

    public static TextView txtIdade;
    private TextView txtSexo;
    private TextView txtPeso;
    private TextView txtAltura;

    private ImageView imgPerfil, imgFundo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paciente);

        imgPerfil = (ImageView) findViewById(R.id.img_frente);
        imgFundo = (ImageView) findViewById(R.id.img_fundo);

        txtNome = (TextView) findViewById(R.id.txtnome_paciente);
        txtDescricao = (TextView) findViewById(R.id.txtdescricao_paciente);

        txtIdade = (TextView) findViewById(R.id.txtidade_parente);
        txtSexo = (TextView) findViewById(R.id.txtsexo_paciente);
        txtPeso = (TextView) findViewById(R.id.txtpeso_paciente);
        txtAltura = (TextView) findViewById(R.id.txtaltura_paciente);

        //pegando a permição
        if(ContextCompat.checkSelfPermission(this,Manifest.permission.READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
            if(ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.READ_EXTERNAL_STORAGE)){

            } else{ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},PERMISSAO_REQUEST);
            }
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {

            Uri selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};
            Toast.makeText(this, selectedImage + "", Toast.LENGTH_SHORT).show();

            Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            imagem = picturePath;
            cursor.close();

            imgPerfil.setImageBitmap(BitmapFactory.decodeFile(picturePath));
            imgFundo.setImageBitmap(BitmapFactory.decodeFile(picturePath));

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions , int[] grantResults) {
        if(requestCode== PERMISSAO_REQUEST) {
            if(grantResults.length> 0&& grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // A permissão foi concedida. Pode continuar}
            }else {
                // A permissão foi negada. Precisa ver o que deve ser desabilitado}
            }

            return;
        }
    }

     public void clickImageView(View view) {

        switch (view.getId()){
            case R.id.img_toolbar:


                finish();
                break;
            case R.id.img_fundo:
                Intent i = new Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, RESULT_LOAD_IMAGE);

                break;
        }
    }

    public void clickTextView(View view) {
        switch (view.getId()){
            case R.id.txtnome_paciente:
                textViewDialog();
                break;
            case R.id.txtpeso_paciente:
                numPickerDialog("Definir Peso", "Kg",0);
                break;
            case R.id.txtaltura_paciente:
                numPickerDialog("Definir Altura", "Cm",1);
                break;
            case R.id.txtsexo_paciente:
                radioGroupDialog();
                break;
            case R.id.txtidade_parente:
                //showDatePickerDialog(view);
                DialogFragment newFragment = new DatePickerFragment();
                newFragment.show(getSupportFragmentManager(), "datePicker");
                break;
        }
    }

    public void textViewDialog(){

        LayoutInflater layoutInflaterAndroid = LayoutInflater.from(this);
        View mView = layoutInflaterAndroid.inflate(R.layout.dialog_edittext, null);
        AlertDialog.Builder alertDialogBuilderUserInput = new AlertDialog.Builder(this);
        alertDialogBuilderUserInput.setView(mView);

        final TextView nome_paciente = (TextView) mView.findViewById(R.id.nome_perfil);
        alertDialogBuilderUserInput
                .setCancelable(false)
                .setPositiveButton("Salvar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogBox, int id) {
                        txtNome.setText(nome_paciente.getText().toString());
                    }
                })

                .setNegativeButton("Cancelar",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogBox, int id) {
                                dialogBox.cancel();
                            }
                        });

        AlertDialog alertDialogAndroid = alertDialogBuilderUserInput.create();
        alertDialogAndroid.show();
    }

    public void numPickerDialog(String titulo, String medida, final int id){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View theView = inflater.inflate(R.layout.dialog_numpicker, null);


        final TextView dialogTitulo = (TextView) theView.findViewById(R.id.dialogTitle);
        dialogTitulo.setText(titulo);

        final TextView dialogMedida = (TextView) theView.findViewById(R.id.dialogMedida);
        dialogMedida.setText(medida);

        final NumberPicker K = (NumberPicker) theView.findViewById(R.id.numPicker_k);
        final NumberPicker G = (NumberPicker) theView.findViewById(R.id.numPicker_g);

        builder.setView(theView)
                .setPositiveButton("SALVAR",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(id == 0){
                            txtPeso.setText(K.getValue() + "."+ G.getValue());
                        }else if(id == 1){
                            txtAltura.setText(K.getValue() + "."+ G.getValue());
                        }
                    }
                }).setNegativeButton("CANCELAR", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        K.setMinValue(0);
        K.setMaxValue(500);


        G.setMinValue(0);
        G.setMaxValue(9);
        G.setValue(0);

        builder.show();
    }

    public void radioGroupDialog(){

        LayoutInflater layoutInflaterAndroid = LayoutInflater.from(this);
        final View mView = layoutInflaterAndroid.inflate(R.layout.dialog_radiogroup, null);
        AlertDialog.Builder alertDialogBuilderUserInput = new AlertDialog.Builder(this);
        alertDialogBuilderUserInput.setView(mView);

        final RadioGroup sexo_paciente = (RadioGroup) mView.findViewById(R.id.radioGroup);

        alertDialogBuilderUserInput
                .setCancelable(false)
                .setPositiveButton("Salvar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogBox, int id) {

                        int selectedId = sexo_paciente.getCheckedRadioButtonId();
                        RadioButton radioSexButton =(RadioButton) mView.findViewById(selectedId);
                        txtSexo.setText(radioSexButton.getText());
                    }
                })

                .setNegativeButton("Cancelar",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogBox, int id) {
                                dialogBox.cancel();
                            }
                        });

        AlertDialog alertDialogAndroid = alertDialogBuilderUserInput.create();
        alertDialogAndroid.show();
    }

    public static class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(),android.R.style.Theme_Material_Light_Dialog, this, year, month, day);
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            txtIdade.setText(day + "-" + (month + 1) + "-" + year);
        }
    }

    public void carregandoimage(String caminho){
        File imgFile = new File(caminho);
        if(imgFile.exists()){

            Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
            //ImageView myImage = (ImageView) findViewById(R.id.img_fundo);
            imgPerfil.setImageBitmap(myBitmap);
            imgFundo.setImageBitmap(myBitmap);

        }
    }

    @Override
    protected void onPause() {

        super.onPause();
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("imagem", imagem);
        editor.putString("nome", txtNome.getText().toString());
        editor.putString("sexo", txtSexo.getText().toString());
        editor.putString("idade", txtIdade.getText().toString());
        editor.putString("peso", txtPeso.getText().toString());
        editor.putString("altura", txtAltura.getText().toString());
        editor.commit();
    }

    @Override
    protected void onResume() {
        super.onResume();

        SharedPreferences settings = getSharedPreferences(PREFS_NAME, MODE_PRIVATE );
        carregandoimage(settings.getString("imagem", "" ));
        txtNome.setText(settings.getString("nome", "" ));
        txtSexo.setText(settings.getString("sexo", "" ));
        txtIdade.setText(settings.getString("idade", "" ));
        txtPeso.setText(settings.getString("peso", "" ));
        txtAltura.setText(settings.getString("altura", "" ));
    }
}