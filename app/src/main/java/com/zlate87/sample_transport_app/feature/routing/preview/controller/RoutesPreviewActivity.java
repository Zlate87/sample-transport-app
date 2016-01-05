package com.zlate87.sample_transport_app.feature.routing.preview.controller;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.zlate87.sample_transport_app.R;
import com.zlate87.sample_transport_app.base.App;
import com.zlate87.sample_transport_app.base.controller.BaseActivity;
import com.zlate87.sample_transport_app.feature.routing.model.RouteResponse;
import com.zlate87.sample_transport_app.feature.routing.model.RouteValidationResult;
import com.zlate87.sample_transport_app.feature.routing.service.ValidationService;
import com.zlate87.sample_transport_app.feature.routing.service.ViewModelMappingService;
import com.zlate87.sample_transport_app.feature.routing.viewmodel.RouteDetails;

import java.util.List;

import javax.inject.Inject;

/**
 * Activity responsible for displaying a list of routes.
 */
public class RoutesPreviewActivity extends BaseActivity {

	private static final String TAG = RoutesPreviewActivity.class.getSimpleName();

	/**
	 * Intent extra key for the {@code RouteResponse} that should be used when creating the routes list.
	 */
	public static final String ROUTE_RESPONSE_INTENT_EXTRA_KEY = "ROUTE_RESPONSE_INTENT_EXTRA_KEY";

	@Inject
	ValidationService validationService;

	@Inject
	ViewModelMappingService viewModelMappingService;

	private RecyclerView routesRecyclerView;
	private TextView messageTextView;

	@Override
	protected int getContentViewId() {
		return R.layout.routing_routes_preview_activity;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		App.getInstance().getComponent().inject(this);
		setupView();
		displayRoutes();
	}

	private void setupView() {
		routesRecyclerView = (RecyclerView) findViewById(R.id.routesRecyclerView);
		messageTextView = (TextView) findViewById(R.id.messageTextView);
	}

	private void displayRoutes() {
		RouteResponse routeResponse = (RouteResponse) getIntent().getExtras().get(ROUTE_RESPONSE_INTENT_EXTRA_KEY);

		// validate
		RouteValidationResult routeValidationResult = validationService.validate(routeResponse);
		if (!routeValidationResult.isValid()) {
			Log.d(TAG, "displayRoutes: the route response is invalid");
			showValidationMessage(routeValidationResult.getMessageStringId());
			return;
		}

		List<RouteDetails> routeDetailsList = viewModelMappingService.map(routeResponse);
		displayRoutesInRecyclerView(routeDetailsList);
	}

	private void displayRoutesInRecyclerView(List<RouteDetails> routeDetailsList) {
		LinearLayoutManager layoutManager = new LinearLayoutManager(this);
		routesRecyclerView.setLayoutManager(layoutManager);
		RoutesPreviewAdapter routesPreviewAdapter = new RoutesPreviewAdapter(routeDetailsList);
		routesRecyclerView.setAdapter(routesPreviewAdapter);
	}

	private void showValidationMessage(int messageStringId) {
		routesRecyclerView.setVisibility(View.GONE);
		messageTextView.setVisibility(View.VISIBLE);
		messageTextView.setText(messageStringId);
	}
}
