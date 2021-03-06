package com.example.nirjhor.offlinecalling;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.text.format.Formatter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import static android.content.Context.WIFI_SERVICE;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Tab5#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Tab5 extends Fragment {



    /*Code for Database*/
    private DatabaseOperation DatabaseOperation;
    /*Code for Database*/

    /*server*/

    boolean statusFlag;
    ServerSocket serverSocket;
    Socket socket;
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

    /*    This is my edit Code  */


    EditText tv_Others_Ip;
    Button btn_connect;

    TextView tv_socketIp;


    public Bundle savedInstanceState;

    public String ips[] = new String[100];

    public ListView listView;
    public Tab5.CustomAdapter customAdapter;
    public View view;

    public TextView tv_MyIp;

    public String sockets_ips[]=new String[200];
    /*    This is my edit Code  */

    public  String myIp;

    String othersIp;



    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;



    public Tab5() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Tab5.
     */
    // TODO: Rename and change types and number of parameters
    public static Tab5 newInstance(String param1, String param2) {
        Tab5 fragment = new Tab5();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        view =inflater.inflate(R.layout.fragment_tab5, container, false);



        myIp =getMyIp();

        tv_MyIp =view.findViewById(R.id.tv_MyIp);

        tv_MyIp.setText(myIp);

        listView = (ListView) view.findViewById(R.id.listView);
        customAdapter = new Tab5.CustomAdapter(ips);
        listView.setAdapter(customAdapter);
        /*    This is my edit Code  */

        // Inflate the layout for this fragment



        /*server*/
        thread = new Tab5.StartServer();
        thread.start();

        /*server*/



        /*Code for connect*/

        messageHandler = new Tab5.MessageHandler();

        tv_Others_Ip = view.findViewById(R.id.tv_Others_Ip);
        String[] baseIp = myIp.split("\\.");
         othersIp = baseIp[0]+"."+baseIp[1]+"."+baseIp[2]+".";
        tv_Others_Ip.setText(othersIp);
        //Toast.makeText(getContext(), ""+baseIp[0], Toast.LENGTH_SHORT).show();
        btn_connect = view.findViewById(R.id.btn_connect);

        tv_socketIp = view.findViewById(R.id.tv_socketIp);

        btn_connect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String others_ip = tv_Others_Ip.getText().toString();

                if (!others_ip.isEmpty()){
                    threadConnect = new Tab5.ConnectThread(others_ip);
                    threadConnect.start();
                }

                Toast.makeText(getContext(), "Onclick on ache baba", Toast.LENGTH_SHORT).show();
            }
        });
        /*Code for connect*/


        /*code Database*/
        DatabaseOperation = new DatabaseOperation(view.getContext());
        /*code Database*/

        updateList2();

        // Inflate the layout for this fragment
        return view;
    }

    public void updateList2(){
        /*make socket array ip */
        int lenght = SingleTon_for_socket.getInstance().sockets_connect.size();
        for (int i=0;i<lenght;i++){
            sockets_ips[i] = SingleTon_for_socket.getInstance().sockets_connect_GROUP.get(i).getInetAddress().getHostAddress();
            Toast.makeText(getContext(), "ip : "+sockets_ips[i], Toast.LENGTH_SHORT).show();
        }



        listView = (ListView) view.findViewById(R.id.listView);
        customAdapter = new Tab5.CustomAdapter(sockets_ips);
        listView.setAdapter(customAdapter);


        /*make socket array ip */
    }

    /*code for database*/
    public void addMessage(String message_send,String connected_Ip,String isGroup) {

        int roll =0;
        String myIp = this.myIp;
        String connectedIp = connected_Ip;
        String message = message_send;
        roll = 2;


        SingleUserMessage singleUserMessage = new SingleUserMessage(myIp,connectedIp,message,roll,isGroup);
        boolean status = DatabaseOperation.addMessage_Group(singleUserMessage);

        if (status)
        {
            Toast.makeText(getContext(), "Successfull", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(getContext(), "Failed", Toast.LENGTH_SHORT).show();
        }

    }
    /*code for database*/


    /*getip*/
    public String getMyIp(){
        WifiManager wm = (WifiManager) getContext().getApplicationContext().getSystemService(WIFI_SERVICE);
        String ip = Formatter.formatIpAddress(wm.getConnectionInfo().getIpAddress());

        return ip;
    }
    /*getip*/





    /*    This is my edit Code  */
    class CustomAdapter extends BaseAdapter {

        String upadtedIps[];

        public CustomAdapter(String[] upadtedIps) {
            this.upadtedIps = upadtedIps;
        }

        @Override
        public int getCount() {
            return ips.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @SuppressLint("RestrictedApi")
        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            convertView = getLayoutInflater(savedInstanceState).inflate(R.layout.customlayout2,null);

            ImageView img_Call,img_Message;

            TextView tv_ip,tv_number;

            //final  int list_position = position;

            tv_ip = (TextView) convertView.findViewById(R.id.tv_ip);

            tv_ip.setText(upadtedIps[position]);

            final String ip = tv_ip.getText().toString();

            return convertView;
        }
    }
    /*    This is my edit Code  */

    /*Code for my connection*/
    public class ConnectThread extends Thread{

        String ip ;


        public ConnectThread(String ipServer) {
            this.ip = ipServer;
        }


        @Override
        public void run() {

            try {
                clientSocket = new Socket(ip,8102);



                Message message = new Message();

                message.what = 2;
                message.obj = "SocketIp: "+clientSocket.getInetAddress().getHostAddress();
                messageHandler.sendMessage(message);


                SingleTon_for_socket.getInstance().sockets_connect_GROUP.add(clientSocket);

                //receiveClass = new Receive(socket);
                //receiveClass.start();
                //new Receive(clientSocket).start();
                //new Receive(clientSocket);
                new Thread(new Tab5.Receive(clientSocket)).start();

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
    /*Code for my connection*/

    //for message showing and error showing
    public class MessageHandler extends Handler{
        @Override
        public void handleMessage(Message msg) {
            if(msg.what == 1){
                tv_socketIp.setText((String)msg.obj);

                updateList();
            }
            if(msg.what == 2){
                tv_socketIp.setText((String)msg.obj);
                Toast.makeText(getContext(), "data : "+msg.obj, Toast.LENGTH_SHORT).show();
                updateList();
            }
            if(msg.what == 3){
                String dataArray[] = (String[]) msg.obj;

                addMessage(dataArray[0],dataArray[1],"1");

                // MessageSend.getInstance().updateMessage();


                //MessageSend.getInstance().updateMessage();

                //updateList();

               /* MessageSend ms = new MessageSend();
                ms.updateMessage();*/
            }


            super.handleMessage(msg);
        }


        public void updateList(){
            /*make socket array ip */
            int lenght = SingleTon_for_socket.getInstance().sockets_connect_GROUP.size();
            for (int i=0;i<lenght;i++){
                sockets_ips[i] = SingleTon_for_socket.getInstance().sockets_connect_GROUP.get(i).getInetAddress().getHostAddress();
                Toast.makeText(getContext(), "ip : "+sockets_ips[i], Toast.LENGTH_SHORT).show();
            }



            listView = (ListView) view.findViewById(R.id.listView);
            customAdapter = new Tab5.CustomAdapter(sockets_ips);
            listView.setAdapter(customAdapter);

            tv_Others_Ip.setText(othersIp);

            //tv_socketIp.setText("New Ip Connected !");

            /*make socket array ip */
        }
    }


    /*server*/
    class StartServer extends Thread{



        public void run() {
            socket = null;
            try {
                serverSocket = new ServerSocket(8102);

                while (true){
                    socket = serverSocket.accept();


                    SingleTon_for_socket.getInstance().sockets_connect_GROUP.add(socket);

                    receiveClass = new Receive(socket);
                    receiveClass.start();

                    //new Receive(socket).start();
                    //new Thread(new Receive(socket)).start();
                    Message message = new Message();

                    message.what = 2;
                    message.obj = "connect ";
                    messageHandler.sendMessage(message);
                }

            } catch (IOException e) {
                Message message = new Message();

                message.what = 2;
                message.obj = "error ";
                messageHandler.sendMessage(message);
                e.printStackTrace();
            }
        }
    }
    /*server*/


    public class Receive extends Thread{



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
                        Message message = new Message();
                        message.what = 3;
                        message.obj = dataArray;
                        messageHandler.sendMessage(message);

                        //message = null;

                        //addMessage(data,connected_ip);
                    }
                } catch (IOException e) {

                    //this.destroy();
                    try {
                        dataInputStream.close();
                        receiveFromclientSocket.close();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }

                    e.printStackTrace();

                }

            }
        }
    }























    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }







}
