package com.example.fragmenttemplate;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.actionbarsherlock.app.SherlockListFragment;
import com.example.fragmenttemplate.utils.ViewFinder;

public class AnimationList extends SherlockListFragment {
	
	private ViewFinder mViewFinder;
	private ListView mListView;
	
	public android.view.View onCreateView(LayoutInflater inflater,
			ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.item_list, null);
	};

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		mViewFinder = new ViewFinder(view);
		
		mListView = mViewFinder.find(android.R.id.list);
		String[] data = new String[100];
		for (int i = 0; i < data.length; i++) {
			data[i] = "data " + i;
		}
		
		mListView.setAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, data ));
	}
}
