package com.zlate87.sample_transport_app.feature.routing.map.controller;

import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import com.google.android.gms.maps.GoogleMap;
import com.zlate87.sample_transport_app.AbstractActivityTest;
import com.zlate87.sample_transport_app.R;
import com.zlate87.sample_transport_app.feature.routing.service.RouteMapService;
import com.zlate87.sample_transport_app.feature.routing.viewmodel.RouteMapData;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Espresso test class for {@code RouteMapActivity}.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class RouteMapActivityTest extends AbstractActivityTest {

	@Inject
	RouteMapService routeMapService;

	@Rule
	public ActivityTestRule<RouteMapActivity> rule = new ActivityTestRule<>(RouteMapActivity.class, true, false);

	@Before
	public void setUp() {
		getTestComponent().inject(this);
	}

	@Test
	public void userWantsToSeeTheRouteMap() {
		// prepare and start activity (use mock route validationService)
		launchActivity();

		// check that the map is displayed
		onView(withId(R.id.map)).check(matches(isDisplayed()));

		// verify that the service was called to add the route to the map
		verify(routeMapService, times(1)).addRouteToMap(any(GoogleMap.class), any(RouteMapData.class));
	}

	private void launchActivity() {
		Context targetContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
		Intent result = new Intent(targetContext, RouteMapActivity.class);
		result.putExtra(RouteMapActivity.ROUTE_MAP_INFO_INTENT_EXTRA_KEY, new RouteMapData());
		rule.launchActivity(result);
	}

}