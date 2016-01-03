package com.zlate87.sample_transport_app.feature.routing.details.controller;

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
public abstract class AbstractRouteDetailsMapActivity extends BaseActivity implements OnMapReadyCallback {

	@Inject
	PolylineService polylineService;

	private MapView mapView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		App.getInstance().getComponent().inject(this);
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
		RouteMapData routeMapInfo = getRouteMapInfo();
		polylineService.addRoutePolylineToMap(googleMap, routeMapInfo);
	}

	protected abstract RouteMapData getRouteMapInfo();
}
