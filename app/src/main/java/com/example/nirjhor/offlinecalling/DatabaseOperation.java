package com.example.nirjhor.offlinecalling;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

public class DatabaseOperation {
    private DatabaseHelper DatabaseHelper;
    private SQLiteDatabase sqLiteDatabase;
    private SingleUserMessage userMessage;

    public DatabaseOperation(Context context) {
        DatabaseHelper = new DatabaseHelper(context);
    }

    public void open()
    {
        sqLiteDatabase = DatabaseHelper.getWritableDatabase();
    }
    public void close()
    {
        DatabaseHelper.close();
    }

    public boolean addMessage(SingleUserMessage singleUserMessage)
    {
        this.open();

        ContentValues values = new ContentValues();

        values.put(DatabaseHelper.TBL_col_My_Ip,singleUserMessage.getMyIp());
        values.put(DatabaseHelper.TBL_col_Connected_Ip,singleUserMessage.getConnectedIp());
        values.put(DatabaseHelper.TBL_col_Message,singleUserMessage.getMessage());
        values.put(DatabaseHelper.TBL_col_Role,singleUserMessage.getUserRoll());

        // id = 1,2,3 etc if successfull ... id = -1 if not success
        long id = sqLiteDatabase.insert(DatabaseHelper.DB_TableName,null,values);

        Log.e("Success/Not", "addCar:insert id = "+id+"");   //it shows the id in logcat

        this.close();

        if(id > 0)
        {
            return true;
        }
        else
            return false;
    }

    public ArrayList<SingleUserMessage> getAllMessage()
    {
        ArrayList<SingleUserMessage> singleUserMessages = new ArrayList<>();
        this.open();

        Cursor cursor = sqLiteDatabase.query(DatabaseHelper.DB_TableName,null,null,null,null,null,null);
        cursor.moveToFirst();
        if(cursor != null && cursor.getCount() > 0)
        {
            for (int i =0; i< cursor.getCount(); i++)   //while(cursor.close())
            {
                int id = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.TBL_col_id));

                String myIp = cursor.getString(cursor.getColumnIndex(DatabaseHelper.TBL_col_My_Ip));
                String connectedIp = cursor.getString(cursor.getColumnIndex(DatabaseHelper.TBL_col_Connected_Ip));
                String message = cursor.getString(cursor.getColumnIndex(DatabaseHelper.TBL_col_Message));
                int roll = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.TBL_col_Role));

                userMessage = new SingleUserMessage(id,myIp,connectedIp,message,roll);
                singleUserMessages.add(userMessage);

                cursor.moveToNext();
            }
        }
        cursor.close();

        this.close();
        return singleUserMessages;
    }
}
