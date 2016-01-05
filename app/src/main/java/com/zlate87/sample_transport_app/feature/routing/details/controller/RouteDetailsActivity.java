package com.zlate87.sample_transport_app.feature.routing.details.controller;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.zlate87.sample_transport_app.R;
import com.zlate87.sample_transport_app.base.controller.BaseActivity;
import com.zlate87.sample_transport_app.feature.routing.preview.controller.RoutePreviewView;
import com.zlate87.sample_transport_app.feature.routing.viewmodel.RouteDetails;

/**
 * Activity responsible for displaying the
 */
public class RouteDetailsActivity extends BaseActivity {

	/**
	 * Intent extra key for the {@code RouteDetails} that should be used.
	 */
	public static final String ROUTE_DETAILS_INTENT_EXTRA_KEY = "ROUTE_DETAILS_INTENT_EXTRA_KEY";
	private static final String HINT_KEY = "RouteDetailsActivity_HINT_KEY";

	private RouteDetails routeDetails;

	@Override
	protected int getContentViewId() {
		return R.layout.routing_route_details_activity;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		routeDetails = (RouteDetails) getIntent().getExtras().get(ROUTE_DETAILS_INTENT_EXTRA_KEY);
		showHint(HINT_KEY, R.id.coordinatorLayout, R.string.routing_routeDetails_segmentExpansionHint);
		displayRouteDetails();
	}

	private void displayRouteDetails() {
		((RoutePreviewView) findViewById(R.id.routePreview)).renderRoutePreview(routeDetails);
		RecyclerView segmentsRecyclerView = (RecyclerView) findViewById(R.id.routeSegmentsRecyclerView);
		LinearLayoutManager layoutManager = new LinearLayoutManager(this);
		segmentsRecyclerView.setLayoutManager(layoutManager);
		RouteSegmentsAdapter routeSegmentsAdapter = new RouteSegmentsAdapter(routeDetails.getRouteSegments());
		segmentsRecyclerView.setAdapter(routeSegmentsAdapter);
	}

}
