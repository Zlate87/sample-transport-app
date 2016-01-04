package com.zlate87.sample_transport_app.feature.routing.viewmodel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * View model cass for displaying route details.
 */
public class RouteDetails implements Serializable {

	private RoutePreview routePreview;
	private RouteMapData routeMapData;
	private List<RouteSegment> routeSegments = new ArrayList<>();

	public RoutePreview getRoutePreview() {
		return routePreview;
	}

	public void setRoutePreview(RoutePreview routePreview) {
		this.routePreview = routePreview;
	}

	public RouteMapData getRouteMapData() {
		return routeMapData;
	}

	public void setRouteMapData(RouteMapData routeMapData) {
		this.routeMapData = routeMapData;
	}

	public List<RouteSegment> getRouteSegments() {
		return routeSegments;
	}
}
