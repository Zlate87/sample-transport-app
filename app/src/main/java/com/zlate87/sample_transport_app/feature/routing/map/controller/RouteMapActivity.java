package com.zlate87.sample_transport_app.feature.routing.map.controller;

import android.os.Bundle;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.zlate87.sample_transport_app.R;
import com.zlate87.sample_transport_app.base.App;
import com.zlate87.sample_transport_app.base.controller.BaseActivity;
import com.zlate87.sample_transport_app.feature.routing.service.PolylineService;
import com.zlate87.sample_transport_app.feature.routing.viewmodel.RouteMapData;

import javax.inject.Inject;

/**
 * Abstract activity class responsible for displaying the route map.
 */
public class RouteMapActivity extends BaseActivity implements OnMapReadyCallback {

	/**
	 * Intent extra key for the {@code RouteDetails} that should be used.
	 */
	public static final String ROUTE_MAP_INFO_INTENT_EXTRA_KEY = "ROUTE_MAP_INFO_INTENT_EXTRA_KEY";

	@Inject
	PolylineService polylineService;

	private RouteMapData routeMapInfo;

	private MapView mapView;

	@Override
	protected int getContentViewId() {
		return R.layout.routing_route_map_activity;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		App.getInstance().getComponent().inject(this);

		routeMapInfo = (RouteMapData) getIntent().getExtras().get(ROUTE_MAP_INFO_INTENT_EXTRA_KEY);

		mapView = (MapView) findViewById(R.id.mapView);
		mapView.onCreate(savedInstanceState);
		mapView.getMapAsync(this);
	}

	@Override
	protected void onResume() {
		super.onResume();
		mapView.onResume();
	}

	@Override
	protected void onPause() {
		mapView.onPause();
		super.onPause();
	}

	@Override
	protected void onDestroy() {
		mapView.onDestroy();
		super.onDestroy();
	}

	@Override
	public void onLowMemory() {
		super.onLowMemory();
		mapView.onLowMemory();
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		mapView.onSaveInstanceState(outState);
	}

	@Override
	public void onMapReady(GoogleMap googleMap) {
		polylineService.addRoutePolylineToMap(googleMap, routeMapInfo);
	}

}
