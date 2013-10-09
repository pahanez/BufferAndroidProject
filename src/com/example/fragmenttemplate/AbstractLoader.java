package com.example.fragmenttemplate;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

public class AbstractLoader extends AsyncTaskLoader<String>{

	public AbstractLoader(Context context) {
		super(context);
	}

	 String mResult;

	    @Override public String loadInBackground() {
	        if (mResult == null) {
	            mResult = "Test_String";
	        }


	        return mResult;
	    }

	    @Override public void deliverResult(String apps) {
	        if (isReset()) {
	            if (apps != null) {
	                onReleaseResources(apps);
	            }
	        }
	        mResult = apps;

	        if (isStarted()) {
	            super.deliverResult(apps);
	        }

	    }

	    @Override protected void onStartLoading() {
	    	Log.e("!LOG!", "onStartLoading " + mResult);
	        if (mResult != null) {
	            deliverResult(mResult);
	        }


	        if (takeContentChanged() || mResult == null) {
	            forceLoad();
	        }
	    }

	    @Override protected void onStopLoading() {
	        cancelLoad();
	    }

	    @Override public void onCanceled(String apps) {
	        super.onCanceled(apps);

	        onReleaseResources(apps);
	    }

	    @Override protected void onReset() {
	        super.onReset();

	        onStopLoading();

	        if (mResult != null) {
	            onReleaseResources(mResult);
	            mResult = null;
	        }
	    }

	    protected void onReleaseResources(String apps) {
	    }

}
