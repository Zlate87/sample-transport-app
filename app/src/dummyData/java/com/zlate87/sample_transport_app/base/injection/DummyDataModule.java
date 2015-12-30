package com.zlate87.sample_transport_app.base.injection;

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
@Module
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
