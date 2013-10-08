package com.example.fragmenttemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import android.os.Bundle;
import android.support.v4.content.Loader;
import android.view.View;

import com.example.fragmenttemplate.entity.Device;
import com.example.fragmenttemplate.loaders.CustomLoader;
import com.github.kevinsawicki.wishlist.SingleTypeAdapter;

public class CstmFrgImpl extends AListFragment<Device> {

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		setEmptyText("No results found");

		/*
		 * new Handler(getActivity().getMainLooper()){ public void
		 * handleMessage(android.os.Message msg) {
		 * getActivity().runOnUiThread(new Runnable() {
		 * 
		 * @Override public void run() { refreshWithProgress(); } }); };
		 * }.sendEmptyMessageDelayed(1, 5000);
		 */
	}

	@Override
	protected SingleTypeAdapter<Device> createAdapter(List<Device> items) {
		return new SingleTypeAdapter<Device>(getActivity(), R.layout.device_row) {

			@Override
			protected int[] getChildViewIds() {
				return new int[] { R.id.device_iv, R.id.device_tv };
			}

			@Override
			protected void update(int position, Device item) {
				imageView(0).setImageResource(item.img);
				setText(1, item.name);
			}
		};
	}

	@Override
	protected int getErrorMessage(Exception exception) {
		return R.string.error_custom_load;
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
	}

	@Override  
	public Loader<List<Device>> onCreateLoader(int arg0, Bundle arg1) {

		return new CustomLoader<List<Device>>(getActivity()) {
			@Override
			public List<Device> loadInBackground() {
				try {
					TimeUnit.SECONDS.sleep(3);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				List<Device> devices = new ArrayList<Device>();
				for (int i = 0; i < 100; i++) {
					devices.add(new Device(i, "Item #" + i,
							R.drawable.device_icon));
				}

				return devices;
			}
		};
	}

}
