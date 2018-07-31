package com.example.nirjhor.offlinecalling;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MakeCall extends AppCompatActivity {

    TextView tv_ip,tv_time,tv_ring;
    String connected_ip ;
    int  i= 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_call);


        connected_ip = getIntent().getStringExtra("ip");

        tv_ip = findViewById(R.id.tv_ip);
        tv_time = findViewById(R.id.tv_time);
        tv_ring = findViewById(R.id.tv_ring);
        tv_ip.setText(connected_ip);


        Handler handler = new Handler();


        handler.postDelayed(new Runnable() {
            @Override    public void run() {
                //Do wharever you want
                i++;
                if(i==4){
                    tv_ring.setText("");
                }
                tv_time.setText("sec : "+i);
            }
        }, 1000);

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
