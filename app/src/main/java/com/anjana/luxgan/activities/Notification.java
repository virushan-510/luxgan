package com.anjana.luxgan.activities;

import static android.content.Context.NOTIFICATION_SERVICE;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.anjana.luxgan.R;

public class Notification extends AppCompatActivity {

    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        button = findViewById(R.id.click);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NotificationCompat.Builder mbuilder =(NotificationCompat.Builder)
                        new NotificationCompat.Builder(getApplicationContext())
                                .setSmallIcon(R.drawable.notification)
                                .setContentTitle("Notification")
                                .setContentText("Please ON Your Location!");
                NotificationManager notificationManager = (NotificationManager)
                        getSystemService(NOTIFICATION_SERVICE);
                notificationManager.notify(0,mbuilder.build());


            }
        });
    }
}