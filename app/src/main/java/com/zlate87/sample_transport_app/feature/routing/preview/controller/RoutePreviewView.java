package com.zlate87.sample_transport_app.feature.routing.preview.controller;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.res.Resources;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zlate87.sample_transport_app.R;
import com.zlate87.sample_transport_app.feature.routing.map.controller.RouteMapActivity;
import com.zlate87.sample_transport_app.feature.routing.viewmodel.RouteDetails;
import com.zlate87.sample_transport_app.feature.routing.viewmodel.RoutePreview;

import java.util.List;

import static com.zlate87.sample_transport_app.feature.routing.map.controller.RouteMapActivity.ROUTE_MAP_INFO_INTENT_EXTRA_KEY;

/**
 * Custom view class for rendering route details preview
 */
public class RoutePreviewView extends LinearLayout {

	private static final String TAG = RoutePreviewView.class.getSimpleName();

	private TextView type;
	private TextView price;
	private TextView time;
	private TextView duration;
	private View timeDateSeparator;
	private LinearLayout iconsLinearLayout;
	private LinearLayout iconsTextLinearLayout;

	private RouteDetails routeDetails;

	private OnClickListener openMapOnClickListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			Intent routeMapIntent = new Intent(getContext(), RouteMapActivity.class);
			routeMapIntent.putExtra(ROUTE_MAP_INFO_INTENT_EXTRA_KEY, routeDetails.getRouteMapData());
			getContext().startActivity(routeMapIntent);
		}
	};

	public RoutePreviewView(Context context) {
		super(context);
		setup();
	}

	public RoutePreviewView(Context context, AttributeSet attrs) {
		super(context, attrs);
		setup();
	}

	private void setup() {
		setOrientation(VERTICAL);

		inflate(getContext(), R.layout.routing_route_preview, this);
		type = (TextView) findViewById(R.id.type);
		price = (TextView) findViewById(R.id.price);
		time = (TextView) findViewById(R.id.time);
		duration = (TextView) findViewById(R.id.duration);
		timeDateSeparator = findViewById(R.id.timeDateSeparator);
		iconsLinearLayout = (LinearLayout) findViewById(R.id.iconsLinearLayout);
		iconsTextLinearLayout = (LinearLayout) findViewById(R.id.iconsTextLinearLayout);
		findViewById(R.id.openMap).setOnClickListener(openMapOnClickListener);
	}

	/**
	 * Renders a given route  view model.
	 *
	 * @param routeDetails the view model
	 */
	public void renderRoutePreview(RouteDetails routeDetails) {
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
		View imageView = inflate(getContext(), R.layout.routing_route_segment_icon, null);
		iconsLinearLayout.addView(imageView);
	}

	private void addNewTextView() {
		View textView = inflate(getContext(), R.layout.routing_route_segment_text, null);
		iconsTextLinearLayout.addView(textView);
	}

	private void setIcon(RoutePreview routePreview, int i, ImageView imageView, TextView textView) {
		List<String> icons = routePreview.getIcons();
		List<String> iconsColors = routePreview.getIconsColors();
		List<String> iconText = routePreview.getIconsText();

		imageView.setVisibility(View.VISIBLE);
		textView.setVisibility(View.VISIBLE);

		String icon = icons.get(i);
		int resourcesIdentifier = getResourcesIdentifier(icon);
		if (resourcesIdentifier != 0) {
			imageView.setImageResource(resourcesIdentifier);
			String iconColor = iconsColors.get(i);
			int tintColor = Color.parseColor(iconColor);
			imageView.setColorFilter(tintColor);
			textView.setBackgroundColor(tintColor);
			textView.setText(iconText.get(i));
		} else {
			Log.w(TAG, String.format("addIcons: no image found for icon [%s]", icon));
			imageView.setVisibility(View.GONE);
			textView.setVisibility(View.GONE);
		}
	}

	private int getResourcesIdentifier(String icon) {
		Resources resources = getContext().getResources();
		ApplicationInfo applicationInfo = getContext().getApplicationInfo();
		return resources.getIdentifier(icon, "mipmap", applicationInfo.packageName);
	}

}
