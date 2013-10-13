package com.example.fragmenttemplate.fragtabhost;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.ViewGroup;

import com.actionbarsherlock.app.SherlockFragment;
import com.example.fragmenttemplate.fragmentsviewpager.FragmentImpl;

public class DeviceFragmentPagerAdapter extends android.support.v4.app.FragmentPagerAdapter {

	public static final int DEVICE_ASSIGNED 			= 0;
	public static final int DEVICE_UNASSIGNED 			= 1;
	
	public DeviceFragmentPagerAdapter(FragmentManager fm) {
		super(fm);
	}

	/**
	 * Current fragment
	 * */
	private SherlockFragment selected;

	@Override
	public Fragment getItem(int arg0) {
		switch (arg0) {
		case DEVICE_ASSIGNED:
			return new FragmentImpl();
		case DEVICE_UNASSIGNED:
			return new FragmentImpl();
		}
		return null;
	}

	@Override
	public int getCount() {
		return 2; //Assigned & Unassigned
	}
	
	@Override
	public void setPrimaryItem(final ViewGroup container, final int position, final Object object) {
		super.setPrimaryItem(container, position, object);
		if (object instanceof SherlockFragment) {
			selected = (SherlockFragment) object;
		} else {
			selected = null;
		}

	}
	
	@Override
	public CharSequence getPageTitle(int position) {
		switch (position) {
		case DEVICE_ASSIGNED:
			return "DEVICE_ASSIGNED";
		case DEVICE_UNASSIGNED:
			return "DEVICE_UNASSIGNED";
		}
		return super.getPageTitle(position);
	}
	

}
