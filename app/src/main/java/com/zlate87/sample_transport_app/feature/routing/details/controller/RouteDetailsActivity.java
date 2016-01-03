package com.zlate87.sample_transport_app.feature.routing.details.controller;

import android.os.Bundle;

import com.zlate87.sample_transport_app.R;
import com.zlate87.sample_transport_app.feature.routing.viewmodel.RouteDetails;
import com.zlate87.sample_transport_app.feature.routing.viewmodel.RouteMapData;

public class RouteDetailsActivity extends AbstractRouteDetailsMapActivity {

	/**
	 * Intent extra key for the {@code RouteDetails} that should be used.
	 */
	public static final String ROUTE_DETAILS_INTENT_EXTRA_KEY = "ROUTE_DETAILS_INTENT_EXTRA_KEY";

	private RouteDetails routeDetails;

	@Override
	protected int getContentViewId() {
		return R.layout.routing_route_details_activity;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		routeDetails = (RouteDetails) getIntent().getExtras().get(ROUTE_DETAILS_INTENT_EXTRA_KEY);
		displayRoute();
	}

	private void displayRoute() {
	}

	@Override
	protected RouteMapData getRouteMapInfo() {
		return routeDetails.getRouteMapData();
	}
}
