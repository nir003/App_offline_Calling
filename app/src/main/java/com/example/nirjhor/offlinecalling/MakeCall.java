package com.example.nirjhor.offlinecalling;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MakeCall extends AppCompatActivity {

    TextView tv_ip;
    String connected_ip ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_call);


        connected_ip = getIntent().getStringExtra("ip");

        tv_ip = findViewById(R.id.tv_ip);
        tv_ip.setText(connected_ip);

    }

    public void endCall(View view) {
        finish();
        //startActivity(new Intent(getApplicationContext(),MainActivity.class));
    }

    public void groupMessage(View view) {
        startActivity(new Intent(MakeCall.this,GroupMessage.class));
    }
    public void groupCall(View view) {
        startActivity(new Intent(MakeCall.this,GroupCall.class));
    }
}
