package com.example.nirjhor.offlinecalling;

import android.content.Context;

import java.net.Socket;
import java.util.ArrayList;

public class SingleTon_for_socket {

    public static Context socketContext;

    private static SingleTon_for_socket ton;

    public SingleTon_for_socket(Context socketContext) {
        this.socketContext = socketContext;
    }

    public static SingleTon_for_socket getInstance() {
        if (ton ==null){
            ton = new SingleTon_for_socket(socketContext);
        }
        return ton;
    }



    ArrayList<Socket> sockets_connect = new ArrayList<>();

    ArrayList<Socket> sockets_connect_GROUP = new ArrayList<>();

    //ArrayList<Socket> sockets_connectedWithOthers;



}
