package com.zlate87.sample_transport_app.feature.routing.controller;

import android.os.Bundle;
import android.widget.TextView;

import com.zlate87.sample_transport_app.R;
import com.zlate87.sample_transport_app.base.App;
import com.zlate87.sample_transport_app.base.controller.BaseActivity;
import com.zlate87.sample_transport_app.feature.routing.model.RouteResponse;
import com.zlate87.sample_transport_app.feature.routing.model.RouteValidationResult;
import com.zlate87.sample_transport_app.feature.routing.service.ValidationService;

import javax.inject.Inject;

/**
 * Activity responsible for displaying a list of routes.
 */
public class RoutesListActivity extends BaseActivity {

	@Inject
	ValidationService validationService;

	private static final String TAG = RoutesListActivity.class.getSimpleName();

	/**
	 * Intent extra key for the {@code RouteResponse} that should be used when creating the routes list.
	 */
	public static final String ROUTE_RESPONSE_INTENT_EXTRA_KEY = "ROUTE_RESPONSE_INTENT_EXTRA_KEY";

	@Override
	protected int getContentViewId() {
		return R.layout.routing_routes_list_activity;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		App.getInstance().getComponent().inject(this);
		displayRoutes();
	}

	private void displayRoutes() {
		RouteResponse routeResponse = (RouteResponse) getIntent().getExtras().get(ROUTE_RESPONSE_INTENT_EXTRA_KEY);
		RouteValidationResult routeValidationResult = validationService.validate(routeResponse);
		if (!routeValidationResult.isValid()) {
			showNoValidRouteMessage(routeValidationResult.getMessageStringId());
			return;
		}

		// TODO: 1/1/2016 add the data in a recycler view
		TextView helloWorldLabel = (TextView) this.findViewById(R.id.helloWorldLabel);
		String text = String.format("%s routes found", routeResponse.getRoutes().size());
		helloWorldLabel.setText(text);
	}

	private void showNoValidRouteMessage(int messageStringId) {
		// TODO: 1/1/2016 display message to the user
	}
}
