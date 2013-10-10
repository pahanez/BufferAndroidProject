package com.example.fragmenttemplate.fragmentsviewpager;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.actionbarsherlock.app.SherlockFragment;
import com.example.fragmenttemplate.R;

public class MainFragment extends SherlockFragment{
	
	private ViewPager mViewPager;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.vp_main, null);
	}
	
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		mViewPager = (ViewPager) view.findViewById(R.id.pager);
		mViewPager.setAdapter(new CustomAdapterPager(getActivity().getSupportFragmentManager()));
		super.onViewCreated(view, savedInstanceState);
	}
}
