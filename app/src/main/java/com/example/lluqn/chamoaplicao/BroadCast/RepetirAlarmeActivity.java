package com.example.lluqn.chamoaplicao.BroadCast;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.lluqn.chamoaplicao.R;

import java.util.Calendar;

public class RepetirAlarmeActivity extends Activity {

    private static final int  SECONDS = 5;
    private static final int REPETIR = 10*1000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TextView textView = new TextView(this);
        textView.setText("alarm"+ SECONDS+ "repetir 10");

        schedule(SECONDS);
    }

    public void schedule(int secunds){

        Intent intent = new Intent("EXECUTE_ALARM");
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this,0,intent,0);

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.add(Calendar.SECOND, secunds);

        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        long time = calendar.getTimeInMillis();
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,time,REPETIR,pendingIntent);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Intent intent = new Intent("EXECUTE_ALARM");
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this,0,intent,0);

        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager.cancel(pendingIntent);
    }
}
