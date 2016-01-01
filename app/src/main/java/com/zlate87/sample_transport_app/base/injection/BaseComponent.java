package com.zlate87.sample_transport_app.base.injection;

import com.zlate87.sample_transport_app.feature.routing.controller.LoadRouteActivity;
import com.zlate87.sample_transport_app.feature.routing.controller.RoutesListActivity;

/**
 * Dagger component interface.
 */
public interface BaseComponent {

	/**
	 * Dagger 2 inject method for a given target.
	 *
	 * @param target the target
	 */
	void inject(LoadRouteActivity target);

	/**
	 * Dagger 2 inject method for a given target.
	 *
	 * @param target the target
	 */
	void inject(RoutesListActivity target);
}
