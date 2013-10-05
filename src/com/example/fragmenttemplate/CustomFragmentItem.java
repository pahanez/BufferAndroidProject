package com.example.fragmenttemplate;

import android.os.Bundle;
import android.view.View;

import com.actionbarsherlock.app.SherlockFragment;
import com.example.fragmenttemplate.utils.ViewFinder;

public class CustomFragmentItem extends SherlockFragment{
	
	protected ViewFinder mViewFinder;
	
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		
		mViewFinder = new ViewFinder(view);
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
