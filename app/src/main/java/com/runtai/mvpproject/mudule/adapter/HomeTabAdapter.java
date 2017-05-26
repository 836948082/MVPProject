package com.runtai.mvpproject.mudule.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;

import com.runtai.mvpproject.comment.base.BaseFragment;
import com.runtai.mvpproject.mudule.fragment.FenLeiFragment;
import com.runtai.mvpproject.mudule.fragment.HomeFragment;
import com.runtai.mvpproject.mudule.fragment.PersonalFragment;
import com.runtai.mvpproject.mudule.fragment.ShopCarFragment;

import java.util.ArrayList;
import java.util.List;

public class HomeTabAdapter extends FragmentPagerAdapter {

    private List<BaseFragment> list;

    public HomeTabAdapter(FragmentManager fm) {
        super(fm);
        // TODO Auto-generated constructor stub
        list = new ArrayList<BaseFragment>();
    }

    public void clearHomeTab() {
        list.clear();
    }

    public void addHomeTab() {
        list.add(new HomeFragment());
        list.add(new FenLeiFragment());
        list.add(new ShopCarFragment());
        list.add(new PersonalFragment());
    }

    @Override
    public Fragment getItem(int location) {
        // TODO Auto-generated method stub
        return list.get(location);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return list.size();
    }

    public List<BaseFragment> getList() {
        return list;
    }

    public void setList(List<BaseFragment> list) {
        this.list = list;
    }

    @Override
    public int getItemPosition(Object object) {
        return PagerAdapter.POSITION_NONE;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return super.getPageTitle(position);
    }
}
