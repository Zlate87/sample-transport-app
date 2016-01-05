package com.zlate87.sample_transport_app.feature.routing.details.controller;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import com.zlate87.sample_transport_app.AbstractActivityTest;
import com.zlate87.sample_transport_app.R;
import com.zlate87.sample_transport_app.feature.routing.viewmodel.RouteDetails;
import com.zlate87.sample_transport_app.feature.routing.viewmodel.RouteSegment;
import com.zlate87.sample_transport_app.feature.routing.viewmodel.SegmentStop;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.zlate87.sample_transport_app.CustomMatchers.dynamicView;
import static com.zlate87.sample_transport_app.CustomMatchers.viewAtPositionInRecyclerView;
import static org.hamcrest.core.IsNot.not;

/**
 * Espresso test class for {@code RouteDetailsActivity}.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class RouteDetailsActivityTest extends AbstractActivityTest {

	@Rule
	public ActivityTestRule<RouteDetailsActivity> rule = new ActivityTestRule<>(RouteDetailsActivity.class, true, false);

	@Before
	public void setUp() {
		getTestComponent().inject(this);
	}

	@Test
	public void userWantsToSeeRouteDetails() {
		// prepare and start activity
		launchActivity();

		// check that the preview view is visible
		// no fields assertion since it is the same component as in the RoutePreviewActivity
		onView(withId(R.id.routePreview)).check(matches(isDisplayed()));

		// check the always visible values from the first item in the list of segments
		onView(viewAtPositionInRecyclerView(R.id.routeSegmentsRecyclerView, 0, R.id.segmentFirstStopTime))
						.check(matches(withText("13:38")));
		onView(viewAtPositionInRecyclerView(R.id.routeSegmentsRecyclerView, 0, R.id.segmentFirstStopName))
						.check(matches(withText("U Rosa-Luxemburg-Platz")));
		onView(viewAtPositionInRecyclerView(R.id.routeSegmentsRecyclerView, 0, R.id.segmentTravelMode))
						.check(matches(withText("subway")));
		onView(viewAtPositionInRecyclerView(R.id.routeSegmentsRecyclerView, 0, R.id.segmentName))
						.check(matches(withText("U2")));
		onView(viewAtPositionInRecyclerView(R.id.routeSegmentsRecyclerView, 0, R.id.segmentDescription))
						.check(matches(withText("S+U Potsdamer Platz")));
		onView(viewAtPositionInRecyclerView(R.id.routeSegmentsRecyclerView, 0, R.id.segmentDuration))
						.check(matches(withText("7 stops, 13 min")));
	}

	@Test
	public void userWantsToShowNadHideSegmentStops() {
		// prepare and start activity
		launchActivity();

		// check that the segment stops are hidden
		onView(viewAtPositionInRecyclerView(R.id.routeSegmentsRecyclerView, 0, R.id.stopsTimesLinearLayout))
						.check(matches(not(isDisplayed())));
		onView(viewAtPositionInRecyclerView(R.id.routeSegmentsRecyclerView, 0, R.id.stopsNamesLinearLayout))
						.check(matches(not(isDisplayed())));

		// click on the first segment so the stops are displayed
		onView(viewAtPositionInRecyclerView(R.id.routeSegmentsRecyclerView, 0, R.id.segmentFirstStopTime)).perform(click());

		// check that the segment stops are not hidden and have valid values
		onView(viewAtPositionInRecyclerView(R.id.routeSegmentsRecyclerView, 0, R.id.stopsTimesLinearLayout))
						.check(matches(isDisplayed()));
		onView(viewAtPositionInRecyclerView(R.id.routeSegmentsRecyclerView, 0, R.id.stopsNamesLinearLayout))
						.check(matches(isDisplayed()));
		onView(dynamicView(R.id.routeSegmentsRecyclerView, 0, R.id.stopsTimesLinearLayout, 0))
						.check(matches(withText("13:40")));
		onView(dynamicView(R.id.routeSegmentsRecyclerView, 0, R.id.stopsTimesLinearLayout, 1))
						.check(matches(withText("13:42")));
		onView(dynamicView(R.id.routeSegmentsRecyclerView, 0, R.id.stopsNamesLinearLayout, 0))
						.check(matches(withText("S+U Alexanderplatz")));
		onView(dynamicView(R.id.routeSegmentsRecyclerView, 0, R.id.stopsNamesLinearLayout, 1))
						.check(matches(withText("U Klosterstr.")));

		// click on the first segment so the stops not are hidden
		onView(viewAtPositionInRecyclerView(R.id.routeSegmentsRecyclerView, 0, R.id.segmentFirstStopTime)).perform(click());

		// check that the segment stops are hidden
		onView(viewAtPositionInRecyclerView(R.id.routeSegmentsRecyclerView, 0, R.id.stopsTimesLinearLayout))
						.check(matches(not(isDisplayed())));
		onView(viewAtPositionInRecyclerView(R.id.routeSegmentsRecyclerView, 0, R.id.stopsNamesLinearLayout))
						.check(matches(not(isDisplayed())));
	}

	@Test
	public void userWantsToNavigateToRoutMapScreenByClickingOnTheMapIcon() {
		// prepare and start activity
		launchActivity();

		// click on the map icon on the route preview
		onView(withId(R.id.openMap)).perform(click());

		// check that the route map view (the one available only on the route map activity) is displayed
		onView(withId(R.id.map)).check(matches(isDisplayed()));
	}

	private void launchActivity() {
		Context targetContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
		Intent result = new Intent(targetContext, RouteDetailsActivity.class);
		result.putExtra(RouteDetailsActivity.ROUTE_DETAILS_INTENT_EXTRA_KEY, prepareRouteDetails());
		rule.launchActivity(result);
	}

	@NonNull
	private RouteDetails prepareRouteDetails() {
		RouteDetails routeDetails = new RouteDetails();
		routeDetails.setRoutePreview(prepareRoutePreview());
		routeDetails.getRouteSegments().addAll(prepareRouteSegments());
		return routeDetails;
	}

	private List<RouteSegment> prepareRouteSegments() {
		List<RouteSegment> segments = new ArrayList<>();
		RouteSegment segment = new RouteSegment();
		segment.setName("U2");
		segment.setDescription("S+U Potsdamer Platz");
		segment.setTravelMode("subway");
		segment.setDuration("7 stops, 13 min");
		segment.setColor("#d64820");
		segment.getSegmentStops().addAll(prepareSegmentStops());
		segments.add(segment);
		segments.add(segment);
		return segments;
	}

	@NonNull
	private List<SegmentStop> prepareSegmentStops() {
		ArrayList<SegmentStop> segmentStops = new ArrayList<>();
		SegmentStop stop = new SegmentStop();
		stop.setTime("13:38");
		stop.setName("U Rosa-Luxemburg-Platz");
		segmentStops.add(stop);

		stop = new SegmentStop();
		stop.setTime("13:40");
		stop.setName("S+U Alexanderplatz");
		segmentStops.add(stop);

		stop = new SegmentStop();
		stop.setTime("13:42");
		stop.setName("U Klosterstr.");
		segmentStops.add(stop);
		return segmentStops;
	}

}