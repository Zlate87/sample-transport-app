package com.zlate87.sample_transport_app;

import android.app.Instrumentation;
import android.support.annotation.NonNull;
import android.support.test.InstrumentationRegistry;

import com.zlate87.sample_transport_app.base.App;
import com.zlate87.sample_transport_app.feature.routing.viewmodel.RoutePreview;

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

	/**
	 * @return {@code RoutePreview} with sum dummy data.
	 */
	@NonNull
	protected RoutePreview prepareRoutePreview() {
		RoutePreview routePreview = new RoutePreview();
		routePreview.setType("Public Transport");
		routePreview.setDuration("15 min");
		routePreview.setLeaveTime("15:15");
		routePreview.setArriveTime("15:30");
		routePreview.setPrice("EUR 3.5");
		routePreview.getIcons().add("walking");
		routePreview.getIcons().add("bus");
		routePreview.getIconsText().add(null);
		routePreview.getIconsText().add("U2");
		routePreview.getIconsColors().add("#00FF00");
		routePreview.getIconsColors().add("#0000FF");
		return routePreview;
	}
}
