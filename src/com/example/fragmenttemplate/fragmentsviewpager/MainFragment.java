package com.example.fragmenttemplate.fragmentsviewpager;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnDragListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;

import com.actionbarsherlock.app.SherlockFragment;
import com.example.fragmenttemplate.R;

public class MainFragment extends SherlockFragment{
	
	private ViewPager mViewPager;
	private View mControlView;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.vp_main, null);
	}
	
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		mControlView = view.findViewById(R.id.control_header);
		mViewPager = (ViewPager) view.findViewById(R.id.pager);
		mViewPager.setAdapter(new CustomAdapterPager(getActivity().getSupportFragmentManager()));
		mViewPager.setOnDragListener(new OnDragListener() {
			
			@Override
			public boolean onDrag(View v, DragEvent event) {
				// TODO Auto-generated method stub
				return false;
			}
		});
		
		mViewPager.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				return false;
			}
		});
		
		
		
		super.onViewCreated(view, savedInstanceState);
	}
}
