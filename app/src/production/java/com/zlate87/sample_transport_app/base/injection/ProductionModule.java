package com.zlate87.sample_transport_app.base.injection;

import android.content.Context;

import com.zlate87.sample_transport_app.base.App;
import com.zlate87.sample_transport_app.feature.routing.service.BackendRoutingService;
import com.zlate87.sample_transport_app.feature.routing.service.RoutingService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Dagger 2 module.
 */
@Module(includes = BaseModule.class)
public class ProductionModule {

	private final App app;

	/**
	 * Constructor.
	 *
	 * @param app the application context
	 */
	public ProductionModule(App app) {
		this.app = app;
	}

	/**
	 * Allow the application context to be injected but require that it be annotated with
	 */
	@Provides @Singleton
	Context provideApplicationContext() {
		return app;
	}

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
