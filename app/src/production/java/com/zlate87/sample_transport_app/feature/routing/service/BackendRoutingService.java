package com.zlate87.sample_transport_app.feature.routing.service;

import com.zlate87.sample_transport_app.feature.routing.model.RouteQueryParameters;
import com.zlate87.sample_transport_app.feature.routing.model.RouteResponse;

/**
 * A routing service that will get routing JSON data from a backend.
 */
public class BackendRoutingService extends AbstractJsonRoutingServer {

	@Override
	public RouteResponse route(RouteQueryParameters routeQueryParameters) {
		throw new RuntimeException("Not implemented");
	}
}
