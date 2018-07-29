package com.example.nirjhor.offlinecalling;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class DbTest extends AppCompatActivity {

    private EditText carNameET;
    private EditText carManuET;

    private CarDatabaseOperation carDatabaseOperation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db_test);


        /*mycode*/
        carNameET = (EditText) findViewById(R.id.carName);
        carManuET = (EditText) findViewById(R.id.carManu);

        carDatabaseOperation = new CarDatabaseOperation(this);
        /*mycode*/


    }

    public void addCar(View view) {
        String name = carNameET.getText().toString();
        String manu = carManuET.getText().toString();

        if(name.isEmpty())
        {
            carNameET.setError(getString(R.string.Empty_field_msg));
        }
        else if(manu.isEmpty())
        {
            carManuET.setError(getString(R.string.Empty_field_msg));
        }
        else
        {
            Car car = new Car(name,manu);
            boolean status = carDatabaseOperation.addCar(car);

            if (status)
            {
                Toast.makeText(this, "Successfull", Toast.LENGTH_SHORT).show();
                carNameET.setText("");
                carManuET.setText("");
            }
            else
            {
                Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show();
            }
        }

    }


    public void viewCar(View view) {
        startActivity(new Intent(getApplicationContext(),CarListActivity.class));

    }


}
