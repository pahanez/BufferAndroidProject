package com.example.fragmenttemplate.fragmentsviewpager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class CustomAdapterPager extends FragmentPagerAdapter{

	public CustomAdapterPager(FragmentManager fm) {
		super(fm);
	}

	@Override
	public Fragment getItem(int arg0) {
		return new FragmentImpl();
	}

	@Override
	public int getCount() {
		return 3;
	}
	
}
