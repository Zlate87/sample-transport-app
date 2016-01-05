package com.zlate87.sample_transport_app;

import com.zlate87.sample_transport_app.base.service.TimeHelperService;
import com.zlate87.sample_transport_app.feature.routing.service.AsyncRoutingService;
import com.zlate87.sample_transport_app.feature.routing.service.RouteMapService;
import com.zlate87.sample_transport_app.feature.routing.service.ValidationService;
import com.zlate87.sample_transport_app.feature.routing.service.ViewModelMappingService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

import static org.mockito.Mockito.mock;

/**
 * OffersModule that provides mocked OffersService.
 */
@Module
public class MockTestModule {

	/**
	 * Dagger 2 provider method.
	 *
	 * @return the provided {@code ValidationService}
	 */
	@Provides
	@Singleton
	public ValidationService provideValidationService() {
		return mock(ValidationService.class);
	}

	/**
	 * Dagger 2 provider method.
	 *
	 * @return the provided {@code AsyncRoutingService}
	 */
	@Provides
	@Singleton
	public AsyncRoutingService provideRoutingService() {
		return mock(AsyncRoutingService.class);
	}

	/**
	 * Dagger 2 provider method.
	 *
	 * @return the provided {@code ViewModelMappingService}
	 */
	@Provides
	@Singleton
	public ViewModelMappingService provideViewModelMappingService() {
		return mock(ViewModelMappingService.class);
	}

	/**
	 * Dagger 2 provider method.
	 *
	 * @return the provided {@code TimeHelperService}
	 */
	@Provides
	@Singleton
	public TimeHelperService provideTimeHelperService() {
		return new TimeHelperService();
	}

	/**
	 * Dagger 2 provider method.
	 *
	 * @return the provided {@code RouteMapService}
	 */
	@Provides
	@Singleton
	public RouteMapService providePolylineService() {
		return mock(RouteMapService.class);
	}
}
