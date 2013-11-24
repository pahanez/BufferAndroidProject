package com.example.fragmenttemplate;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

import com.actionbarsherlock.app.SherlockFragment;

public class HackerTyperFragment extends SherlockFragment implements OnKeyListener {
	
	private EditText mHackerView;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.hacker_typer_layout, null);
	}
	
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		mHackerView = (EditText) view.findViewById(R.id.hacker_typer_et);
		
		/*mHackerView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				InputMethodManager inputMethodManager=(InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
				inputMethodManager.toggleSoftInputFromWindow(mHackerView.getApplicationWindowToken(), InputMethodManager.SHOW_FORCED, 0);
			}
		});*/
		
		mHackerView.setOnEditorActionListener(new OnEditorActionListener() {
			
			@Override
			public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
				Log.e("p37td8" , "onEditorAction");
				
				return false;
			}
		});
		
		mHackerView.setOnKeyListener(this);
		mHackerView.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				Log.e("p37td8" , "onTextChanged");
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				//EMPTY
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				s.clear();
				mHackerView.add
				mHackerView.setText(" 111 ");
				
			}
		});
	}

	@Override
	public boolean onKey(View v, int keyCode, KeyEvent event) {
		Log.e("p37td8" , "onKey" + keyCode);
		return false;
	}

}
