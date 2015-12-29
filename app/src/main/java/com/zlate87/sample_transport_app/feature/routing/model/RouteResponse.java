
package com.zlate87.sample_transport_app.feature.routing.model;

import java.util.ArrayList;
import java.util.List;

public class RouteResponse {

	private List<Route> routes = new ArrayList<Route>();
	private ProviderAttributes providerAttributes;

	/**
	 * @return The routes
	 */
	public List<Route> getRoutes() {
		return routes;
	}

	/**
	 * @param routes The routes
	 */
	public void setRoutes(List<Route> routes) {
		this.routes = routes;
	}

	/**
	 * @return The providerAttributes
	 */
	public ProviderAttributes getProviderAttributes() {
		return providerAttributes;
	}

	/**
	 * @param providerAttributes The provider_attributes
	 */
	public void setProviderAttributes(ProviderAttributes providerAttributes) {
		this.providerAttributes = providerAttributes;
	}

}
