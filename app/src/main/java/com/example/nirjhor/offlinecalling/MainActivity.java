package com.example.nirjhor.offlinecalling;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.v4.view.*;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements
        Tab1.OnFragmentInteractionListener,
        Tab2.OnFragmentInteractionListener,
        Tab3.OnFragmentInteractionListener,
        Tab4.OnFragmentInteractionListener,
        Tab5.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tablayout);

        //tabLayout.addTab(tabLayout.newTab().setText("Tab 1"));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_wifi_white_24dp));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_group_white_24dp));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.history));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_person_white_24dp));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_person_white_24dp));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);






        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        final PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager(),tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.setOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));


        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());

                Toast.makeText(MainActivity.this, "tab: "+tab.getPosition(), Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        // tableLayout.addTab(tableLayout.newTab().setText("Tab 1"));

        /*
         * android:theme="@style/ThemeOverlay.AppCompat.Dark.ActionbBar"
         * */
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
