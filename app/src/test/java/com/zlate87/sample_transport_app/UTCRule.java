package com.zlate87.sample_transport_app;

import org.joda.time.DateTimeZone;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

/**
 * Test rule for seting constant time zone for tests.
 */
public class UTCRule extends TestWatcher {

	private DateTimeZone origDefault = DateTimeZone.getDefault();

	@Override
	protected void starting(Description description) {
		DateTimeZone.setDefault(DateTimeZone.UTC);
	}

	@Override
	protected void finished(Description description) {
		DateTimeZone.setDefault(origDefault);
	}
}
