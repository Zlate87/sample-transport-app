package com.zlate87.sample_transport_app.feature.routing.service;

import com.zlate87.sample_transport_app.R;
import com.zlate87.sample_transport_app.feature.routing.model.Route;
import com.zlate87.sample_transport_app.feature.routing.model.RouteResponse;
import com.zlate87.sample_transport_app.feature.routing.model.RouteValidationResult;

import org.junit.Test;

import java.util.ArrayList;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Test class for {@code ValidationService}.
 */
public class ValidationServiceTest {

	private ValidationService validationService = new ValidationService();

	@Test
	public void shouldValidateNullRouteResponse() {
		// given

		// when
		RouteValidationResult routeValidationResult = validationService.validate(null);

		// then
		assertThat(routeValidationResult.isValid(), is(false));
		assertThat(routeValidationResult.getMessageStringId(), is(R.string.routing_validationMessage_noValidRoutesFound));
	}

	@Test
	public void shouldValidateEmptyRouteResponse() {
		// given
		RouteResponse routeResponse = new RouteResponse();
		routeResponse.setRoutes(null);

		// when
		RouteValidationResult routeValidationResult = validationService.validate(routeResponse);

		// then
		assertThat(routeValidationResult.isValid(), is(false));
		assertThat(routeValidationResult.getMessageStringId(), is(R.string.routing_validationMessage_noValidRoutesFound));
	}

	@Test
	public void shouldValidateRouteResponseWithNoRoutes() {
		// given
		RouteResponse routeResponse = new RouteResponse();
		routeResponse.setRoutes(new ArrayList<Route>());

		// when
		RouteValidationResult routeValidationResult = validationService.validate(routeResponse);

		// then
		assertThat(routeValidationResult.isValid(), is(false));
		assertThat(routeValidationResult.getMessageStringId(), is(R.string.routing_validationMessage_noValidRoutesFound));
	}

	@Test
	public void shouldValidateValidRouteResponse() {
		// given
		RouteResponse routeResponse = new RouteResponse();
		ArrayList<Route> routes = new ArrayList<>();
		routes.add(new Route());
		routeResponse.setRoutes(routes);

		// when
		RouteValidationResult routeValidationResult = validationService.validate(routeResponse);

		// then
		assertThat(routeValidationResult.isValid(), is(true));
		assertThat(routeValidationResult.getMessageStringId(), is(0));
	}

}