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
		getSupportFragmentManager().beginTransaction().add(R.id.fragment_holder, new CameraFr()).commit();
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
	


}
