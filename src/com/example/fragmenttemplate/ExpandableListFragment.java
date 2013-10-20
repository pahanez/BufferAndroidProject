package com.example.fragmenttemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.SimpleExpandableListAdapter;

import com.actionbarsherlock.app.SherlockFragment;
import com.example.fragmenttemplate.utils.ViewFinder;

public class ExpandableListFragment extends SherlockFragment{
	
	private ExpandableListView mExpandableListView;
	private static final String GROUP_ID = "action_id";
	
	
	
	  String[] actions = new String[] {"Door Back", "Ligth Back"};
	  
	  String[] doorBackAction = new String[] {"Sensation", "Desire", "Wildfire", "Hero"};
	  String[] phonesSams = new String[] {"Galaxy S II", "Galaxy Nexus", "Wave"};
	  String[] phonesLG = new String[] {"Optimus", "Optimus Link", "Optimus Black", "Optimus One"};
	  
	  ArrayList<Map<String, String>> groupData;
	  
	  ArrayList<Map<String, String>> childDataItem;

	  ArrayList<ArrayList<Map<String, String>>> childData;
	  
	  Map<String, String> m;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.edit_frag, null);
	}
	
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		ViewFinder mFinder = new ViewFinder(view);
		mExpandableListView = mFinder.find(R.id.ex_panda);
		
		
		
        groupData = new ArrayList<Map<String, String>>();
        for (String action : actions) {
          m = new HashMap<String, String>();
            m.put(GROUP_ID, action);
            groupData.add(m);  
        }
        
        String groupFrom[] = new String[] {GROUP_ID};
        int groupTo[] = new int[] {android.R.id.text1};
        

        childData = new ArrayList<ArrayList<Map<String, String>>>(); 
        
        childDataItem = new ArrayList<Map<String, String>>();
        for (String action : doorBackAction) {
          m = new HashMap<String, String>();
            m.put(GROUP_ID, action); 
            childDataItem.add(m);  
        }
        childData.add(childDataItem);

        childDataItem = new ArrayList<Map<String, String>>();
        for (String phone : phonesSams) {
          m = new HashMap<String, String>();
            m.put(GROUP_ID, phone);
            childDataItem.add(m);  
        }
        childData.add(childDataItem);


        String childFrom[] = new String[] {GROUP_ID};
        int childTo[] = new int[] {android.R.id.text1};
        
        SimpleExpandableListAdapter adapter = new SimpleExpandableListAdapter(
            getActivity(),
            groupData,
            android.R.layout.simple_expandable_list_item_1,
            groupFrom,
            groupTo,
            childData,
            android.R.layout.simple_list_item_1,
            childFrom,
            childTo);
        
        
        mExpandableListView.setAdapter(adapter);
        
		
	}
	
}
