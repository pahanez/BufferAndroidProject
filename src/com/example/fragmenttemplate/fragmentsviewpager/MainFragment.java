package com.example.fragmenttemplate.fragmentsviewpager;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.TouchDelegate;
import android.view.View;
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
		TouchDelegate td = new TouchDelegate(mControlView.getBackground().getBounds(), mViewPager);
		
//		mViewPager.addTouchables(new ArrayList<View>(Arrays.asList(new View[]{mControlView})));
		mControlView.setTouchDelegate(td);
		mViewPager.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				Log.e("p37td8","kodno");
				return false;
			}
		});
//		mViewPager.setTouchDelegate(td);
		
		
		super.onViewCreated(view, savedInstanceState);
	}
}
