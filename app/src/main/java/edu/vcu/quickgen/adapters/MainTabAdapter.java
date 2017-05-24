package edu.vcu.quickgen.adapters;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v13.app.FragmentPagerAdapter;

import edu.vcu.quickgen.fragments.CubeListFragment;
import edu.vcu.quickgen.fragments.ToolsListFragment;


public class MainTabAdapter extends FragmentPagerAdapter {

    public MainTabAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch(position){
            case 0:
                return CubeListFragment.newInstance();
            case 1:
                return ToolsListFragment.newInstance();
            default:
                throw new IndexOutOfBoundsException("Requested fragment beyond maximum position");
        }
    }

    @Override
    public int getCount() {
        return 2;
    }
}