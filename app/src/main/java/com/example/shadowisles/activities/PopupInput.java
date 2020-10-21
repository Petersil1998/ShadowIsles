package com.example.shadowisles.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.shadowisles.R;
import com.example.shadowisles.config.Config;

public class PopupInput extends Activity {
    EditText ipAddress;
    Button apply;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_popup);
        ipAddress = findViewById(R.id.ipAddress);
        ipAddress.setText(Config.getInstance(this).getIPAddress());

        apply = findViewById(R.id.find);

        apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ipAddress = PopupInput.this.ipAddress.getText().toString().trim();
                if(ipAddress.length() > 0) {
                    Intent resultIntent = new Intent();
                    resultIntent.putExtra("ipAddress", ipAddress);
                    setResult(Activity.RESULT_OK, resultIntent);
                    finish();
                }
            }
        });
    }
}
