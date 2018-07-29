package com.example.nirjhor.offlinecalling;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by nirjhor on 11/2/2017.
 */

public class CarDatabaseHelper extends SQLiteOpenHelper {
    public static final String DB_Name = "Car Database";
    public static final int DB_version = 1;

    public static final String DB_TableName = "tbl_car";

    public static final String TBL_col_id = "tbl_id";
    public static final String TBL_col_Car_Name = "tbl_name";
    public static final String TBL_col_Manu = "tbl_manu";

    public static final String Query_Create_Table_Car = "create table "+DB_TableName+"("+
            TBL_col_id+" integer primary key, "+
            TBL_col_Car_Name+" text, "+
            TBL_col_Manu+" text);";

    public CarDatabaseHelper(Context context) {
        super(context, DB_Name, null, DB_version); //  super(context, name: DBname, factory: null, version: int number);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Query_Create_Table_Car);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
