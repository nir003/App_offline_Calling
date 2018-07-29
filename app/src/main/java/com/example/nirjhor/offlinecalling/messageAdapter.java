package com.example.nirjhor.offlinecalling;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class messageAdapter extends ArrayAdapter<SingleUserMessage> {
    private Context context;
    private ArrayList<SingleUserMessage> singleUserMessages;

    public messageAdapter(Context context, ArrayList<SingleUserMessage> singleUserMessages) {

        super(context,R.layout.message_single_row,singleUserMessages);

        this.context = context;
        this.singleUserMessages = singleUserMessages;
    }


    class viewHolder
    {
        TextView tv_ipName;
        TextView tv_message;
        TextView tv_ipAddress;

        LinearLayout linLayout;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        messageAdapter.viewHolder holder;

        if(convertView == null)
        {
            holder = new messageAdapter.viewHolder();

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.message_single_row,parent,false);

            holder.tv_ipName =  convertView.findViewById(R.id.tv_ipName);
            holder.tv_message =  convertView.findViewById(R.id.tv_message);
            holder.tv_ipAddress =  convertView.findViewById(R.id.tv_ipAddress);

            holder.linLayout =  convertView.findViewById(R.id.linLayout);

            convertView.setTag(holder);
        }
        else {
            holder = (messageAdapter.viewHolder) convertView.getTag();
        }


        /*Extra code for style left-right by roll*/

        String myIp = singleUserMessages.get(position).getMyIp();
        String message = singleUserMessages.get(position).getMessage();
        String connectedIp = singleUserMessages.get(position).getConnectedIp();
        String ip_name = "";
        String ip = "";
        int roll = singleUserMessages.get(position).getUserRoll();


        /*Extra code*/
        int left = 10;
        int top = 100;
        int right = 10;
        int bottom = 10;

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        /*Extra code*/

        if(roll==1){
            ip_name = "My Ip:";
            ip = myIp;

            left = 60;

            params.gravity = Gravity.RIGHT;

        }else if(roll==2){
            ip_name = "Connected Ip:";
            ip = connectedIp;

            right = 60;

            params.gravity = Gravity.LEFT;
        }
        else {
            Toast.makeText(context, "Roll Error", Toast.LENGTH_LONG).show();
        }

        holder.tv_ipName.setText(ip_name);
        holder.tv_message.setText(message);
        holder.tv_ipAddress.setText(ip);


       /*Extra code*/


        params.setMargins(left, top, right, bottom);




        holder.linLayout.setLayoutParams(params);




       /*Extra code*/


        return convertView;
    }
    
}
