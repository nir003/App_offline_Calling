package com.example.nirjhor.offlinecalling;

import android.content.Intent;
import android.os.Handler;
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
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Group_MessageSend extends AppCompatActivity {

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






    /*tab1*/


    /*server*/

    boolean statusFlag;
    ServerSocket serverSocket;

    Thread thread,receiveClass;

    //Send sendMessageClass;
    /*server*/


    /*code for connect*/



    Socket clientSocket = null;
    Handler clientHandler,messageHandler;
    Thread clientThread,threadConnect;
    Thread clientReceive;
    //Send sendToServerClass;
    /*code for connect*/
    /*tab1*/




    EditText tv_Others_Ip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group__message_send);


        tv_Others_Ip = findViewById(R.id.tv_Others_Ip);

        writeText = findViewById(R.id.writeText);

        tv_connected_ip = findViewById(R.id.tv_connected_ip);






//        /*code Database*/
//        DatabaseOperation = new DatabaseOperation(this);
//        /*code Database*/


        /*Code for Message List*/
//        updateMessage();
        /*Code for Message List*/
    }


    public void addMessage(String message_send,String connected_Ip) {

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
       // Toast.makeText(this, "ip: " + message, Toast.LENGTH_SHORT).show();


        if (!message.isEmpty()) {


            Runnable runnable = new Runnable() {
                @Override
                public void run() {

                    for (int i=0;i<SingleTon_for_socket.getInstance().sockets_connect_GROUP.size();i++){

                        socket = SingleTon_for_socket.getInstance().sockets_connect_GROUP.get(i);
                        try {


                            dataOutputStream = new DataOutputStream(socket.getOutputStream());
                            dataOutputStream.writeUTF(message);


                        } catch (IOException e) {

                            e.printStackTrace();

                        }
                    }

                }
            };
            new Thread(runnable).start();


           // addMessage(message,myIp);
           // updateMessage();

        }
        else {
           // writeText.setError(getString(R.string.Empty_field_msg));
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
//
//    public  void updateMessage()
//    {
//        /*Code for message List*/
//        messageListView =  findViewById(R.id.messagelist);
//        DatabaseOperation = new DatabaseOperation(this);
//        messages = DatabaseOperation.getSingleUserMessage(connected_Ip);
//        messageAdapter = new messageAdapter(this,messages);
//        messageListView.setAdapter(messageAdapter);
//        /*Code for message List*/
//    }


//    public void update(View view) {
//        updateMessage();
//    }

    public void group_Connet(View view) {
        String others_ip = tv_Others_Ip.getText().toString();

        if (!others_ip.isEmpty()){
            threadConnect = new ConnectThread(others_ip);
            threadConnect.start();
        }


    }


    /*Code for my connection*/
    public class ConnectThread extends Thread{

        String ip ;


        public ConnectThread(String ipServer) {
            this.ip = ipServer;
        }

        Message message = new Message();
        @Override
        public void run() {

            try {
                clientSocket = new Socket(ip,8101);




               // message.what = 2;
              //  message.obj = "SocketIp: "+clientSocket.getInetAddress().getHostAddress();
               // messageHandler.sendMessage(message);


                SingleTon_for_socket.getInstance().sockets_connect_GROUP.add(clientSocket);

                //new Receive(clientSocket).start();
                //new Receive(clientSocket);
               new Thread(new Receive(clientSocket)).start();

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
    /*Code for my connection*/

    public class Receive implements Runnable{

        Message message = new Message();

        String connected_ip = "";

        Socket receiveFromclientSocket;
        DataInputStream dataInputStream;
        public Receive(Socket socket){



            this.receiveFromclientSocket = socket;
            try {
                dataInputStream = new DataInputStream(receiveFromclientSocket.getInputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }

            connected_ip = receiveFromclientSocket.getInetAddress().getHostAddress();

        }



        @Override
        public void run() {

            while (true){

                try {
                    String data = dataInputStream.readUTF();

                    String dataArray[] = {data,connected_ip};

                    if (!data.isEmpty()){
                        message.what = 3;
                        message.obj = dataArray;
                        messageHandler.sendMessage(message);


                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
    }

    public class MessageHandler extends Handler{
        @Override
        public void handleMessage(Message msg) {


            if(msg.what == 3){
                String dataArray[] = (String[]) msg.obj;

                addMessage(dataArray[0],dataArray[1]);


                //MessageSend.getInstance().updateMessage();

                //updateList();
            }


            super.handleMessage(msg);
        }



    }

//
//    public void updateListConnection(){
//        String ip_connected = "";
//
//        for (int i =0;i< SingleTon_for_socket.getInstance().sockets_connect_GROUP.size();i++){
//
//            ip_connected = ip_connected+","+SingleTon_for_socket.getInstance().sockets_connect_GROUP.get(list_position).getInetAddress().getHostAddress();
//        }
//
//        tv_connected_ip.setText(ip_connected);
//    }
}
