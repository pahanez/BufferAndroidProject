package com.example.fragmenttemplate;

import android.os.Bundle;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.Loader;
import android.util.Log;
import android.view.View;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.example.fragmenttemplate.fragtabhost.TabHostFragment;

public class MainActivity extends SherlockFragmentActivity implements LoaderCallbacks<String>{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		getSupportFragmentManager().beginTransaction().add(R.id.fragment_holder, new AnimationList()).commit();
	}
	
	@Override
	public Loader<String> onCreateLoader(int arg0, Bundle arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onLoadFinished(Loader<String> arg0, String arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onLoaderReset(Loader<String> arg0) {
		// TODO Auto-generated method stub
		
	}
	
	public void tempo(View view){ 
		getSupportFragmentManager().beginTransaction().replace(R.id.fragment_holder, new AnimationList()).commit();
	}


}
