package com.zlate87.sample_transport_app;

import com.zlate87.sample_transport_app.base.App;
import com.zlate87.sample_transport_app.base.injection.BaseComponent;

/**
 * Test Application class.
 */
public class TestApp extends App {

	@Override
	protected void setupJodaTime() {
		// no setup of joda-time for the robolectric tests
	}

	@Override
	protected BaseComponent initializeComponent() {
		return null;
	}
}
