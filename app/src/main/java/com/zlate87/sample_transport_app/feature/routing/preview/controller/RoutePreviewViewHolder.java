package com.zlate87.sample_transport_app.feature.routing.preview.controller;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.zlate87.sample_transport_app.feature.routing.details.controller.RouteDetailsActivity;
import com.zlate87.sample_transport_app.feature.routing.viewmodel.RouteDetails;

import static com.zlate87.sample_transport_app.feature.routing.details.controller.RouteDetailsActivity.ROUTE_DETAILS_INTENT_EXTRA_KEY;

/**
 * {@code ViewHolder} for a route preview.
 */
public class RoutePreviewViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

	private Context context;

	private RouteDetails routeDetails;

	private RoutePreviewView routePreviewView;

	/**
	 * Constructor.
	 *
	 * @param itemView the view
	 */
	public RoutePreviewViewHolder(View itemView) {
		super(itemView);
		itemView.setOnClickListener(this);
		routePreviewView = (RoutePreviewView) itemView;
		context = itemView.getContext();
	}

	/**
	 * Re use the view.
	 *
	 * @param routeDetails the view model
	 */
	public void reUseView(RouteDetails routeDetails) {
		this.routeDetails = routeDetails;
		routePreviewView.renderRoutePreview(routeDetails);
	}

	@Override
	public void onClick(View v) {
		Intent routeDetailsIntent = new Intent(context, RouteDetailsActivity.class);
		routeDetailsIntent.putExtra(ROUTE_DETAILS_INTENT_EXTRA_KEY, routeDetails);
		context.startActivity(routeDetailsIntent);
	}
}
