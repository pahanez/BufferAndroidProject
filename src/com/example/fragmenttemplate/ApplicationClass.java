package com.example.fragmenttemplate;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.util.Log;

public class ApplicationClass extends Application{
	
	private static final String TAG = ApplicationClass.class.getSimpleName();
	
    private static final int CORE_POOL_SIZE = 5;

    private static final ThreadFactory sThreadFactory = new ThreadFactory() {
        private final AtomicInteger mCount = new AtomicInteger(1);

        public Thread newThread(Runnable r) {
            return new Thread(r, "Thread #" + mCount.getAndIncrement());
        }
    };
    
    public ExecutorService getExecutor() {
        if (mExecutorService == null) {
            mExecutorService = Executors.newFixedThreadPool(CORE_POOL_SIZE, sThreadFactory);
        }
        return mExecutorService;
    }

    private ExecutorService mExecutorService;
    
    
    
    private static ApplicationClass sAppInstance;
    
    public ApplicationClass(){
    	super();
    	sAppInstance = this;
    	setupLogging();
    }
    
	private void setupLogging() {
//		Log.setLogLevel(android.util.Log.VERBOSE);
	}
	
	public static Context getContext() {
		return sAppInstance.getApplicationContext();
	}
	
	public static String getApplicationVersion() {
		final Context ctx = sAppInstance;
		String version = null;
		PackageManager pm = ctx.getPackageManager();
		try {
			PackageInfo info = pm.getPackageInfo(ctx.getPackageName(), 0);
			version = info.versionName;
		} catch (NameNotFoundException e) {
			Log.e(TAG, "can't get app version", e);
			version = "?";
		}
		return version;
	}
}
