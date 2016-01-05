package com.zlate87.sample_transport_app;

import com.zlate87.sample_transport_app.base.injection.BaseComponent;
import com.zlate87.sample_transport_app.feature.routing.map.controller.RouteMapActivityTest;
import com.zlate87.sample_transport_app.feature.routing.preview.controller.RoutesPreviewActivityTest;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Dagger component for the tests containing mock modules.
 */
@Singleton
@Component(modules = MockTestModule.class)
public interface TestComponent extends BaseComponent {

	/**
	 * Dagger 2 inject method for a given target.
	 *
	 * @param target the target
	 */
	void inject(RoutesPreviewActivityTest target);

	/**
	 * Dagger 2 inject method for a given target.
	 *
	 * @param target the target
	 */
	void inject(RouteMapActivityTest target);
}
