package com.zlate87.sample_transport_app.feature.routing.controller;

import android.content.Intent;
import android.util.Log;
import android.view.View;

import com.zlate87.sample_transport_app.R;
import com.zlate87.sample_transport_app.base.controller.BaseActivity;
import com.zlate87.sample_transport_app.feature.routing.model.RouteQueryParameters;

import static com.zlate87.sample_transport_app.feature.routing.controller.LoadRouteActivity.ROUTE_QUERY_PARAMETERS_INTENT_EXTRA_KEY;

/**
 * Activity responsible for selecting a route.
 */
public class SelectRouteActivity extends BaseActivity {

	private static final String TAG = SelectRouteActivity.class.getSimpleName();

	@Override
	protected int getContentViewId() {
		return R.layout.routing_select_route_activity;
	}

	/**
	 * OnClickListener method for the route button.
	 *
	 * @param view the button
	 */
	public void route(View view) {
		Log.v(TAG, "route: called");
		RouteQueryParameters routeQueryParameters = new RouteQueryParameters();
		Intent loadRoutesActivityIntent = new Intent(this, LoadRouteActivity.class);
		loadRoutesActivityIntent.putExtra(ROUTE_QUERY_PARAMETERS_INTENT_EXTRA_KEY, routeQueryParameters);
		startActivity(loadRoutesActivityIntent);
	}
}
