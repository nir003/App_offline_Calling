package com.example.nirjhor.offlinecalling;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DB_Name = "Single_User_Message";
    public static final int DB_version = 1;



    public static final String DB_TableName = "tbl_single_user";

    public static final String TBL_col_id = "tbl_id";
    public static final String TBL_col_My_Ip = "tbl_myip";
    public static final String TBL_col_Connected_Ip = "tbl_connectedip";
    public static final String TBL_col_Message = "tbl_message";
    public static final String TBL_col_Role = "tbl_user_role";







    public static final String DB_TableName2 = "tbl_group";

    public static final String TBL_col_id2 = "tbl_id";
    public static final String TBL_col_My_Ip2 = "tbl_myip";
    public static final String TBL_col_Connected_Ip2 = "tbl_connectedip";
    public static final String TBL_col_Message2 = "tbl_message";
    public static final String TBL_col_Role2 = "tbl_user_role";














    public static final String Query_Create_Table_Car = "create table "+DB_TableName+"("+
            TBL_col_id+" integer primary key, "+
            TBL_col_My_Ip+" text, "+
            TBL_col_Message+" text, "+
            TBL_col_Role+" integer, "+
            TBL_col_Connected_Ip+" text);";





    public static final String Query_Create_Group_Table_Car = "create table "+DB_TableName2+"("+
            TBL_col_id2+" integer primary key, "+
            TBL_col_My_Ip2+" text, "+
            TBL_col_Message2+" text, "+
            TBL_col_Role2+" integer, "+
            TBL_col_Connected_Ip2+" text);";

    public DatabaseHelper(Context context) {
        super(context, DB_Name, null, DB_version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(Query_Create_Table_Car);
        sqLiteDatabase.execSQL(Query_Create_Group_Table_Car);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
