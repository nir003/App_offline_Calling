package com.example.nirjhor.offlinecalling;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by nirjhor on 11/2/2017.
 */

public class carAdapter extends ArrayAdapter<Car> {
    private Context context;
    private ArrayList<Car> cars;

    public carAdapter(@NonNull Context context, ArrayList<Car> cars) {
        super(context,R.layout.car_single_row,cars);
        this.context = context;
        this.cars = cars;
    }

    class viewHolder
    {
        ImageView carImage;
        TextView carNameTV;
        TextView carManuTV;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        viewHolder holder;

        if(convertView == null)
        {
            holder = new viewHolder();

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.car_single_row,parent,false);

            holder.carImage = (ImageView) convertView.findViewById(R.id.carImage);
            holder.carNameTV = (TextView) convertView.findViewById(R.id.carName);
            holder.carManuTV = (TextView) convertView.findViewById(R.id.carManu);

            convertView.setTag(holder);
        }
        else {
            holder = (viewHolder) convertView.getTag();
        }

        holder.carNameTV.setText(cars.get(position).getCarName());
        holder.carManuTV.setText(cars.get(position).getCarManu());


        return convertView;
    }
}
