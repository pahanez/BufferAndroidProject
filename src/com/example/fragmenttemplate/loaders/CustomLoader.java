package com.example.fragmenttemplate.loaders;

import android.content.Context;

import com.github.kevinsawicki.wishlist.AsyncLoader;

public class CustomLoader<D> extends AsyncLoader<D>{

	public CustomLoader(Context context) {
		super(context);
		
	}

	@Override
	public D loadInBackground() {
		return null;
	}


}
