package com.example.nirjhor.offlinecalling;

/**
 * Created by nirjhor on 11/2/2017.
 */

public class Car {
    private int carID;
    private String carName;
    private String carManu;

    public Car(int carID, String carName, String carManu) {
        this.carID = carID;
        this.carName = carName;
        this.carManu = carManu;
    }

    public Car(String carName, String carManu) {
        this.carName = carName;
        this.carManu = carManu;
    }

    public int getCarID() {
        return carID;
    }

    public String getCarName() {
        return carName;
    }

    public String getCarManu() {
        return carManu;
    }
}
