package com.zlate87.sample_transport_app;

import android.app.Instrumentation;
import android.support.test.InstrumentationRegistry;

import com.zlate87.sample_transport_app.base.App;

/**
 * Abstract test class for testing activities.
 */
public class AbstractActivityTest {

	/**
	 * @return the test component for injections.
	 */
	protected TestComponent getTestComponent() {
		Instrumentation instrumentation = InstrumentationRegistry.getInstrumentation();
		App app = (App) instrumentation.getTargetContext().getApplicationContext();
		MockTestModule mockTestModule = new MockTestModule();
		// to generate DaggerTestComponent just run the test
		TestComponent component = DaggerTestComponent.builder().mockTestModule(mockTestModule).build();
		app.setComponent(component);
		return component;
	}
}
