package com.zlate87.sample_transport_app.feature.routing.controller;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.zlate87.sample_transport_app.R;
import com.zlate87.sample_transport_app.feature.routing.viewmodel.RoutePreview;

/**
 * {@code ViewHolder} for a route.
 */
public class RouteViewHolder extends RecyclerView.ViewHolder {

	private TextView type;
	private TextView price;
	private TextView time;
	private TextView duration;
	private View timeDateSeparator;

	/**
	 * Constructor.
	 * @param itemView the view
	 */
	public RouteViewHolder(View itemView) {
		super(itemView);
		setupView(itemView);
	}

	private void setupView(View itemView) {
		type = (TextView) itemView.findViewById(R.id.type);
		price = (TextView) itemView.findViewById(R.id.price);
		time = (TextView) itemView.findViewById(R.id.time);
		duration = (TextView) itemView.findViewById(R.id.duration);
		timeDateSeparator = itemView.findViewById(R.id.timeDateSeparator);
	}

	/**
	 * Re use the view
	 * @param routePreview the view model
	 */
	public void reUseView(RoutePreview routePreview) {
		type.setText(routePreview.getType());
		String priceText = routePreview.getPrice();
		price.setText(priceText);
		price.setVisibility(priceText == null ? View.GONE : View.VISIBLE);
		timeDateSeparator.setVisibility(priceText == null ? View.GONE : View.VISIBLE);
		String combinedTime = String.format("%s -> %s", routePreview.getLeaveTime(), routePreview.getArriveTime());
		time.setText(combinedTime);
		duration.setText(routePreview.getDuration());
		// TODO: 1/1/2016 Render the icons
	}
}
