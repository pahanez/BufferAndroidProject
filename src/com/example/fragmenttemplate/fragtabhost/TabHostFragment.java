package com.example.fragmenttemplate.fragtabhost;

import android.os.Bundle;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabHost.TabContentFactory;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockFragment;
import com.example.fragmenttemplate.R;
import com.example.fragmenttemplate.utils.ViewFinder;
import com.github.kevinsawicki.wishlist.ViewUtils;

public class TabHostFragment extends SherlockFragment implements OnTabChangeListener, TabContentFactory {

	/**
	 * findViewById helper
	 * */
	private ViewFinder mFinder;

	/**
	 * View pager
	 */
	protected ViewPager pager;

	/**
	 * Tab host
	 */
	protected TabHost host;

	/**
	 * Pager adapter
	 */
	protected FragmentPagerAdapter adapter;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(getContentView(), null);
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		mFinder = new ViewFinder(view);

		pager = mFinder.find(R.id.devices_vp);
		host = mFinder.find(R.id.devices_th);
		host.setup();
		host.setOnTabChangedListener(this);

		configureTabPager();
	}

	/**
	 * Get content view to be used when
	 * {@link #onCreateView(LayoutInflater, ViewGroup, Bundle)} is called
	 * 
	 * @return layout resource id
	 */
	protected int getContentView() {
		return R.layout.tabs_pager;
	}

	/**
	 * Create pager adapter
	 * 
	 * @return pager adapter
	 */
	protected FragmentPagerAdapter createAdapter(){
		return new DeviceFragmentPagerAdapter(getFragmentManager());
	}
	

	/**
	 * Configure tabs and pager
	 */
	protected void configureTabPager() {
		if (adapter == null) {
			createPager();
			createTabs();
		}
	}

	private void createTabs() {

		if (host.getTabWidget().getTabCount() > 0) {
			host.setCurrentTab(0);
			host.clearAllTabs();
		}

		LayoutInflater inflater = getActivity().getLayoutInflater();
		int count = adapter.getCount();
		for (int i = 0; i < count; i++) {
			TabSpec spec = host.newTabSpec("tab" + i);
			spec.setContent(this);
			View view = inflater.inflate(R.layout.tab, null);
			TextView tab_tv = (TextView) view.findViewById(R.id.tab_tv);
			tab_tv.setText(getTitle(i));
			spec.setIndicator(view);
			host.addTab(spec);
		}

	}

	/**
	 * Add emptyview to tabhost
	 */
	@Override
	public View createTabContent(String tag) {
		return ViewUtils.setGone(new View(getActivity().getApplication()), true);
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();

		Log.e("p37td8", "onDestroyView");
		mFinder = null;
		pager = null;
		host = null;
		adapter = null;
	}

	/**
	 * Get title for position
	 * 
	 * @param position
	 * @return title
	 */
	protected String getTitle(final int position) {
		return adapter.getPageTitle(position).toString();
	}

	/**
	 * Create generic Adapter and add to pager
	 */
	private void createPager() {
		Log.e("p37td8","" + adapter);
		adapter = createAdapter();
		pager.setAdapter(adapter);

	}

	@Override
	public void onTabChanged(String tabId) {
		updateCurrentItem(host.getCurrentTab());
	}

	private void updateCurrentItem(final int newPosition) {
		if (newPosition > -1 && newPosition < adapter.getCount()) {
			pager.setCurrentItem(newPosition);
		}
	}
	
}
