package com.example.fragmenttemplate;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.actionbarsherlock.app.SherlockFragment;
import com.github.kevinsawicki.wishlist.ViewFinder;

public class TestFragment extends SherlockFragment{
	
	private ViewFinder mFinder;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.item_list	, null );
	}
	
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		mFinder = new ViewFinder(view);
		
		
	}
}
