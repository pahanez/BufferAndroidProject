package com.example.fragmenttemplate;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class CstmFrgImpl extends CustomFragmentItem{
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		return inflater.inflate(R.layout.fragment_activity, null, false);
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
//		setListAdapter(new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,new String[]{"1","2","3"}));
	}
}
