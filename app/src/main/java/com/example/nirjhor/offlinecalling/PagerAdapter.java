package com.example.nirjhor.offlinecalling;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.View;
import android.widget.Switch;

/**
 * Created by nirjhor on 2/26/2018.
 */

public class PagerAdapter extends FragmentPagerAdapter {

   public int nNoOfTabs;

    public PagerAdapter(FragmentManager fm, int nNoOfTabs) {
        super(fm);
        this.nNoOfTabs = nNoOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position)
        {
            case 0:
                Tab1 tab1 = new Tab1();
                return tab1;
            case 1:
                Tab2 tab2 = new Tab2();
                return tab2;
            case 2:
                Tab3 tab3 = new Tab3();
                return tab3;
            case 3:
                Tab4 tab4 = new Tab4();
                return tab4;
            case 4:
                Tab5 tab5 = new Tab5();
                return tab5;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return nNoOfTabs;
    }
}
