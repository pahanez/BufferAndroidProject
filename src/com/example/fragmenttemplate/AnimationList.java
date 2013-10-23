package com.example.fragmenttemplate;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockListFragment;
import com.example.fragmenttemplate.utils.ViewFinder;

public class AnimationList extends SherlockListFragment implements OnItemClickListener {
	
	private ViewFinder mViewFinder;
	private ListView mListView;
	private ArrayAdapter<String> mArrayAdapter;
	private List<String> data;
	private int deletedPosition;
	
	public android.view.View onCreateView(LayoutInflater inflater,
			ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.item_list, null);
	};

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		mViewFinder = new ViewFinder(view);
		
		mListView = mViewFinder.find(android.R.id.list);
		data = new ArrayList<String>();
		for (int i = 0; i < 100; i++) {
			data.add("data " + i); 
		}
		
		
		mArrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, data );
			
		mListView.setAdapter(mArrayAdapter);
		
		
		
		 final Animation animation = AnimationUtils.loadAnimation(getActivity(),
		            R.anim.fdas);
		    animation.setAnimationListener(new AnimationListener() {
		        @Override
		        public void onAnimationStart(Animation animation) { 
		        }

		        @Override
		        public void onAnimationRepeat(Animation animation) {
		        }

		        @Override
		        public void onAnimationEnd(Animation animation) {
		            mArrayAdapter.remove(mArrayAdapter.getItem(deletedPosition));
		            mArrayAdapter.notifyDataSetChanged();
		        }
		    });
		    
		    mListView.setOnItemClickListener(new OnItemClickListener() {
		        @Override
		        public void onItemClick(AdapterView<?> parent, View view,
		                int position, long id) {
		        	deletedPosition = position;
		            view.startAnimation(animation);
		        }

		    });
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) { 
		
	}
	
	
	
}
