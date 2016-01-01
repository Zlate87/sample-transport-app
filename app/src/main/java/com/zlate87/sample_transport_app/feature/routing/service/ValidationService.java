package com.zlate87.sample_transport_app.feature.routing.service;

import com.zlate87.sample_transport_app.R;
import com.zlate87.sample_transport_app.feature.routing.model.RouteResponse;
import com.zlate87.sample_transport_app.feature.routing.model.RouteValidationResult;

/**
 * Service class responsible for the validation of the routes.
 */
public class ValidationService {

	/**
	 * Validates a {@code RouteResponse}.
	 * @param routeResponse the route response to validate.
	 * @return {@code RouteValidationResult}
	 */
	public RouteValidationResult validate(RouteResponse routeResponse) {
		if (routeResponse == null) {
			return new RouteValidationResult(false, R.string.routing_validationMessage_noValidRoutesFound);
		}
		if (routeResponse.getRoutes() == null) {
			return new RouteValidationResult(false, R.string.routing_validationMessage_noValidRoutesFound);
		}
		if (routeResponse.getRoutes().isEmpty()) {
			return new RouteValidationResult(false, R.string.routing_validationMessage_noValidRoutesFound);
		}
		return new RouteValidationResult(true);
	}
}
