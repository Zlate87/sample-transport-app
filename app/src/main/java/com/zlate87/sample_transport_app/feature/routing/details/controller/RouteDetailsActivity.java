package com.zlate87.sample_transport_app.feature.routing.details.controller;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

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
	public static final String SHARED_PREFERENCES_FILE = "SHARED_PREFERENCES_FILE";
	public static final String IS_HINT_PRESENTED_KEY = "isHintPresentedKey";

	private RouteDetails routeDetails;

	@Override
	protected int getContentViewId() {
		return R.layout.routing_route_details_activity;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		routeDetails = (RouteDetails) getIntent().getExtras().get(ROUTE_DETAILS_INTENT_EXTRA_KEY);
		showHint();
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

	private void showHint() {
		final SharedPreferences prefs = getSharedPreferences(SHARED_PREFERENCES_FILE, MODE_PRIVATE);
		boolean isHintPresented = prefs.getBoolean(IS_HINT_PRESENTED_KEY, false);
		if (!isHintPresented) {
			View coordinatorLayout = findViewById(R.id.coordinatorLayout);
			int messageId = R.string.routing_routeDetails_segmentExpansionHint;
			final Snackbar snackbar = Snackbar.make(coordinatorLayout, messageId, Snackbar.LENGTH_INDEFINITE);
			snackbar.setAction(R.string.doNotShowAgain, new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					SharedPreferences.Editor editor = prefs.edit();
					editor.putBoolean(IS_HINT_PRESENTED_KEY, true);
					editor.apply();
				}
			});
			snackbar.show();
		}
	}

}
