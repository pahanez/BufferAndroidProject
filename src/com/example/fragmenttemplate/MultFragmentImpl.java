package com.example.fragmenttemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import android.os.Bundle;
import android.support.v4.content.Loader;

import com.example.fragmenttemplate.entity.Device;
import com.example.fragmenttemplate.loaders.CustomLoader;
import com.github.kevinsawicki.wishlist.MultiTypeAdapter;

public class MultFragmentImpl extends AMultListFragment<Device>{
	private static final int ITEM 		= 0;
	private static final int DIVIDER 	= 1;

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
					devices.add(new Device(i, "Device #" + i,
							R.drawable.device_icon));
				}

				return devices;
			}
		};
	}

	@Override
	protected MultiTypeAdapter createAdapter(List<Device> items) {
		return new MultiTypeAdapter(getActivity()) {
			
			@Override
			protected void update(int position, Object item, int type) {
				switch (type) {
				case ITEM:
					imageView(0).setImageResource(R.drawable.device_icon);
					setText(1, ((Device)item).name);
					break;
				case DIVIDER:
					setText(0, ((Device)item).name);
					break;
				}		
			}
			
			@Override
			protected int[] getChildViewIds(int type) {
				switch (type) {
				case ITEM:
					return new int[]{R.id.device_iv , R.id.device_tv};
				case DIVIDER:
					return new int[]{R.id.separator_tv };
				}
				return null;
			}
			
			@Override
			protected int getChildLayoutId(int type) {
				switch (type) {
				case ITEM:
					return R.layout.device_row;
				case DIVIDER:
					return R.layout.separator;
				}
			return 0;
		}
			
			@Override
			public int getViewTypeCount() {
				return 2;
			}
		};
		
		
	}

	@Override
	protected int getErrorMessage(Exception exception) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public void onLoadFinished(Loader<List<Device>> loader, List<Device> items) {
		super.onLoadFinished(loader, items);


		if (!isUsable())
			return;

		getSherlockActivity().setSupportProgressBarIndeterminateVisibility(
				false);
		Exception exception = getException(loader);
		if (exception != null) {
			showError(exception, getErrorMessage(exception));
			showList();
			return;
		}

		this.items = items;
		getListAdapter().clear();
		for(Device item:items){
			
		}
		
		for (int i = 0; i < items.size(); i++) {
			if(i % 4 == 0) getListAdapter().addItem(DIVIDER, new Device(0, "Sepoku", 0));
			getListAdapter().addItem(ITEM, items.get(i));
		}
		showList();

	
	}

}
