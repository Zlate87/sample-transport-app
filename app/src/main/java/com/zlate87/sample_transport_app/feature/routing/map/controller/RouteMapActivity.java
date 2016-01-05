package com.zlate87.sample_transport_app.feature.routing.map.controller;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.zlate87.sample_transport_app.R;
import com.zlate87.sample_transport_app.base.App;
import com.zlate87.sample_transport_app.base.controller.BaseActivity;
import com.zlate87.sample_transport_app.feature.routing.service.RouteMapService;
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
	private static final int PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;
	private static final String HINT_KEY = "RouteMapActivity_HINT_KEY";

	@Inject
	RouteMapService routeMapService;

	private RouteMapData routeMapInfo;

	private GoogleMap googleMap;

	@Override
	protected int getContentViewId() {
		return R.layout.routing_route_map_activity;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		App.getInstance().getComponent().inject(this);
		routeMapInfo = (RouteMapData) getIntent().getExtras().get(ROUTE_MAP_INFO_INTENT_EXTRA_KEY);
		showHint(HINT_KEY, R.id.coordinatorLayout, R.string.routing_routeMap_pinHint);
		prepareMap();
	}

	private void prepareMap() {
		SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
		mapFragment.getMapAsync(this);
	}

	@Override
	public void onMapReady(GoogleMap googleMap) {
		this.googleMap = googleMap;
		enableMyLocationOnMap();
		routeMapService.addRouteToMap(googleMap, routeMapInfo);
	}

	public void enableMyLocationOnMap() {
		if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
						!= PackageManager.PERMISSION_GRANTED) {
				ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
								PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
			return;
		}
		googleMap.setMyLocationEnabled(true);
	}

	@Override
	public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults) {
		switch (requestCode) {
			case PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION: {
				if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
					enableMyLocationOnMap();
				}
				break;
			}
		}
	}

}
