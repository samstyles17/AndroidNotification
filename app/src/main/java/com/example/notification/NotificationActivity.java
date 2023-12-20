package com.example.notification;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

public class NotificationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        String infoSummary= getIntent().getStringExtra("InfoSummary");
        EditText ordSumm = findViewById(R.id.editTextInfo);

        ordSumm.setText(infoSummary);
    }
}