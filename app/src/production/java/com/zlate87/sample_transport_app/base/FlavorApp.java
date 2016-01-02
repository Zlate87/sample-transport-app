package com.zlate87.sample_transport_app.base;

import com.zlate87.sample_transport_app.base.injection.BaseComponent;
import com.zlate87.sample_transport_app.base.injection.DaggerProductionDataComponent;
import com.zlate87.sample_transport_app.base.injection.ProductionModule;

/**
 * App class responsible for implementation specific for this flavor.
 */
public class FlavorApp extends App {

	@Override
	protected BaseComponent initializeComponent() {
		return DaggerProductionDataComponent.builder()
						.productionModule(new ProductionModule(this))
						.build();
	}
}
