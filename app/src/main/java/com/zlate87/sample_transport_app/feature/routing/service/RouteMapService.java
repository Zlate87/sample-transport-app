package com.zlate87.sample_transport_app.feature.routing.service;

import android.graphics.Color;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.maps.android.PolyUtil;
import com.zlate87.sample_transport_app.feature.routing.viewmodel.PolylineData;
import com.zlate87.sample_transport_app.feature.routing.viewmodel.Position;
import com.zlate87.sample_transport_app.feature.routing.viewmodel.RouteMapData;

import java.util.List;

/**
 * Services class for working with the route map.
 */
public class RouteMapService {

	/**
	 * Adds the route to the map.
	 *
	 * @param googleMap    the map
	 * @param routeMapInfo the view model containing the information for the route
	 */
	public void addRouteToMap(final GoogleMap googleMap, final RouteMapData routeMapInfo) {
		final LatLngBounds.Builder latLngBounds = new LatLngBounds.Builder();
		for (PolylineData polylineData : routeMapInfo.getPolylineDataList()) {
			PolylineOptions polyline = getPolylineOptions(latLngBounds, polylineData);
			if (polyline != null) {
				googleMap.addPolyline(polyline);
			}
		}
		googleMap.setOnMapLoadedCallback(new GoogleMap.OnMapLoadedCallback() {
			@Override
			public void onMapLoaded() {
				addMarker(routeMapInfo.getStartPoint(), googleMap);
				addMarker(routeMapInfo.getEndPoint(), googleMap);
				googleMap.animateCamera(CameraUpdateFactory.newLatLngBounds(latLngBounds.build(), 200));
			}
		});
	}

	private void addMarker(Position position, GoogleMap googleMap) {
		googleMap.addMarker(new MarkerOptions()
						.position(new LatLng(position.getLatitude(), position.getLongitude()))
						.title(position.getName()));
	}

	public PolylineOptions getPolylineOptions(LatLngBounds.Builder latLngBoundsBuilder, PolylineData polylineData) {
		PolylineOptions polyline = new PolylineOptions();
		String encodedValue = polylineData.getEncodedValue();
		if (encodedValue == null) {
			return null;
		}
		List<LatLng> latLngList = PolyUtil.decode(encodedValue);
		for (LatLng latLng : latLngList) {
			latLngBoundsBuilder.include(latLng);
		}
		polyline.addAll(latLngList);
		polyline.color(Color.parseColor(polylineData.getColor()));
		return polyline;
	}
}
