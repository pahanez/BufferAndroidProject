package com.example.fragmenttemplate;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

import com.actionbarsherlock.app.SherlockFragment;
import com.example.fragmenttemplate.utils.DetachableResultReceiver;
import com.example.fragmenttemplate.utils.DetachableResultReceiver.Receiver;
import com.example.fragmenttemplate.utils.ViewFinder;

public class ReceiverFragment extends SherlockFragment implements OnClickListener, Receiver{
	
	private Button mStartAnotherFragment;
	private DetachableResultReceiver mReceiver;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.button_layout, null);
	}
	
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		ViewFinder finder = new ViewFinder(view);
		mReceiver = new DetachableResultReceiver(new Handler());
		mReceiver.setReceiver(this);
		
		mStartAnotherFragment = finder.find(R.id.button_start);
		mStartAnotherFragment.setOnClickListener(this);
		
		
	}
	
	

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.button_start:
			Fragment senderFragment = new SenderFragment();
			Bundle bundle = new Bundle();
			bundle.putParcelable("receiver", mReceiver);
			senderFragment.setArguments(bundle);
				((MainActivity)getActivity()).initFragment(senderFragment, "sender_fragment");
			break;

		default:
			break;
		}
	}

	@Override
	public void onReceiveResult(int resultCode, Bundle resultData) {
		Log.e("p37td8","kino " + resultCode + " , " + resultData);
	}
}
