package com.example.nirjhor.offlinecalling;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Tab3#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Tab3 extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;


     /*    This is my edit Code  */
     public Bundle savedInstanceState;
     public String name[] = {"Person 1","Person 2","Person 3","Person 4","Person 5","Person 6","Person 7","Person 8"};
     public String ips[] = {"192.168.4.3","192.168.4.1","192.168.4.6","192.168.4.101","192.168.4.8",
             "192.168.4.9","192.168.4.5","192.168.4.0"};
     public String times[] = {"12:00","12:03","12:05","12:10","12:30","12:35","12:40","1:00"};
     public String time_while[] = {"3:30","2:00","5:50","20:21","5:00","5:10","5:59","1:10"};
      /*    This is my edit Code  */

    public Tab3() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Tab3.
     */
    // TODO: Rename and change types and number of parameters
    public static Tab3 newInstance(String param1, String param2) {
        Tab3 fragment = new Tab3();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        /*Mycode*/
        this.savedInstanceState = savedInstanceState;
        /*Mycode*/
        //ListView listView = find
    }




    /*   Now this is our oncreate Actually :D cause it has the view   */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_tab3, container, false);

         /*    This is my edit Code  */
        ListView listView = (ListView) view.findViewById(R.id.listView);

        CustomAdapter customAdapter = new CustomAdapter();
        listView.setAdapter(customAdapter);
         /*    This is my edit Code  */

        // Inflate the layout for this fragment
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }


    /*    This is my edit Code  */
    class CustomAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return name.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @SuppressLint("RestrictedApi")
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView = getLayoutInflater(savedInstanceState).inflate(R.layout.customlayout_history,null);

            ImageView img_Call,img_Message;

            img_Message = (ImageView) convertView.findViewById(R.id.img_Message);
            img_Call = (ImageView) convertView.findViewById(R.id.img_Call);

            TextView tv_ip,tv_number,tv_time,tv_while;

            tv_ip = (TextView) convertView.findViewById(R.id.tv_ip);
            tv_time = (TextView) convertView.findViewById(R.id.tv_time);
            tv_while = (TextView) convertView.findViewById(R.id.tv_while);

            tv_ip.setText(ips[position]);
            tv_time.setText(times[position]);
            tv_while.setText(time_while[position]);

            final String ip = tv_ip.getText().toString();

            img_Call.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(getContext(), "Call : "+ip, Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(getContext(),MakeCall.class);
                    i.putExtra("ip",ip);
                    startActivity(i);
                }
            });
            img_Message.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(getContext(), "Message : "+ip, Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(getContext(),MessageSend.class);
                    i.putExtra("ip",ip);
                    startActivity(i);
                }
            });


            return convertView;
        }
    }
     /*    This is my edit Code  */
}
