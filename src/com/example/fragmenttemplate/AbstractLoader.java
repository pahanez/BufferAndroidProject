package com.example.fragmenttemplate;

import android.content.AsyncTaskLoader;
import android.content.Context;

public class AbstractLoader extends AsyncTaskLoader<String>{

	public AbstractLoader(Context context) {
		super(context);
	}

	 String mResult;

	    /**
	     * This is where the bulk of our work is done.  This function is
	     * called in a background thread and should generate a new set of
	     * data to be published by the loader.
	     */
	    @Override public String loadInBackground() {
	        // Retrieve all known applications.
	        if (mResult == null) {
	            mResult = "kido";
	        }


	        // Done!
	        return mResult;
	    }

	    /**
	     * Called when there is new data to deliver to the client.  The
	     * super class will take care of delivering it; the implementation
	     * here just adds a little more logic.
	     */
	    @Override public void deliverResult(String apps) {
	        if (isReset()) {
	            // An async query came in while the loader is stopped.  We
	            // don't need the result.
	            if (apps != null) {
	                onReleaseResources(apps);
	            }
	        }
	        mResult = apps;

	        if (isStarted()) {
	            // If the Loader is currently started, we can immediately
	            // deliver its results.
	            super.deliverResult(apps);
	        }

	    }

	    /**
	     * Handles a request to start the Loader.
	     */
	    @Override protected void onStartLoading() {
	        if (mResult != null) {
	            // If we currently have a result available, deliver it
	            // immediately.
	            deliverResult(mResult);
	        }


	        if (takeContentChanged() || mResult == null) {
	            // If the data has changed since the last time it was loaded
	            // or is not currently available, start a load.
	            forceLoad();
	        }
	    }

	    /**
	     * Handles a request to stop the Loader.
	     */
	    @Override protected void onStopLoading() {
	        // Attempt to cancel the current load task if possible.
	        cancelLoad();
	    }

	    /**
	     * Handles a request to cancel a load.
	     */
	    @Override public void onCanceled(String apps) {
	        super.onCanceled(apps);

	        // At this point we can release the resources associated with 'apps'
	        // if needed.
	        onReleaseResources(apps);
	    }

	    /**
	     * Handles a request to completely reset the Loader.
	     */
	    @Override protected void onReset() {
	        super.onReset();

	        // Ensure the loader is stopped
	        onStopLoading();

	        // At this point we can release the resources associated with 'apps'
	        // if needed.
	        if (mResult != null) {
	            onReleaseResources(mResult);
	            mResult = null;
	        }
	    }

	    /**
	     * Helper function to take care of releasing resources associated
	     * with an actively loaded data set.
	     */
	    protected void onReleaseResources(String apps) {
	        // For a simple List<> there is nothing to do.  For something
	        // like a Cursor, we would close it here.
	    }

}
