package com.example.fragmenttemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

import com.actionbarsherlock.app.SherlockFragment;

public class HackerTyperFragment extends SherlockFragment {
	
	private EditText mHackerViewHidden;
	private TextView mHackerView;
	private BufferedReader mReader;
	private char [] chars = new char[3];
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.hacker_typer_layout, null);
	}
	
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		mHackerView = (TextView) view.findViewById(R.id.hacker_typer_tv);
		mHackerViewHidden = (EditText) view.findViewById(R.id.hacker_et);
		
		mHackerView.setOnTouchListener(new OnTouchListener() {
			 
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				mHackerViewHidden.dispatchTouchEvent(event);
				return false;
			}
		});
		
		mHackerViewHidden.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if(event.getAction() == MotionEvent.ACTION_DOWN){
					InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
//					imm.showSoftInput(v, InputMethodManager.SHOW_IMPLICIT);
					imm.toggleSoftInputFromWindow(v.getApplicationWindowToken(), InputMethodManager.SHOW_FORCED, 0); //TODO
				}
				return false;
			}
		});

		mHackerViewHidden.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				Log.e("p37td8" , "onTextChanged");
				mHackerView.setText(mHackerView.getText() + getNextData());
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				//EMPTY
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				//EMPTY
			}
		});
		try {
			
			mReader = new BufferedReader(new InputStreamReader(getActivity().getAssets().open("kexec.c")));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	String getNextData(){
		try {
			mReader.read(chars); 
			return new String(chars	, 0	, chars.length);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
}
