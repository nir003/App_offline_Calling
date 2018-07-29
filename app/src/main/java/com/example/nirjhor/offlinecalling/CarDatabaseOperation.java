package com.example.nirjhor.offlinecalling;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

/**
 * Created by nirjhor on 11/2/2017.
 */

public class CarDatabaseOperation {

    private CarDatabaseHelper carDatabaseHelper;
    private SQLiteDatabase sqLiteDatabase;
    private Car car;

    public CarDatabaseOperation(Context context) {
        carDatabaseHelper = new CarDatabaseHelper(context);
    }

    public void open()
    {
        sqLiteDatabase = carDatabaseHelper.getWritableDatabase();
    }

    public void close()
    {
        carDatabaseHelper.close();
    }

    public boolean addCar(Car car)
    {
        this.open();

        ContentValues values = new ContentValues();

        values.put(CarDatabaseHelper.TBL_col_Car_Name,car.getCarName());
        values.put(CarDatabaseHelper.TBL_col_Manu,car.getCarManu());

        // id = 1,2,3 etc if successfull ... id = -1 if not success
        long id = sqLiteDatabase.insert(carDatabaseHelper.DB_TableName,null,values);
        Log.e("Success/Not", "addCar:insert id = "+id+"");   //it shows the id in logcat

        this.close();

        if(id > 0)
        {
            return true;
        }
        else
            return false;
    }

    public ArrayList<Car> getAllCar()
    {
        ArrayList<Car> cars = new ArrayList<>();
        this.open();

         Cursor cursor = sqLiteDatabase.query(carDatabaseHelper.DB_TableName,null,null,null,null,null,null);
        cursor.moveToFirst();
        if(cursor != null && cursor.getCount() > 0)
        {
            for (int i =0; i< cursor.getCount(); i++)   //while(cursor.close())
            {
                int id = cursor.getInt(cursor.getColumnIndex(carDatabaseHelper.TBL_col_id));

                String name = cursor.getString(cursor.getColumnIndex(carDatabaseHelper.TBL_col_Car_Name));
                String manu = cursor.getString(cursor.getColumnIndex(carDatabaseHelper.TBL_col_Manu));

                car = new Car(id,name,manu);
                cars.add(car);

                cursor.moveToNext();
            }
        }
        cursor.close();

        this.close();
        return cars;
    }

}
