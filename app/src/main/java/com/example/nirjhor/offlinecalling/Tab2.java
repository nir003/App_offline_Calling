package com.example.nirjhor.offlinecalling;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.format.Formatter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

import static android.content.Context.WIFI_SERVICE;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Tab2#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Tab2 extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public View view;
    ImageButton btn_send, btn_update;
    private OnFragmentInteractionListener mListener;

    public LinearLayout lin_group;

    /*Copy*/
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
    /*Copy*/

    public TextView tv_connected_ip;

    public Tab2() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Tab2.
     */
    // TODO: Rename and change types and number of parameters
    public static Tab2 newInstance(String param1, String param2) {
        Tab2 fragment = new Tab2();
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

         view = inflater.inflate(R.layout.fragment_tab2, container, false);

        writeText = view.findViewById(R.id.writeText);

         btn_send = view.findViewById(R.id.btn_send);
         btn_update =  view.findViewById(R.id.btn_refresh);

        tv_connected_ip = view.findViewById(R.id.tv_connected_ip);

        tv_connected_ip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        myIp = getMyIp();


        /*code Database*/
        DatabaseOperation = new DatabaseOperation(getContext());
        /*code Database*/
        /*Code for Message List*/
        updateMessage();
        /*Code for Message List*/

        btn_send.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Toast.makeText(getContext(), "Send", Toast.LENGTH_SHORT).show();

                 send();


             }
         });
        btn_update.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 updateMessage();
             }
         });

        // Inflate the layout for this fragment
        return view;
    }

    /*getip*/
    public String getMyIp(){
        WifiManager wm = (WifiManager) getContext().getApplicationContext().getSystemService(WIFI_SERVICE);
        String ip = Formatter.formatIpAddress(wm.getConnectionInfo().getIpAddress());

        return ip;
    }
    /*getip*/

    public void addMessage(String message_send) {

        int roll =0;
        String myIp = this.myIp;
        String connectedIp = connected_Ip;
        String message = message_send;
        roll = 1;
        String isGroup = "1";


        SingleUserMessage singleUserMessage = new SingleUserMessage(myIp,connectedIp,message,roll,isGroup);

        boolean status = DatabaseOperation.addMessage_Group(singleUserMessage);

        if (status)
        {
            Toast.makeText(getContext(), "Successfull", Toast.LENGTH_SHORT).show();
            writeText.setText("");
        }
        else
        {
            Toast.makeText(getContext(), "Failed", Toast.LENGTH_SHORT).show();
        }

    }

    public void send() {

        final String message = writeText.getText().toString();
        Toast.makeText(getContext(), "ip: " + message, Toast.LENGTH_SHORT).show();


        if (!message.isEmpty()) {

            for (int i=0;i< SingleTon_for_socket.getInstance().sockets_connect_GROUP.size();i++){

                socket = SingleTon_for_socket.getInstance().sockets_connect_GROUP.get(i);

                //Toast.makeText(getContext(), ""+socket.getInetAddress().getHostAddress(), Toast.LENGTH_SHORT).show();

                try {
                    dataOutputStream = new DataOutputStream(socket.getOutputStream());
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
                } catch (IOException e) {
                    e.printStackTrace();
                }



            }


            addMessage(message);
            updateMessage();

        }
     /*   else {
            writeText.setError(getString(R.string.Empty_field_msg));
        }*/



    }

    public  void updateMessage()
    {
        /*Code for message List*/
        messageListView =  view.findViewById(R.id.messagelist);
        DatabaseOperation = new DatabaseOperation(getContext());
        messages = DatabaseOperation.getGroupUserMessage("1");
        messageAdapter = new messageAdapter(getContext(),messages);
        messageListView.setAdapter(messageAdapter);
        /*Code for message List*/
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
