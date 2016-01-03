package com.zlate87.sample_transport_app.feature.routing.service;

import android.graphics.Color;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.maps.android.PolyUtil;
import com.zlate87.sample_transport_app.feature.routing.viewmodel.PolylineData;
import com.zlate87.sample_transport_app.feature.routing.viewmodel.RouteMapData;

import java.util.List;

/**
 * Services class for working with polylines.
 */
public class PolylineService {

	/**
	 * Adds the route polyline to a map.
	 *
	 * @param googleMap    the map
	 * @param routeMapInfo the view model containing the information for the polyline
	 */
	public void addRoutePolylineToMap(final GoogleMap googleMap, RouteMapData routeMapInfo) {
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
				googleMap.animateCamera(CameraUpdateFactory.newLatLngBounds(latLngBounds.build(), 50));
			}
		});
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
