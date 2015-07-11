package com.example.michael.notificationprogress;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class MainActivity extends Activity {

    private NotificationManager manager;
    private static final int NOTIFICATION_ID = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

    }

    public void notify(View view){
        final Notification.Builder builder = new Notification.Builder(this);
        builder.setContentTitle("Baixando arquivo").setContentText("Aguarde...").setSmallIcon(R.drawable.ic_file_download_white_24dp);
        builder.setProgress(0, 0, true);
        manager.notify(NOTIFICATION_ID, builder.build());

        new Thread(new Runnable() {
            @Override
            public void run() {
                SystemClock.sleep(5000);
                builder.setContentTitle("Arquivo baixando");
                builder.setContentText("Finalizado");
                builder.setProgress(0, 0, false);
                manager.notify(NOTIFICATION_ID, builder.build());
            }
        }).start();
    }

    public void notify2(View view){
        final Notification.Builder builder = new Notification.Builder(this);
        builder.setContentTitle("Baixando arquivo").setContentText("Aguarde...").setSmallIcon(R.drawable.ic_file_download_white_24dp);
        manager.notify(NOTIFICATION_ID, builder.build());

        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = 0; i < 100; i++){
                    builder.setProgress(100, i, false);
                    manager.notify(NOTIFICATION_ID, builder.build());
                    SystemClock.sleep(100);
                }

                builder.setContentTitle("Arquivo baixando");
                builder.setContentText("Finalizado");
                builder.setProgress(0, 0, false);
                manager.notify(NOTIFICATION_ID, builder.build());
            }
        }).start();

    }
}
