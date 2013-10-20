package com.example.fragmenttemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockFragment;
import com.example.fragmenttemplate.utils.ViewFinder;
import com.github.kevinsawicki.wishlist.ViewUtils;

public class ExpandableListFragment extends SherlockFragment{
	
	private ExpandableListView mExpandableListView;
	private static final String GROUP_ID = "action_id";
	
	
	
	  String[] actions = new String[] {"Door Back", "Ligth Back"};
	  
	  String[] doorBackAction = new String[] {"Sensation", "Desire", "Wildfire", "Hero"};
	  String[] phonesSams = new String[] {"Galaxy S II", "Galaxy Nexus", "Wave"};
	  
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
        int groupTo[] = new int[] {R.id.action_group_text};
        

        childData = new ArrayList<ArrayList<Map<String, String>>>(); 
        
        childDataItem = new ArrayList<Map<String, String>>();
        for (String action : doorBackAction) {
          m = new HashMap<String, String>();
            m.put(GROUP_ID, action); 
            childDataItem.add(m);  
        }
        addFooter(childDataItem);
        childData.add(childDataItem);

        childDataItem = new ArrayList<Map<String, String>>();
        for (String phone : phonesSams) {
          m = new HashMap<String, String>();
            m.put(GROUP_ID, phone);
            childDataItem.add(m);  
        }
        addFooter(childDataItem);
        childData.add(childDataItem);


        String childFrom[] = new String[] {GROUP_ID};
        int childTo[] = new int[] {R.id.action_item_text};
        
        final CustomExpandableListAdapter adapter = new CustomExpandableListAdapter(
            getActivity(),
            groupData,
            R.layout.action_group_view,
            groupFrom,
            groupTo,
            childData,
            R.layout.action_item_view,
            childFrom,
            childTo);
        
        
        mExpandableListView.setAdapter(adapter);
        for(int i = 0; i<adapter.getGroupCount();i++)
        	mExpandableListView.expandGroup(i); //open all groups when started
        mExpandableListView.setOnChildClickListener(new OnChildClickListener() {
			
			@Override
			public boolean onChildClick(ExpandableListView parent, View v,
					int groupPosition, int childPosition, long id) {
				if(( childData.get(groupPosition).size() - 1 ) == childPosition){
					Log.e("p37td8","add_item");
					 m = new HashMap<String, String>();
					 m.put(GROUP_ID, "new item");
					childData.get(groupPosition).add(m);
					adapter.notifyDataSetChanged();
				}
				return false;
			}
		});
        
		
	}
	
	private void addFooter(ArrayList<Map<String, String>> child) {
		 m = new HashMap<String, String>();
         m.put(GROUP_ID, "(+) Add item");
         childDataItem.add(m);  
	}

	private class CustomExpandableListAdapter extends BaseExpandableListAdapter{
	    private List<? extends Map<String, ?>> mGroupData;
	    private int mExpandedGroupLayout;
	    private int mCollapsedGroupLayout;
	    private String[] mGroupFrom;
	    private int[] mGroupTo;
	    
	    private List<? extends List<? extends Map<String, ?>>> mChildData;
	    private int mChildLayout;
	    private int mLastChildLayout;
	    private String[] mChildFrom;
	    private int[] mChildTo;
	    
	    private LayoutInflater mInflater;
	    
	    public CustomExpandableListAdapter(Context context,
	            List<? extends Map<String, ?>> groupData, int groupLayout,
	            String[] groupFrom, int[] groupTo,
	            List<? extends List<? extends Map<String, ?>>> childData,
	            int childLayout, String[] childFrom, int[] childTo) {
	        this(context, groupData, groupLayout, groupLayout, groupFrom, groupTo, childData,
	                childLayout, childLayout, childFrom, childTo);
	    }

	    public CustomExpandableListAdapter(Context context,
	            List<? extends Map<String, ?>> groupData, int expandedGroupLayout,
	            int collapsedGroupLayout, String[] groupFrom, int[] groupTo,
	            List<? extends List<? extends Map<String, ?>>> childData,
	            int childLayout, String[] childFrom, int[] childTo) {
	        this(context, groupData, expandedGroupLayout, collapsedGroupLayout,
	                groupFrom, groupTo, childData, childLayout, childLayout,
	                childFrom, childTo);
	    }

	    public CustomExpandableListAdapter(Context context,
	            List<? extends Map<String, ?>> groupData, int expandedGroupLayout,
	            int collapsedGroupLayout, String[] groupFrom, int[] groupTo,
	            List<? extends List<? extends Map<String, ?>>> childData,
	            int childLayout, int lastChildLayout, String[] childFrom,
	            int[] childTo) {
	        mGroupData = groupData;
	        mExpandedGroupLayout = expandedGroupLayout;
	        mCollapsedGroupLayout = collapsedGroupLayout;
	        mGroupFrom = groupFrom;
	        mGroupTo = groupTo;
	        
	        mChildData = childData;
	        mChildLayout = childLayout;
	        mLastChildLayout = lastChildLayout;
	        mChildFrom = childFrom;
	        mChildTo = childTo;
	        
	        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	    }
	    
	    public Object getChild(int groupPosition, int childPosition) {
	        return mChildData.get(groupPosition).get(childPosition);
	    }

	    public long getChildId(int groupPosition, int childPosition) {
	        return childPosition;
	    }

	    public View getChildView(int groupPosition, int childPosition, boolean isLastChild,
	            View convertView, ViewGroup parent) {
	        View v;
	        if (convertView == null) {
	            v = newChildView(isLastChild, parent);
	        } else {
	            v = convertView;
	        }
	        bindChildView(v, mChildData.get(groupPosition).get(childPosition), mChildFrom, mChildTo,groupPosition);
	        return v;
	    }

	    /**
	     * Instantiates a new View for a child.
	     * @param isLastChild Whether the child is the last child within its group.
	     * @param parent The eventual parent of this new View.
	     * @return A new child View
	     */
	    public View newChildView(boolean isLastChild, ViewGroup parent) {
	        return mInflater.inflate((isLastChild) ? mLastChildLayout : mChildLayout, parent, false);
	    }
	    
	    
	    private void bindChildView(View view, Map<String, ?> data, String[] from, int[] to,int groupPosition){
	    	 int len = to.length;
	    	 for (int i = 0; i < len; i++) {
		            TextView v = (TextView)view.findViewById(to[i]);
		            if (v != null) {
		                v.setText((String)data.get(from[i]));
		            }
		        }
	    	 Button button = ((Button)view.findViewById(R.id.action_item_button));
	    	 button.setText("b" + mChildData.get(groupPosition).indexOf(data));
	    	 
	    }
	    
	    private void bindGroupView(View view,final Map<String, ?> data,final String[] from, int[] to){
	    	 int len = to.length;
	    	 for (int i = 0; i < len; i++) {
		            TextView v = (TextView)view.findViewById(to[i]);
		            if (v != null) {
		                v.setText((String)data.get(from[i]));
		            }
		        }
	    	 ((Button)view.findViewById(R.id.action_group_button)).setOnClickListener(new OnClickListener() {
	    		 
	    		 @Override
	    		 public void onClick(View v) {
	    			 Log.e("p37td8","actiongroup_pressed " + data.get(from[0]));
	    		 }
	    	 });
	    }

	    public int getChildrenCount(int groupPosition) {
	        return mChildData.get(groupPosition).size();
	    }

	    public Object getGroup(int groupPosition) {
	        return mGroupData.get(groupPosition);
	    }

	    public int getGroupCount() {
	        return mGroupData.size();
	    }

	    public long getGroupId(int groupPosition) {
	        return groupPosition;
	    }

	    public View getGroupView(int groupPosition, boolean isExpanded, View convertView,
	            ViewGroup parent) {
	        View v;
	        if (convertView == null) {
	            v = newGroupView(isExpanded, parent);
	        } else {
	            v = convertView;
	        }
	        bindGroupView(v, mGroupData.get(groupPosition), mGroupFrom, mGroupTo);
	        return v;
	    }

	    /**
	     * Instantiates a new View for a group.
	     * @param isExpanded Whether the group is currently expanded.
	     * @param parent The eventual parent of this new View.
	     * @return A new group View
	     */
	    public View newGroupView(boolean isExpanded, ViewGroup parent) {
	        return mInflater.inflate((isExpanded) ? mExpandedGroupLayout : mCollapsedGroupLayout,
	                parent, false);
	    }

	    public boolean isChildSelectable(int groupPosition, int childPosition) {
	        return true;
	    }

	    public boolean hasStableIds() {
	        return true;
	    }
	    
}
	
}
