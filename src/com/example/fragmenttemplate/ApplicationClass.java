package com.example.fragmenttemplate;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

import android.app.Application;
import android.util.Log;

public class ApplicationClass extends Application{
	
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
}
