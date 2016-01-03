package com.zlate87.sample_transport_app.feature.routing.viewmodel;

import java.io.Serializable;

/**
 * View model cass for displaying route details.
 */
public class RouteDetails implements Serializable {

	private RoutePreview routePreview;

	// TODO: 1/1/2016 add additional details

	public RoutePreview getRoutePreview() {
		return routePreview;
	}

	public void setRoutePreview(RoutePreview routePreview) {
		this.routePreview = routePreview;
	}
}
