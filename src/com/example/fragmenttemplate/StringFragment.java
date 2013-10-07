package com.example.fragmenttemplate;

import java.util.Arrays;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.content.Loader;

import com.example.fragmenttemplate.loaders.CustomLoader;
import com.github.kevinsawicki.wishlist.SectionSingleTypeAdapter;
import com.github.kevinsawicki.wishlist.SingleTypeAdapter;

public class StringFragment extends AListFragment<String>{
	
	
	@Override
	public Loader<List<String>> onCreateLoader(int arg0, Bundle arg1) {
		return new CustomLoader<List<String>>(getActivity()){
			@Override
			public List<String> loadInBackground() {
				
				return Arrays.asList(new String[]{"data","dadslaj","lfsii","sklfd","fiuaysdfgus","kdsjklfa","klsdjfsid","sdkfjiosao"});
			}
		};
	}

	@Override
	protected SingleTypeAdapter<String> createAdapter(List<String> items) {
		SectionSingleTypeAdapter<String> adapter = new SectionSingleTypeAdapter<String>(getActivity(), android.R.layout.simple_list_item_1) {

			@Override
			protected int[] getChildViewIds() {
				
				return new int[]{android.R.id.text1};
			}

			@Override
			protected void update(int position, String item) {
				setText(0, item);
			}
		};
		
		
	return adapter;
	}

	@Override
	protected int getErrorMessage(Exception exception) {
		// TODO Auto-generated method stub
		return 0;
	}



}
