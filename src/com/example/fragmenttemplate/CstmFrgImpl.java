package com.example.fragmenttemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import android.os.Bundle;
import android.support.v4.content.Loader;
import android.view.View;

import com.example.fragmenttemplate.loaders.CustomLoader;
import com.github.kevinsawicki.wishlist.SingleTypeAdapter;

public class CstmFrgImpl extends AListFragment<String> {

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		setEmptyText("No results found");
		
		/*new Handler(getActivity().getMainLooper()){
			public void handleMessage(android.os.Message msg) {
				getActivity().runOnUiThread(new Runnable() {
					
					@Override
					public void run() {
						refreshWithProgress();
					}
				});
			};
		}.sendEmptyMessageDelayed(1, 5000);*/
	}

	@Override
	protected SingleTypeAdapter<String> createAdapter(List<String> items) {
		return new SingleTypeAdapter<String>(getActivity(),
				android.R.layout.simple_list_item_1) {

			@Override
			protected int[] getChildViewIds() {
				return new int[] { android.R.id.text1 };
			}

			@Override
			protected void update(int position, String item) {
				setText(0, item);
			}
		};
	}

	@Override
	protected int getErrorMessage(Exception exception) {
		return 0;
	}
	
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
	}
	@Override
	public Loader<List<String>> onCreateLoader(int arg0, Bundle arg1) {

		return new CustomLoader<List<String>>(getActivity()){
			@Override
			public List<String> loadInBackground() {
				try {
					TimeUnit.SECONDS.sleep(3);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				return new ArrayList<String>(Arrays.asList( new String[] { "data____1", "data____2", "data____3" }));
			}
		};
	}
	
}
