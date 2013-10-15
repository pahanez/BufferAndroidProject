package com.example.fragmenttemplate;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.actionbarsherlock.app.SherlockListFragment;
import com.example.fragmenttemplate.utils.ViewFinder;

public class AnimationList extends SherlockListFragment {
	
	private ViewFinder mViewFinder;

	public android.view.View onCreateView(LayoutInflater inflater,
			ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.item_list, null);
	};

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		
	}
}
