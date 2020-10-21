package com.example.shadowisles.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.shadowisles.R;
import com.example.shadowisles.config.Config;
import com.example.shadowisles.entities.QueueEligibility;
import com.example.shadowisles.util.ShadowIslesClient;
import com.example.shadowisles.util.ThreshAPIClient;
import com.example.shadowisles.util.Util;

import java.util.List;

public class SettingsActivity extends AppCompatActivity {

    private Config config;

    private Button start;
    private EditText ipTxt;

    private ShadowIslesClient shadowIslesClient;
    private ThreshAPIClient threshAPIClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_layout);

        start = findViewById(R.id.start);
        ipTxt = findViewById(R.id.ip);

        config = Config.getInstance(this);
        threshAPIClient = ThreshAPIClient.getInstance();

        if(config.getIPAddress() != null){
            ShadowIslesClient.updateIP(config.getIPAddress());
            startLobbyActivity();
        }

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ip = ipTxt.getText().toString().trim();
                if(ip.length() > 0) {
                    config.setIPAddress(ip);
                    startLobbyActivity();
                }
            }
        });
    }

    private void startLobbyActivity(){
        shadowIslesClient = ShadowIslesClient.getInstance();
        initQueues();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void initQueues(){
        List<QueueEligibility> allQueues = null;
        try {
            allQueues = shadowIslesClient.getAllQueues();
        } catch (Exception ignored) {}
        if(allQueues != null) {
            for (QueueEligibility queueEligibility : allQueues) {
                if(queueEligibility.isEligible()){
                    Util.availableQueues.add(threshAPIClient.getQueue(queueEligibility.getQueueId()));
                }
            }
        }
    }
}
