package com.zlate87.sample_transport_app.feature.routing.service;

import android.content.Context;

import com.zlate87.sample_transport_app.base.service.TimeHelperService;
import com.zlate87.sample_transport_app.feature.routing.model.RouteResponse;
import com.zlate87.sample_transport_app.feature.routing.viewmodel.RouteDetails;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.mockito.Mockito.mock;

/**
 * Test class for {@code ViewModelMappingService}.
 */
public class ViewModelMappingServiceTest {

	private ViewModelMappingService viewModelMappingService;

	@Before
	public void setUp() {
		TimeHelperService timeHelperService = mock(TimeHelperService.class);
		Context context = mock(Context.class);
		viewModelMappingService = new ViewModelMappingService(timeHelperService, context);
	}

	@Test
	public void shouldMapToViewModel() {
		// given
		RouteResponse routeResponse = prepareRouteResponse();

		// when
		List<RouteDetails> routeDetailsList = viewModelMappingService.map(routeResponse);

		// then
		assertRouteDetailsList(routeDetailsList);
	}

	private void assertRouteDetailsList(List<RouteDetails> routeDetailsList) {
		// TODO: 1/1/2016 implement
	}

	private RouteResponse prepareRouteResponse() {
		// TODO: 1/1/2016 implement
		return null;
	}

}