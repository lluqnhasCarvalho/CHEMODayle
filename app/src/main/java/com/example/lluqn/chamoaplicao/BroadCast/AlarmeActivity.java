package com.example.lluqn.chamoaplicao.BroadCast;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Calendar;

public class AlarmeActivity extends Activity {

    private static final int SECONDS = 5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TextView textView = new TextView(this);
        textView.setText("Alarme "+ SECONDS );

        setContentView(SECONDS);
        schedule(SECONDS);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        Intent intent = new Intent("EXECUTE_ALARM");
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this,0,intent,0);

        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager.cancel(pendingIntent);
    }

    public  void schedule(int seconds){
        Intent intent= new Intent("EXECUTE_ALARM");
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this,0,intent,0);

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.add(calendar.SECOND, seconds);

        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        long time = calendar.getTimeInMillis();
        alarmManager.set(AlarmManager.RTC_WAKEUP, time, pendingIntent);
    }
}
