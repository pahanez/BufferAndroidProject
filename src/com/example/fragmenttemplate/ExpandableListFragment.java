package com.example.fragmenttemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.SimpleExpandableListAdapter;

import com.actionbarsherlock.app.SherlockFragment;
import com.example.fragmenttemplate.utils.ViewFinder;

public class ExpandableListFragment extends SherlockFragment{
	
	private ExpandableListView mExpandableListView;
	
	
	
	
	  // названия компаний (групп)
	  String[] groups = new String[] {"HTC", "Samsung", "LG"};
	  
	  // названия телефонов (элементов)
	  String[] phonesHTC = new String[] {"Sensation", "Desire", "Wildfire", "Hero"};
	  String[] phonesSams = new String[] {"Galaxy S II", "Galaxy Nexus", "Wave"};
	  String[] phonesLG = new String[] {"Optimus", "Optimus Link", "Optimus Black", "Optimus One"};
	  
	  // коллекция для групп
	  ArrayList<Map<String, String>> groupData;
	  
	  // коллекция для элементов одной группы
	  ArrayList<Map<String, String>> childDataItem;

	  // общая коллекция для коллекций элементов
	  ArrayList<ArrayList<Map<String, String>>> childData;
	  // в итоге получится childData = ArrayList<childDataItem>
	  
	  // список аттрибутов группы или элемента
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
		
		
		
		// заполняем коллекцию групп из массива с названиями групп
        groupData = new ArrayList<Map<String, String>>();
        for (String group : groups) {
          // заполняем список аттрибутов для каждой группы
          m = new HashMap<String, String>();
            m.put("groupName", group); // имя компании
            groupData.add(m);  
        }
        
        // список аттрибутов групп для чтения
        String groupFrom[] = new String[] {"groupName"};
        // список ID view-элементов, в которые будет помещены аттрибуты групп
        int groupTo[] = new int[] {android.R.id.text1};
        

        // создаем коллекцию для коллекций элементов 
        childData = new ArrayList<ArrayList<Map<String, String>>>(); 
        
        // создаем коллекцию элементов для первой группы
        childDataItem = new ArrayList<Map<String, String>>();
        // заполняем список аттрибутов для каждого элемента
        for (String phone : phonesHTC) {
          m = new HashMap<String, String>();
            m.put("phoneName", phone); // название телефона
            childDataItem.add(m);  
        }
        // добавляем в коллекцию коллекций
        childData.add(childDataItem);

        // создаем коллекцию элементов для второй группы        
        childDataItem = new ArrayList<Map<String, String>>();
        for (String phone : phonesSams) {
          m = new HashMap<String, String>();
            m.put("phoneName", phone);
            childDataItem.add(m);  
        }
        childData.add(childDataItem);

        // создаем коллекцию элементов для третьей группы        
        childDataItem = new ArrayList<Map<String, String>>();
        for (String phone : phonesLG) {
          m = new HashMap<String, String>();
            m.put("phoneName", phone);
            childDataItem.add(m);  
        }
        childData.add(childDataItem);

        // список аттрибутов элементов для чтения
        String childFrom[] = new String[] {"phoneName"};
        // список ID view-элементов, в которые будет помещены аттрибуты элементов
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
