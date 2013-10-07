package com.example.fragmenttemplate;

import android.app.Activity;

import com.github.kevinsawicki.wishlist.MultiTypeAdapter;

public class MultAdapter extends MultiTypeAdapter{
	private static final int ITEM 		= 0;
	private static final int DIVIDER 	= 1;
	
	
	public MultAdapter(Activity activity) {
		super(activity);
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
	protected void update(int position, Object item, int type) {
		switch (type) {
		case ITEM:
			imageView(0).setImageResource(R.drawable.device_icon);
			setText(1, "kono");
			break;
		case DIVIDER:
			setText(0, "devider");
			break;
		}		
	}
	
	@Override
	public int getViewTypeCount() {
		return 2;
	}
	
}
