package com.example.nirjhor.offlinecalling;

import android.content.Intent;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

public class MessageSend extends AppCompatActivity {

    TextView tv_connected_ip;


    int list_position = 0;
    public Socket socket;

    public DataOutputStream dataOutputStream;

    public EditText writeText;

    /*Code for Database*/
    private DatabaseOperation DatabaseOperation;
    /*Code for Database*/

    /*code for message list*/
    private ListView messageListView;
    private messageAdapter messageAdapter;
    private ArrayList<SingleUserMessage> messages;
    /*code for message list*/

    public String myIp = "faka";
    public String connected_Ip = "faka";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_send);

        writeText = findViewById(R.id.writeText);

        tv_connected_ip = findViewById(R.id.tv_connected_ip);

        list_position = getIntent().getIntExtra("position", 0);
        myIp = getIntent().getStringExtra("myIp");
        connected_Ip = getIntent().getStringExtra("ip");



        tv_connected_ip.setText("Connected ip:" + connected_Ip + "    position: " + list_position);

        socket = SingleTon_for_socket.getInstance().sockets_connect.get(list_position);


        try {
            dataOutputStream = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }



        /*code Database*/
        DatabaseOperation = new DatabaseOperation(this);
        /*code Database*/


        /*Code for Message List*/
        updateMessage();
        /*Code for Message List*/


    }

    public void addMessage(String message_send) {

        int roll =0;
        String myIp = this.myIp;
        String connectedIp = connected_Ip;
        String message = message_send;
        roll = 1;


            SingleUserMessage singleUserMessage = new SingleUserMessage(myIp,connectedIp,message,roll);
            boolean status = DatabaseOperation.addMessage(singleUserMessage);

            if (status)
            {
                Toast.makeText(this, "Successfull", Toast.LENGTH_SHORT).show();
                writeText.setText("");
            }
            else
            {
                Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show();
            }

    }

    public void send(View view) {

        final String message = writeText.getText().toString();
        Toast.makeText(this, "ip: " + message, Toast.LENGTH_SHORT).show();


        if (!message.isEmpty()) {


            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    try {

                        dataOutputStream.writeUTF(message);


                    } catch (IOException e) {

                        e.printStackTrace();

                    }
                }
            };
            new Thread(runnable).start();





            addMessage(message);
            updateMessage();

        }
        else {
            writeText.setError(getString(R.string.Empty_field_msg));
        }



    }

    public void view(View view) {
        startActivity(new Intent(getApplicationContext(),MessageListActivity.class));
    }

/*    public static MessageSend instance;
    public static MessageSend getInstance(){
        if(instance == null){
            instance = new MessageSend();
        }

        return instance;

    }*/

    public  void updateMessage()
    {
        /*Code for message List*/
        messageListView =  findViewById(R.id.messagelist);
        DatabaseOperation = new DatabaseOperation(this);
        messages = DatabaseOperation.getSingleUserMessage(connected_Ip);
        messageAdapter = new messageAdapter(this,messages);
        messageListView.setAdapter(messageAdapter);
        /*Code for message List*/
    }


    public void update(View view) {
        updateMessage();
    }
}
