package com.example.fragmenttemplate;

import android.os.Bundle;
import android.os.ResultReceiver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

import com.actionbarsherlock.app.SherlockFragment;
import com.example.fragmenttemplate.utils.ViewFinder;

public class SenderFragment extends SherlockFragment implements OnClickListener{
	
	private Button mSendButton,mPopButton;
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.double_button_layout, null);
	}
	
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		ViewFinder viewFinder = new ViewFinder(view);
		mSendButton = viewFinder.find(R.id.send_info);
		mPopButton = viewFinder.find(R.id.pop_fragment);
		mSendButton.setOnClickListener(this);
		mPopButton.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.send_info:
			ResultReceiver receiver = getArguments().getParcelable("receiver");
			Bundle bundle = new Bundle();
			
			receiver.send(392, bundle);
			break;
		case R.id.pop_fragment:
				((MainActivity)getActivity()).popFragment();
			break;

		default:
			break;
		}
	}
}
