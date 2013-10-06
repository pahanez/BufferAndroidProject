package com.example.fragmenttemplate;

import java.util.List;

import com.github.kevinsawicki.wishlist.SingleTypeAdapter;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class CstmFrgImpl extends AListFragment<String> {
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		return inflater.inflate(R.layout.fragment_activity, null, false);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		getListAdapter().getWrappedAdapter().setItems(
				new String[] { "1", "2", "3" });
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
}
