package com.zlate87.sample_transport_app.base.injection;

import com.zlate87.sample_transport_app.feature.routing.service.AsyncRoutingService;
import com.zlate87.sample_transport_app.feature.routing.service.RoutingService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Dagger 2 module.
 */
@Module
public class BaseModule {

	/**
	 * Dagger 2 provider method.
	 *
	 * @param routingService the routingService
	 * @return the provided {@code AsyncRoutingService}
	 */
	@Provides
	@Singleton
	public AsyncRoutingService provideRoutingService(RoutingService routingService) {
		return new AsyncRoutingService(routingService);
	}
}
