package com.zlate87.sample_transport_app.base.injection;

import android.content.Context;

import com.zlate87.sample_transport_app.base.App;
import com.zlate87.sample_transport_app.base.service.FileReaderService;
import com.zlate87.sample_transport_app.feature.routing.service.DummyRoutingService;
import com.zlate87.sample_transport_app.feature.routing.service.RoutingService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Dagger 2 module.
 */
@Module(includes = BaseModule.class)
public class DummyDataModule {

	private final App app;

	/**
	 * Constructor.
	 *
	 * @param app the application context
	 */
	public DummyDataModule(App app) {
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
	 * @return the provided {@code FileReaderService}
	 */
	@Provides
	@Singleton
	public FileReaderService provideFileReaderService() {
		return new FileReaderService(app);
	}

	/**
	 * Dagger 2 provider method.
	 *
	 * @param fileReaderService the fileReaderService to be used
	 * @return the provided {@code DummyRoutingService}
	 */
	@Provides
	@Singleton
	public RoutingService provideRoutingService(FileReaderService fileReaderService) {
		return new DummyRoutingService(fileReaderService);
	}
}
