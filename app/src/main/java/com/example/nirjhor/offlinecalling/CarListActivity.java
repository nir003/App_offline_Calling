package com.example.nirjhor.offlinecalling;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Adapter;
import android.widget.ListView;

import java.util.ArrayList;

public class CarListActivity extends AppCompatActivity {

    private ListView carListView;

    private carAdapter caradapter;

    private ArrayList<Car> cars;

    private CarDatabaseOperation carDatabaseOperation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_list);

        carListView = (ListView) findViewById(R.id.carlist);

        carDatabaseOperation = new CarDatabaseOperation(this);

        cars = carDatabaseOperation.getAllCar();

        caradapter = new carAdapter(this,cars);

        carListView.setAdapter(caradapter);


    }
}
