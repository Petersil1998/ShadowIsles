package com.example.shadowisles.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.shadowisles.R;
import com.example.shadowisles.entities.LobbyRequestEntity;
import com.example.shadowisles.entities.Queue;
import com.example.shadowisles.exception.IPNotFoundException;
import com.example.shadowisles.exception.LobbyNotCreatableException;
import com.example.shadowisles.exception.LockfileNotFoundException;
import com.example.shadowisles.exception.TimeoutException;
import com.example.shadowisles.util.ShadowIslesClient;
import com.example.shadowisles.util.Util;

public class NewLobbyActivity extends AppCompatActivity {

    private Spinner spinner;
    private Button newLobbyFinished;

    private ShadowIslesClient shadowIslesClient;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_lobby_layout);
        spinner = findViewById(R.id.queues);
        newLobbyFinished = findViewById(R.id.createLobbyFinished);

        if(Util.availableQueues != null) {
            ArrayAdapter<Queue> adapter = new ArrayAdapter<>(this,
                    android.R.layout.simple_spinner_item, Util.availableQueues);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(adapter);
        }

        newLobbyFinished.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Queue queue = (Queue) spinner.getSelectedItem();
                LobbyRequestEntity lobbyRequest = new LobbyRequestEntity();
                lobbyRequest.setQueueId(queue.getQueueId());
                if(queue.getQueueId() == 0){
                    lobbyRequest.setCustom(true);
                }
                try {
                    shadowIslesClient.createLobby(lobbyRequest);
                    finish();
                } catch (LockfileNotFoundException | TimeoutException | IPNotFoundException | LobbyNotCreatableException e) {
                    Toast.makeText(NewLobbyActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
