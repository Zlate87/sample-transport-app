package com.zlate87.sample_transport_app.base.injection;

import com.zlate87.sample_transport_app.MainActivity;

/**
 * Dagger component interface.
 */
public interface BaseComponent {

	/**
	 * Dagger 2 inject method for a given target.
	 *
	 * @param target the target
	 */
	void inject(MainActivity target);
}
