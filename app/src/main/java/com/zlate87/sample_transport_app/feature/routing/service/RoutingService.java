package com.zlate87.sample_transport_app.feature.routing.service;

import com.zlate87.sample_transport_app.feature.routing.model.RouteQueryParameters;
import com.zlate87.sample_transport_app.feature.routing.model.RouteResponse;

/**
 * Interface for specifying a routing service.
 */
public interface RoutingService {

	/**
	 * Method responsible for providing a {@code RouteResponse} for a given {@code RouteQueryParameters}.
	 *
	 * @param routeQueryParameters the query parameters for the route
	 * @return the routing options for the given route query parameters
	 */
	RouteResponse route(RouteQueryParameters routeQueryParameters);
}
