package com.example.fragmenttemplate;

import java.util.Collections;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.Loader;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.github.kevinsawicki.wishlist.ViewUtils;
import com.github.mobile.ui.ItemListFragment;

public class ItemListFrgmnt<E> extends CustomFragmentItem implements
		LoaderCallbacks<String> { // TODO <String>

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
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		if (!items.isEmpty())
			setListShown(true, false);
	}
	
	/**
     * Set list shown or progress bar show
     *
     * @param shown
     * @return this fragment
     */
    public ItemListFrgmnt<E> setListShown(final boolean shown) {
        return setListShown(shown, true);
    }

	private ItemListFrgmnt<E> setListShown(boolean shown, boolean animate) {

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
	
	
	
	private ItemListFrgmnt<E> fadeIn(final View view, final boolean animate) {
        if (view != null)
            if (animate)
                view.startAnimation(AnimationUtils.loadAnimation(getActivity(),
                        android.R.anim.fade_in));
            else
                view.clearAnimation();
        return this;
    }

    private ItemListFrgmnt<E> show(final View view) {
        ViewUtils.setGone(view, false);
        return this;
    }

    private ItemListFrgmnt<E> hide(final View view) {
        ViewUtils.setGone(view, true);
        return this;
    }
    
    /**
     * Set empty text on list fragment
     *
     * @param message
     * @return this fragment
     */
    protected ItemListFrgmnt<E> setEmptyText(final String message) {
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
    protected ItemListFrgmnt<E> setEmptyText(final int resId) {
        if (emptyView != null)
            emptyView.setText(resId);
        return this;
    }
    
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
	public Loader<String> onCreateLoader(int arg0, Bundle arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onLoadFinished(Loader<String> arg0, String arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onLoaderReset(Loader<String> arg0) {
		// TODO Auto-generated method stub

	}
}
