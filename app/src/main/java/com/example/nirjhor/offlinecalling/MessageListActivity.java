package com.example.nirjhor.offlinecalling;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class MessageListActivity extends AppCompatActivity {


    private ListView messageListView;

    private messageAdapter messageAdapter;

    private ArrayList<SingleUserMessage> messages;
    private DatabaseOperation DatabaseOperation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_list);

        messageListView =  findViewById(R.id.messagelist);

        DatabaseOperation = new DatabaseOperation(this);

        messages = DatabaseOperation.getAllMessage();

        messageAdapter = new messageAdapter(this,messages);

        messageListView.setAdapter(messageAdapter);
    }
}
