package com.zlate87.sample_transport_app;

import android.annotation.TargetApi;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

/**
 * Class containing custom matchers.
 */
public class CustomMatchers {

	/**
	 * Matcher that matches ImageView drawable with a drawable from resources.
	 *
	 * @param resourceId the resource id for the drawable.
	 * @return {@code true} if the drawable matches, {@code false} if not
	 */
	public static Matcher<View> withDrawable(final int resourceId) {
		return new TypeSafeMatcher<View>() {
			@Override
			public void describeTo(Description description) {
				description.appendText(String.format("with drawable from resource id: [%s]", resourceId));
			}

			@TargetApi(Build.VERSION_CODES.LOLLIPOP)
			@Override
			public boolean matchesSafely(View view) {
				final Drawable resourcesDrawable = getDrawable(view, resourceId);
				final ImageView imageView = (ImageView) view;
				if (imageView.getDrawable() == null) {
					return resourcesDrawable == null;
				}
				return areDrawablesIdentical(imageView.getDrawable(), resourcesDrawable);
			}
		};
	}

	/**
	 * Matcher that matches view with a given id at given position in a recycler view.
	 *
	 * @param recyclerViewId the recycler view id
	 * @param position       element position where the view is at recycler view
	 * @param viewId         the view id
	 * @return {@code true} if the view matches, {@code false} if not
	 */
	public static Matcher<View> viewAtPositionInRecyclerView(final int recyclerViewId, final int position,
																													 final int viewId) {
		return new TypeSafeMatcher<View>() {
			Resources resources = null;
			View childView;

			public void describeTo(Description description) {
				String idDescription = Integer.toString(recyclerViewId);
				if (resources != null) {
					try {
						idDescription = this.resources.getResourceName(recyclerViewId);
					} catch (Resources.NotFoundException var4) {
						idDescription = String.format("%d (resource name not found)", recyclerViewId);
					}
				}
				description.appendText("with id: " + idDescription);
			}

			public boolean matchesSafely(View view) {
				resources = view.getResources();

				if (childView == null) {
					RecyclerView recyclerView = (RecyclerView) view.getRootView().findViewById(recyclerViewId);
					if (recyclerView != null && recyclerView.getId() == recyclerViewId) {
						childView = recyclerView.getChildAt(position);
					} else {
						return false;
					}
				}

				if (viewId == -1) {
					return view == childView;
				} else {
					View targetView = childView.findViewById(viewId);
					return view == targetView;
				}
			}
		};
	}

	/**
	 * Matches dynamic view in a linear layout within a recycler view for a given:
	 *
	 * @param recyclerViewId the id of the recycler view
	 * @param viewPosition   the position index of the route preview view in the recycler view
	 * @param iconsLayout    the id of the layout for the icon attributes
	 * @param iconPosition   the icon position inside the icons layout
	 * @return the matcher
	 */
	public static Matcher<View> dynamicView(final int recyclerViewId,
																					final int viewPosition,
																					final int iconsLayout,
																					final int iconPosition) {
		return new TypeSafeMatcher<View>() {
			Resources resources = null;
			View routePreviewView;

			public void describeTo(Description description) {
				String idDescription = Integer.toString(recyclerViewId);
				if (resources != null) {
					try {
						idDescription = this.resources.getResourceName(recyclerViewId);
					} catch (Resources.NotFoundException var4) {
						idDescription = String.format("%d (resource name not found)", recyclerViewId);
					}
				}
				description.appendText("with id: " + idDescription);
			}

			public boolean matchesSafely(View view) {
				resources = view.getResources();

				if (routePreviewView == null) {
					RecyclerView recyclerView = (RecyclerView) view.getRootView().findViewById(recyclerViewId);
					if (recyclerView != null && recyclerView.getId() == recyclerViewId) {
						routePreviewView = recyclerView.getChildAt(viewPosition);
					} else {
						return false;
					}
				}

				LinearLayout iconsLinearLayout = (LinearLayout) routePreviewView.findViewById(iconsLayout);
				View targetView = iconsLinearLayout.getChildAt(iconPosition);
				return view == targetView;
			}
		};
	}

	private static Drawable getDrawable(View view, int resourceId) {
		final Drawable resourcesDrawable;
		if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
			resourcesDrawable = view.getResources().getDrawable(resourceId);
		} else {
			resourcesDrawable = view.getContext().getDrawable(resourceId);
		}
		return resourcesDrawable;
	}

	private static boolean areDrawablesIdentical(Drawable drawableA, Drawable drawableB) {
		Drawable.ConstantState constantStateA = drawableA.getConstantState();
		Drawable.ConstantState constantStateB = drawableB.getConstantState();
		return (constantStateA != null && constantStateB != null && constantStateA.equals(constantStateB))
						|| getBitmap(drawableA).sameAs(getBitmap(drawableB));
	}

	private static Bitmap getBitmap(Drawable drawable) {
		return ((BitmapDrawable) drawable).getBitmap();
	}
}
