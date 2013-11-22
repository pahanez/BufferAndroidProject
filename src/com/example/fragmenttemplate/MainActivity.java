package com.example.fragmenttemplate;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.Loader;

import com.actionbarsherlock.app.SherlockFragmentActivity;

public class MainActivity extends SherlockFragmentActivity implements LoaderCallbacks<String>{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		getSupportFragmentManager().beginTransaction().add(R.id.fragment_holder, new HackerTyperFragment()).commit();
	}
	
	public void initFragment(Fragment fragment, String id){
		FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
//		ft.add(fragment, id);
		ft.replace(R.id.fragment_holder, fragment);
		ft.addToBackStack(id); 
		ft.commit();
	}
	
	public void popFragment(){
		getSupportFragmentManager().popBackStack();
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
	

//31.10.2013 Ubuntu One App investigation
//01.11.2013 git@github.com:casidiablo/groundy.git
//13.11.2013 Ubuntu One GaleryActivity
//14.11.2013 Ubuntu One Login Process
//19.11.2013 Ubuntu One , http://graphics-geek.blogspot.com/2013/09/lazy-lists.html
}
