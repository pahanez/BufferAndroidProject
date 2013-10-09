package com.example.fragmenttemplate;

import android.app.LoaderManager.LoaderCallbacks;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;

public class MainActivity extends FragmentActivity implements LoaderCallbacks<String>{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
//		getSupportLoaderManager().initLoader(1, null, this);
		getLoaderManager().initLoader(1, null, this);
//		getSupportFragmentManager().beginTransaction().add(R.id.fragment_holder, new MultFragmentImpl()).commit();
	}

	@Override
	public android.content.Loader<String> onCreateLoader(int id, Bundle args) {
		// TODO Auto-generated method stub
		Log.e("p37td8","onCreateLoader");
		return new AbstractLoader(this);
	}

	@Override
	public void onLoadFinished(android.content.Loader<String> arg0, String arg1) {
		Log.e("p37td8","onLoadFinished " + arg1);
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onLoaderReset(android.content.Loader<String> arg0) {
		// TODO Auto-generated method stub
		
	}
	
	public void click(View v){
		Log.e("p37td8","clicked");
		getLoaderManager().restartLoader(1, null, this);
	}


}
