package com.example.fragmenttemplate;

import java.util.Collections;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.github.kevinsawicki.wishlist.MultiTypeAdapter;
import com.github.kevinsawicki.wishlist.SingleTypeAdapter;
import com.github.kevinsawicki.wishlist.ViewUtils;

public abstract class AMultListFragment<E> extends CustomFragmentItem implements
		LoaderCallbacks<List<E>> {

	private static final String FORCE_REFRESH = "forceRefresh";

	/**
	 * List items provided to {@link #onLoadFinished(Loader, List)}
	 */
	protected List<E> items = Collections.emptyList();

	/**
	 * List view
	 */
	protected ListView listView;

	/**
	 * Empty view
	 */
	protected TextView emptyView;

	/**
	 * Progress bar
	 */
	protected ProgressBar progressBar;

	/**
	 * Is the list currently shown?
	 */
	protected boolean listShown;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.item_list, null);
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		emptyView = mViewFinder.find(android.R.id.empty);
		progressBar = mViewFinder.find(R.id.pb_loading); 
		listView = mViewFinder.find(android.R.id.list);
		
		
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				onListItemClick((ListView) parent, view, position, id);
			}
		});
		configureList(getActivity(), getListView());
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		if (!items.isEmpty())
			setListShown(true, false);
		
		getLoaderManager().initLoader(0, null, this);
	}

	@Override
	public void onDestroyView() {
		listShown = false;
		emptyView = null;
		progressBar = null;
		listView = null;

		super.onDestroyView();
	}

	/**
	 * Set the list to be shown
	 */
	protected void showList() {
		setListShown(true, isResumed());
	}

	/**
	 * Set list shown or progress bar show
	 * 
	 * @param shown
	 * @return this fragment
	 */
	public AMultListFragment<E> setListShown(final boolean shown) {
		return setListShown(shown, true);
	}

	private AMultListFragment<E> setListShown(boolean shown, boolean animate) {

		if (!isUsable())
			return this;

		if (shown == listShown) {
			if (shown)
				// List has already been shown so hide/show the empty view with
				// no fade effect
				if (items.isEmpty())
					hide(listView).show(emptyView);
				else
					hide(emptyView).show(listView);
			return this;
		}

		listShown = shown;

		if (shown)
			if (!items.isEmpty())
				hide(progressBar).hide(emptyView).fadeIn(listView, animate)
						.show(listView);
			else
				hide(progressBar).hide(listView).fadeIn(emptyView, animate)
						.show(emptyView);
		else
			hide(listView).hide(emptyView).fadeIn(progressBar, animate)
					.show(progressBar);

		return this;

	}

	private AMultListFragment<E> fadeIn(final View view, final boolean animate) {
		if (view != null)
			if (animate)
				view.startAnimation(AnimationUtils.loadAnimation(getActivity(),
						android.R.anim.fade_in));
			else
				view.clearAnimation();
		return this;
	}

	private AMultListFragment<E> show(final View view) {
		ViewUtils.setGone(view, false);
		return this;
	}

	private AMultListFragment<E> hide(final View view) {
		ViewUtils.setGone(view, true);
		return this;
	}

	/**
	 * Set empty text on list fragment
	 * 
	 * @param message
	 * @return this fragment
	 */
	protected AMultListFragment<E> setEmptyText(final String message) {
		if (emptyView != null)
			emptyView.setText(message);
		return this;
	}

	/**
	 * Set empty text on list fragment
	 * 
	 * @param resId
	 * @return this fragment
	 */
	protected AMultListFragment<E> setEmptyText(final int resId) {
		if (emptyView != null)
			emptyView.setText(resId);
		return this;
	}

	/**
	 * Force a refresh of the items displayed ignoring any cached items
	 */
	protected void forceRefresh() {
		Bundle bundle = new Bundle();
		bundle.putBoolean(FORCE_REFRESH, true);
		refresh(bundle);
	}

	/**
	 * Refresh the list with the progress bar showing
	 */
	protected void refreshWithProgress() {
		items.clear();
		setListShown(false);
		refresh();
	}

	/**
	 * Refresh the fragment's list
	 */
	public void refresh() {
		refresh(null);
	}

	private void refresh(final Bundle args) {
		if (!isUsable())
			return;
		getSherlockActivity()
				.setSupportProgressBarIndeterminateVisibility(true);
		getLoaderManager().restartLoader(0, args, this);
	}

	/**
	 * Get {@link ListView}
	 * 
	 * @return listView
	 */
	public ListView getListView() {
		return listView;
	}

	/**
	 * Configure list after view has been created
	 * 
	 * @param activity
	 * @param listView
	 */
	protected void configureList(Activity activity, ListView listView) {
		 listView.setAdapter(createAdapter()); 
	}

	/**
	 * Get list adapter
	 * 
	 * @return list adapter
	 */
	@SuppressWarnings("unchecked")
	protected MultiTypeAdapter getListAdapter() {
		if (listView != null)
			return (MultiTypeAdapter) listView
					.getAdapter();
		else
			return null;
	}

	/**
	 * Notify the underlying adapter that the data set has changed
	 * 
	 * @return this fragment
	 */
	protected AMultListFragment<E> notifyDataSetChanged() {
			MultiTypeAdapter typeAdapter = getListAdapter();
			if (typeAdapter != null)
				typeAdapter.notifyDataSetChanged();
		return this;
	}

	/**
	 * Create adapter to display items
	 * 
	 * @return adapter
	 */

	protected MultiTypeAdapter createAdapter() {
		return createAdapter(items);
	}

	/**
	 * Create adapter to display items
	 * 
	 * @param items
	 * @return adapter
	 */
	protected abstract MultiTypeAdapter createAdapter(final List<E> items);

	/**
	 * Callback when a list view item is clicked
	 * 
	 * @param l
	 * @param v
	 * @param position
	 * @param id
	 */
	public void onListItemClick(ListView l, View v, int position, long id) {
	}

	@Override
	public void onLoadFinished(Loader<List<E>> loader, List<E> items) {}

	/**
	 * Show exception in a {@link Toast}
	 * 
	 * @param e
	 * @param defaultMessage
	 */
	protected void showError(final Exception e, final int defaultMessage) {
		Toasts.show(getActivity(), e, defaultMessage);
	}

	/**
	 * Get error message to display for exception
	 * 
	 * @param exception
	 * @return string resource id
	 */
	protected abstract int getErrorMessage(Exception exception);

	/**
	 * Get exception from loader if it provides one by being a
	 * {@link ThrowableLoader}
	 * 
	 * @param loader
	 * @return exception or null if none provided
	 */
	protected Exception getException(final Loader<List<E>> loader) {
		// if (loader instanceof ThrowableLoader)
		// return ((ThrowableLoader<List<E>>) loader).clearException();
		// else
		return null;
	}

	@Override
	public void onLoaderReset(Loader<List<E>> arg0) {
		// Intentionally left blank
	}

}
