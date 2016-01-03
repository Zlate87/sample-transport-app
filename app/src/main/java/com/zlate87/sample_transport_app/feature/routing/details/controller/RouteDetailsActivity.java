package com.zlate87.sample_transport_app.feature.routing.details.controller;

import android.os.Bundle;

import com.zlate87.sample_transport_app.R;
import com.zlate87.sample_transport_app.base.controller.BaseActivity;
import com.zlate87.sample_transport_app.feature.routing.preview.controller.RoutePreviewView;
import com.zlate87.sample_transport_app.feature.routing.viewmodel.RouteDetails;

public class RouteDetailsActivity extends BaseActivity {

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

		((RoutePreviewView) findViewById(R.id.routePreview)).renderRoutePreview(routeDetails);
	}

}
