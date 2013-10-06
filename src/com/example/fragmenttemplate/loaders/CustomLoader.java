package com.example.fragmenttemplate.loaders;

import java.util.List;

import android.content.Context;

import com.github.kevinsawicki.wishlist.AsyncLoader;

public class CustomLoader<D> extends AsyncLoader<D>{

	private static final String TAG = "CustomLoader";
	
	public CustomLoader(Context context) {
		super(context);
		
	}

	@Override
	public D loadInBackground() {
		
		return null;
	}


}
