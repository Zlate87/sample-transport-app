package com.zlate87.sample_transport_app.feature.routing.preview.controller;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zlate87.sample_transport_app.R;
import com.zlate87.sample_transport_app.feature.routing.details.controller.RouteDetailsActivity;
import com.zlate87.sample_transport_app.feature.routing.viewmodel.RouteDetails;
import com.zlate87.sample_transport_app.feature.routing.viewmodel.RoutePreview;

import java.util.List;

import static com.zlate87.sample_transport_app.feature.routing.details.controller.RouteDetailsActivity.ROUTE_DETAILS_INTENT_EXTRA_KEY;

/**
 * {@code ViewHolder} for a route.
 */
public class RouteViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

	private static final String TAG = RouteViewHolder.class.getSimpleName();

	private Context context;

	private TextView type;
	private TextView price;
	private TextView time;
	private TextView duration;
	private View timeDateSeparator;
	private LinearLayout iconsLinearLayout;
	private LinearLayout iconsTextLinearLayout;
	private RouteDetails routeDetails;

	/**
	 * Constructor.
	 * @param itemView the view
	 */
	public RouteViewHolder(View itemView) {
		super(itemView);
		itemView.setOnClickListener(this);
		context = itemView.getContext();
		setupView(itemView);
	}

	private void setupView(View itemView) {
		type = (TextView) itemView.findViewById(R.id.type);
		price = (TextView) itemView.findViewById(R.id.price);
		time = (TextView) itemView.findViewById(R.id.time);
		duration = (TextView) itemView.findViewById(R.id.duration);
		timeDateSeparator = itemView.findViewById(R.id.timeDateSeparator);
		iconsLinearLayout = (LinearLayout) itemView.findViewById(R.id.iconsLinearLayout);
		iconsTextLinearLayout = (LinearLayout) itemView.findViewById(R.id.iconsTextLinearLayout);
	}

	/**
	 * Re use the view
	 * @param routeDetails the view model
	 */
	public void reUseView(RouteDetails routeDetails) {
		this.routeDetails = routeDetails;
		RoutePreview routePreview = routeDetails.getRoutePreview();
		type.setText(routePreview.getType());

		String priceText = routePreview.getPrice();
		price.setText(priceText);
		price.setVisibility(priceText == null ? View.GONE : View.VISIBLE);
		timeDateSeparator.setVisibility(priceText == null ? View.GONE : View.VISIBLE);

		String combinedTime = String.format("%s -> %s", routePreview.getLeaveTime(), routePreview.getArriveTime());
		time.setText(combinedTime);

		duration.setText(routePreview.getDuration());

		addIcons(routePreview);
	}

	private void addIcons(RoutePreview routePreview) {
		List<String> icons = routePreview.getIcons();

		// make sure there is an image views for every icon
		int imagesViewsToBeAdded = icons.size() - iconsLinearLayout.getChildCount();
		for (int i = 0; i < imagesViewsToBeAdded; i++) {
			addNewImageView();
			addNewTextView();
		}

		// hide images views that should not be used
		for (int i = icons.size(); i < iconsLinearLayout.getChildCount(); i++) {
			iconsLinearLayout.getChildAt(i).setVisibility(View.GONE);
			iconsTextLinearLayout.getChildAt(i).setVisibility(View.GONE);
		}

		// load the icons
		for (int i = 0; i < icons.size(); i++) {
			ImageView imageView = (ImageView) iconsLinearLayout.getChildAt(i);
			TextView textView = (TextView) iconsTextLinearLayout.getChildAt(i);
			setIcon(routePreview, i, imageView, textView);
		}

	}

	private void addNewImageView() {
		ImageView imageView = new ImageView(context);
		iconsLinearLayout.addView(imageView);
	}

	private void addNewTextView() {
		TextView textView = new TextView(context);
		final float scale = context.getResources().getDisplayMetrics().density;
		int pixels = (int) (56 * scale + 0.5f);
		textView.setWidth(pixels);
		textView.setGravity(Gravity.CENTER);
		iconsTextLinearLayout.addView(textView);
	}

	private void setIcon(RoutePreview routePreview, int i, ImageView imageView, TextView textView) {
		List<String> icons = routePreview.getIcons();
		List<String> iconsColors = routePreview.getIconsColors();
		List<String> iconText = routePreview.getIconsText();

		imageView.setVisibility(View.VISIBLE);
		textView.setVisibility(View.VISIBLE);

		int id = context.getResources().getIdentifier(icons.get(i), "mipmap", context.getApplicationInfo().packageName);
		if (id != 0) {
			imageView.setImageResource(id);
			String iconColor = iconsColors.get(i);
			int tintColor = Color.parseColor(iconColor);
			imageView.setColorFilter(tintColor);
			textView.setBackgroundColor(tintColor);
			textView.setText(iconText.get(i));
		} else {
			Log.w(TAG, String.format("addIcons: no image found for icon [%s]", icons.get(i)));
			imageView.setVisibility(View.GONE);
			textView.setVisibility(View.GONE);
		}
	}

	@Override
	public void onClick(View v) {
		Intent routeDetailsIntent = new Intent(context, RouteDetailsActivity.class);
		routeDetailsIntent.putExtra(ROUTE_DETAILS_INTENT_EXTRA_KEY, routeDetails);
		context.startActivity(routeDetailsIntent);
	}
}
