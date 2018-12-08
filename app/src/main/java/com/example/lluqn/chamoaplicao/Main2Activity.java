package com.example.lluqn.chamoaplicao;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.example.lluqn.chamoaplicao.BroadCast.AlarmeActivity;

public class Main2Activity extends AppCompatActivity {

    private static final int IMAGE_GALLERY_REQUEST = 1;
    private static final int PERMISSAO_REQUEST = 2;
    private final String PREFS_NAME = "instacia_activity";


    ImageView imageView;
    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        SharedPreferences settings = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        boolean open =  settings.getBoolean("chave",false);
        String data = settings.getString("data","");

       if(open == false){
           startActivity(new Intent(this, MainActivity.class));
       }

        //pegando a permição
        if(ContextCompat.checkSelfPermission(this,Manifest.permission.READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
            if(ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.READ_EXTERNAL_STORAGE)){

            } else{ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},PERMISSAO_REQUEST);
            }
        }

        imageView = (ImageView) findViewById(R.id.img_perfil_main);
        TextView nome = (TextView) findViewById(R.id.text_nome);
        nome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, IMAGE_GALLERY_REQUEST);
            }
        });

        toolbar = (Toolbar) findViewById(R.id.toolbar_main);
        toolbar.inflateMenu(R.menu.menu_principal);
    }

    public void clickButton(View view) {

        switch (view.getId()){
            case R.id.agenda_bt:
                startActivity(new Intent(this, EventoActivity.class));
                break;
            case R.id.perfil_bt:
                startActivity(new Intent(this, PacienteActivity.class));
                break;
            case R.id.dicas_bt:
                startActivity(new Intent(this, DicasInformacoesActivity.class));
                break;
            case R.id.medicamento_bt:
                startActivity(new Intent(this, MedicamentoActivity.class));
                break;
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == IMAGE_GALLERY_REQUEST && resultCode == RESULT_OK && null != data) {

            Uri selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};
            Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();

            Glide.with(this).load(picturePath).into(imageView);

           // Bitmap img = (BitmapFactory.decodeFile(picturePath));
            //imageView.setImageBitmap(img);

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

    @Override
    protected void onPause() {
        super.onPause();

        SharedPreferences sharedPreferences;
        sharedPreferences = this.getSharedPreferences(PREFS_NAME,MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("chave", true);
        editor.commit();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }


}
