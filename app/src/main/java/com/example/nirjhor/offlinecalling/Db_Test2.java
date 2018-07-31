package com.example.nirjhor.offlinecalling;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Db_Test2 extends AppCompatActivity {

    private EditText ET_MyIp;
    private EditText ET_ConnectedIp;
    private EditText ET_Message;
    private EditText ET_userRoll;

    private DatabaseOperation DatabaseOperation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db__test2);

        /*mycode*/
        ET_MyIp = (EditText) findViewById(R.id.ET_MyIp);
        ET_ConnectedIp = (EditText) findViewById(R.id.ET_ConnectedIp);
        ET_Message = (EditText) findViewById(R.id.ET_Message);
        ET_userRoll = (EditText) findViewById(R.id.ET_userRoll);

        DatabaseOperation = new DatabaseOperation(this);
        /*mycode*/
    }

    public void addMessage(View view) {

        int roll =0;
        String myIp = ET_MyIp.getText().toString();
        String connectedIp = ET_ConnectedIp.getText().toString();
        String message = ET_Message.getText().toString();
         roll = Integer.parseInt(ET_userRoll.getText().toString());

        if(myIp.isEmpty())
        {
            ET_MyIp.setError(getString(R.string.Empty_field_msg));
        }
        else if(connectedIp.isEmpty())
        {
            ET_ConnectedIp.setError(getString(R.string.Empty_field_msg));
        }
        else if(message.isEmpty())
        {
            ET_Message.setError(getString(R.string.Empty_field_msg));
        }
        else if( roll == 0 )
        {
            ET_userRoll.setError(getString(R.string.Empty_field_msg));
        }
        else
        {
            SingleUserMessage singleUserMessage = new SingleUserMessage(myIp,connectedIp,message,roll);
            boolean status = DatabaseOperation.addMessage(singleUserMessage);

            if (status)
            {
                Toast.makeText(this, "Successfull", Toast.LENGTH_SHORT).show();
                ET_MyIp.setText("");
                ET_ConnectedIp.setText("");
                ET_Message.setText("");
                ET_userRoll.setText("");
            }
            else
            {
                Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void viewMessage(View view) {
        startActivity(new Intent(getApplicationContext(),MessageListActivity.class));
    }
}
