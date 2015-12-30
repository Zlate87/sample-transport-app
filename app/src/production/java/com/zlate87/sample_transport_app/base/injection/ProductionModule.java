package com.zlate87.sample_transport_app.base.injection;

import com.zlate87.sample_transport_app.feature.routing.service.BackendRoutingService;
import com.zlate87.sample_transport_app.feature.routing.service.RoutingService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Dagger 2 module.
 */
@Module
public class ProductionModule {

	/**
	 * Dagger 2 provider method.
	 *
	 * @return the provided {@code DummyRoutingService}
	 */
	@Provides
	@Singleton
	public RoutingService provideRoutingService() {
		return new BackendRoutingService();
	}
}
