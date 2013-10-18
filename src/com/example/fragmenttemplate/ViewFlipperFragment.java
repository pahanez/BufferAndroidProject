package com.example.fragmenttemplate;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.actionbarsherlock.app.SherlockFragment;
import com.example.fragmenttemplate.utils.ViewFinder;

public class ViewFlipperFragment extends SherlockFragment implements OnClickListener{
	
	private ViewFlipper mViewFlipper;
	private Button b1,b2;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.flipper_layout, null);
	}
	
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		ViewFinder finder = new ViewFinder(view);
		
		mViewFlipper = finder.find(R.id.flipper); 
		mViewFlipper.setDisplayedChild(mViewFlipper.getChildCount()-1);
		TextView tv1 = finder.find(R.id.first_flpp_tv);
		tv1.setText("First Child");
		TextView tv2 = finder.find(R.id.second_flpp_tv);
		tv2.setText("Second Child"); 
		b1 = finder.find(R.id.button1);
		b1.setOnClickListener(this);
		b2 = finder.find(R.id.button2);
		b2.setOnClickListener(this);
	}
	
	public void onClick(View view){ 
		
		switch (view.getId()) {
		case R.id.button1:
			mViewFlipper.setDisplayedChild(0);
			break;
		case R.id.button2:
			mViewFlipper.setDisplayedChild(1);
			break;

		default:
			break;
		}
	}
}
