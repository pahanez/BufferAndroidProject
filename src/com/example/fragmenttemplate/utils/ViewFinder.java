package com.example.fragmenttemplate.utils;

import android.content.res.Resources;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

public class ViewFinder {

	private static interface FindWrapper {

		View findViewById(int id);

		Resources getResources();
	}

	private static class ViewWrapper implements FindWrapper {

		private final View view;

		ViewWrapper(final View view) {
			this.view = view;
		}

		public View findViewById(final int id) {
			return view.findViewById(id);
		}

		public Resources getResources() {
			return view.getResources();
		}
	}

	private final FindWrapper wrapper;

	/**
	 * Create finder wrapping given view
	 * 
	 * @param view
	 */
	public ViewFinder(final View view) {
		wrapper = new ViewWrapper(view);
	}

	/**
	 * Find view with id
	 * 
	 * @param id
	 * @return found view
	 */
	@SuppressWarnings("unchecked")
	public <V extends View> V find(final int id) {
		return (V) wrapper.findViewById(id);
	}

	/**
	 * Get image view with id
	 * 
	 * @param id
	 * @return image view
	 */
	public ImageView imageView(final int id) {
		return find(id);
	}

	/**
	 * Get compound button with id
	 * 
	 * @param id
	 * @return image view
	 */
	public CompoundButton compoundButton(final int id) {
		return find(id);
	}

	/**
	 * Get text view with id
	 * 
	 * @param id
	 * @return text view
	 */
	public TextView textView(final int id) {
		return find(id);
	}

	/**
	 * Set text of child view with given id
	 * 
	 * @param id
	 * @param content
	 * @return text view
	 */
	public TextView setText(final int id, final CharSequence content) {
		final TextView text = find(id);
		text.setText(content);
		return text;
	}

	/**
	 * Set text of child view with given id
	 * 
	 * @param id
	 * @param content
	 * @return text view
	 */
	public TextView setText(final int id, final int content) {
		return setText(id, wrapper.getResources().getString(content));
	}

}
