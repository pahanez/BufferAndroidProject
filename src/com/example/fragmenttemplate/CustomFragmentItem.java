package com.example.fragmenttemplate;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;

import com.actionbarsherlock.app.SherlockListFragment;
import com.example.fragmenttemplate.utils.ViewFinder;

public class CustomFragmentItem extends SherlockListFragment{
	
	protected ViewFinder mViewFinder;
	
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		
		mViewFinder = new ViewFinder(view);
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		setListAdapter(new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,new String[]{"1","2","3"}));
	}
	
	 /**
     * Is this fragment usable from the UI-thread
     *
     * @return true if usable, false otherwise
     */
    protected boolean isUsable() {
        return getActivity() != null;
    }
	
	
}
