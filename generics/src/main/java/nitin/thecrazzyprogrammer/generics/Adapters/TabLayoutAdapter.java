package nitin.thecrazzyprogrammer.generics.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

import nitin.thecrazzyprogrammer.generics.Models.TabData;

/**
 * Created by Nitin Khurana on 1/21/2018.
 */
public class TabLayoutAdapter extends FragmentPagerAdapter {

    ArrayList<TabData> tabs;

    public TabLayoutAdapter(FragmentManager fm, ArrayList<TabData> tabs) {
        super(fm);
        this.tabs = tabs;
    }

    @Override
    public Fragment getItem(int position) {

        Fragment fragment = tabs.get(position).getFragment();
        fragment.setArguments(tabs.get(position).getArguments());
        return fragment;
    }


    @Override
    public CharSequence getPageTitle(int position) {
        return tabs.get(position).getTitle();
    }

    @Override
    public int getCount() {
        return tabs.size();
    }
}
