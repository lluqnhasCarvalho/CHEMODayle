package com.example.lluqn.chamoaplicao.Adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.lluqn.chamoaplicao.fragment.ConsultaFragment;
import com.example.lluqn.chamoaplicao.fragment.TratamentoFragment;


public class PageAdapter extends FragmentPagerAdapter {

    private int numOfTabs;
    public PageAdapter(FragmentManager fm, int numOfTabs) {
        super(fm);
        this.numOfTabs = numOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new ConsultaFragment();
            case 1:
                return new TratamentoFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numOfTabs;
    }
}