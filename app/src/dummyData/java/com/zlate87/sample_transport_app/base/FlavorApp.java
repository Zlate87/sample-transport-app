package com.zlate87.sample_transport_app.base;

import com.zlate87.sample_transport_app.base.injection.BaseComponent;
import com.zlate87.sample_transport_app.base.injection.DaggerDummyDataComponent;
import com.zlate87.sample_transport_app.base.injection.DummyDataModule;

/**
 * App class responsible for implementation specific for this flavor.
 */
public class FlavorApp extends App {

	@Override
	protected BaseComponent initializeComponent() {
		return DaggerDummyDataComponent.builder()
						.dummyDataModule(new DummyDataModule(this))
						.build();
	}
}
