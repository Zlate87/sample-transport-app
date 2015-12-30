package com.zlate87.sample_transport_app.base.injection;

import com.zlate87.sample_transport_app.MainActivity;

/**
 * Created by Zlatko on 12/30/2015.
 */
public interface BaseComponent {

	/**
	 * Dagger 2 inject method for a given target.
	 *
	 * @param target the target
	 */
	void inject(MainActivity target);
}
