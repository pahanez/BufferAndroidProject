package com.example.bufferandroidproject.abs;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import android.os.Bundle;
import android.support.v4.content.Loader;

import com.github.kevinsawicki.wishlist.AsyncLoader;
import com.github.kevinsawicki.wishlist.SingleTypeAdapter;

public class CustomPagerFragment extends ItemListFragment<String> {

	@Override
	public Loader<List<String>> onCreateLoader(int arg0, Bundle arg1) {
		return new AsyncLoader<List<String>>(getActivity()) {
			@Override
			public List<String> loadInBackground() {
				return Arrays.asList("kimono_1","kimono_2","kimono_3");
			}
		};
	}

	@Override
	protected int getErrorMessage(Exception exception) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	protected SingleTypeAdapter<String> createAdapter(List<String> items) {
		// TODO Auto-generated method stub
		return new CustomStringAdapter(getActivity(),
				(String[]) items.toArray());
	}

}
