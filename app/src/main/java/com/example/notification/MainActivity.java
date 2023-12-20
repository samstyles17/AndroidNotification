package com.example.notification;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity
{

    private EditText name,email,phno;
    private Button notify;
    String summary="";
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence channelName = "Order Notifications";
            String channelId = "order_channel";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(channelId, channelName, importance);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }

        name = findViewById(R.id.nameeditText);
        email = findViewById(R.id.emaileditText);
        phno = findViewById(R.id.phoneeditText);

        notify = findViewById(R.id.notifyButton);

        notify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                summary+= "Information Summary";
                summary+= "\n";
                summary+=  name.getText().toString().trim();
                summary+= "\n";
                summary+=  email.getText().toString().trim();
                summary+= "\n";
                summary+= phno.getText().toString().trim();

                NotificationCompat.Builder builder = new NotificationCompat.Builder(MainActivity.this, "order_channel")
                        .setSmallIcon(R.drawable.baseline_notifications_24)
                        .setContentTitle("Info Summary")
                        .setContentText("Your info summary is ready.")
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT);

                Intent i = new Intent(MainActivity.this,NotificationActivity.class);
                i.putExtra("InfoSummary",summary);
                PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this, 0, i, PendingIntent.FLAG_UPDATE_CURRENT);
                builder.setContentIntent(pendingIntent);

                NotificationManagerCompat notificationManager = NotificationManagerCompat.from(MainActivity.this);
                notificationManager.notify(1, builder.build());
            }
        });

    }
}