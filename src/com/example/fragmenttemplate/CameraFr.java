package com.example.fragmenttemplate;

import java.io.IOException;

import android.hardware.Camera;
import android.hardware.Camera.PictureCallback;
import android.hardware.Camera.ShutterCallback;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

import com.actionbarsherlock.app.SherlockFragment;

public class CameraFr extends SherlockFragment implements OnClickListener{
	private Button mBut;
	private Camera mCamera;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.button_layout, null);
	}
	
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		mBut = (Button)view.findViewById(R.id.button_start);
		mBut.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.button_start:
			mCamera = Camera.open();
			SurfaceView view = new SurfaceView(getActivity());
			try {
				mCamera.setPreviewDisplay(view.getHolder());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			mCamera.startPreview();
				mCamera.takePicture(new ShutterCallback() {
					
					@Override
					public void onShutter() {
						
					};
				}, new PictureCallback() {
					
					@Override
					public void onPictureTaken(byte[] data, Camera camera) {
						Log.e("p37td8"," " + data.length);
					}
				}, new PictureCallback() {
					
					@Override
					public void onPictureTaken(byte[] data, Camera camera) {
						Log.e("p37td8"," " + data.length);
					}
				});
				mCamera.release();
			break;

		default:
			break;
		}
	}
}
