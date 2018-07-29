package com.example.nirjhor.offlinecalling;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MessageSend extends AppCompatActivity {

    TextView tv_connected_ip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_send);

        tv_connected_ip = findViewById(R.id.tv_connected_ip);
        tv_connected_ip.setText("Connected ip:"+getIntent().getStringExtra("ip"));
    }
}
