package com.zlate87.sample_transport_app.feature.routing.preview.controller;

import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import com.zlate87.sample_transport_app.AbstractActivityTest;
import com.zlate87.sample_transport_app.R;
import com.zlate87.sample_transport_app.feature.routing.model.RouteResponse;
import com.zlate87.sample_transport_app.feature.routing.model.RouteValidationResult;
import com.zlate87.sample_transport_app.feature.routing.service.ValidationService;
import com.zlate87.sample_transport_app.feature.routing.service.ViewModelMappingService;
import com.zlate87.sample_transport_app.feature.routing.viewmodel.RouteDetails;
import com.zlate87.sample_transport_app.feature.routing.viewmodel.RoutePreview;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.zlate87.sample_transport_app.CustomMatchers.dynamicView;
import static com.zlate87.sample_transport_app.CustomMatchers.viewAtPositionInRecyclerView;
import static com.zlate87.sample_transport_app.CustomMatchers.withDrawable;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;

/**
 * Espresso test class for {@code RoutesPreviewActivity}.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class RoutesPreviewActivityTest extends AbstractActivityTest {

	@Inject
	protected ValidationService validationService;

	@Inject
	protected ViewModelMappingService modelMappingService;

	@Rule
	public ActivityTestRule<RoutesPreviewActivity> rule = new ActivityTestRule<>(
					RoutesPreviewActivity.class, true, false);

	@Before
	public void setUp() {
		getTestComponent().inject(this);
	}

	@Test
	public void userWantsToSeeUserFriendlyErrorMessageForInvalidRoute() {
		// prepare and start activity
		mockValidationServiceForInvalidRoute();
		launchActivity();

		// check that the error message is displayed to the user
		onView(withId(R.id.messageTextView)).check(matches(isDisplayed()));
		onView(withId(R.id.messageTextView)).check(matches(withText("Sorry, but we did not find any route for you :(.")));
	}

	@Test
	public void userWantsToSeeListOfRoutes() {
		// prepare and start activity
		lunchActivityWithValidData();


		// check that the list is visible
		onView(withId(R.id.routesRecyclerView)).check(matches(isDisplayed()));


		// check the values from the first item in the list
		onView(viewAtPositionInRecyclerView(R.id.routesRecyclerView, 0, R.id.type))
						.check(matches(withText("Public Transport")));
		onView(viewAtPositionInRecyclerView(R.id.routesRecyclerView, 0, R.id.openMap))
						.check(matches(withDrawable(android.R.drawable.ic_dialog_map)));
		onView(viewAtPositionInRecyclerView(R.id.routesRecyclerView, 0, R.id.price))
						.check(matches(withText("EUR 3.5")));
		onView(viewAtPositionInRecyclerView(R.id.routesRecyclerView, 0, R.id.time))
						.check(matches(withText("15:15 -> 15:30")));
		onView(viewAtPositionInRecyclerView(R.id.routesRecyclerView, 0, R.id.duration))
						.check(matches(withText("15 min")));
		onView(dynamicView(R.id.routesRecyclerView, 0, R.id.iconsLinearLayout, 0))
						.check(matches(withDrawable(R.mipmap.walking)));
		onView(dynamicView(R.id.routesRecyclerView, 0, R.id.iconsLinearLayout, 1))
						.check(matches(withDrawable(R.mipmap.bus)));
		onView(dynamicView(R.id.routesRecyclerView, 0, R.id.iconsTextLinearLayout, 0))
						.check(matches(withText("")));
		onView(dynamicView(R.id.routesRecyclerView, 0, R.id.iconsTextLinearLayout, 1))
						.check(matches(withText("U2")));
	}

	@Test
	public void userWantsToNavigateToRouteDetailsScreenByClickingOnAnElementInTheList() {
		// prepare and start activity
		lunchActivityWithValidData();

		// click on the first item
		onView(viewAtPositionInRecyclerView(R.id.routesRecyclerView, 0, R.id.type)).perform(click());

		// check that the route preview view (the one available only on the route details activity) is displayed
		onView(withId(R.id.routePreview)).check(matches(isDisplayed()));
	}

	@Test
	public void userWantsToNavigateToRoutMapScreenByClickingOnTheMapIconOnAnElementInTheList() {
		// prepare and start activity (use mock route validationService)
		lunchActivityWithValidData();

		// click on the map icon on the first item
		onView(viewAtPositionInRecyclerView(R.id.routesRecyclerView, 0, R.id.openMap)).perform(click());

		// check that the route map view (the one available only on the route map activity) is displayed
		onView(withId(R.id.map)).check(matches(isDisplayed()));
	}

	private List<RouteDetails> prepareRouteDetailsList() {
		List<RouteDetails> routeDetailsList = new ArrayList<>();
		RouteDetails routeDetails = new RouteDetails();
		RoutePreview routePreview = prepareRoutePreview();
		routeDetails.setRoutePreview(routePreview);
		routeDetailsList.add(routeDetails);
		routeDetailsList.add(routeDetails);
		return routeDetailsList;
	}

	private void mockValidationServiceForInvalidRoute() {
		int messageStringId = R.string.routing_validationMessage_noValidRoutesFound;
		RouteValidationResult invalidRouteValidationResult = new RouteValidationResult(false, messageStringId);
		mockValidationService(invalidRouteValidationResult);
	}

	private void mockValidationServiceForValidRoute() {
		RouteValidationResult validRouteValidationResult = new RouteValidationResult(true);
		mockValidationService(validRouteValidationResult);
	}

	private void mockValidationService(RouteValidationResult routeValidationResult) {
		given(validationService.validate(any(RouteResponse.class))).willReturn(routeValidationResult);
	}

	private void launchActivity() {
		Context targetContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
		Intent result = new Intent(targetContext, RoutesPreviewActivity.class);
		result.putExtra(RoutesPreviewActivity.ROUTE_RESPONSE_INTENT_EXTRA_KEY, new RouteResponse());
		rule.launchActivity(result);
	}

	private void lunchActivityWithValidData() {
		mockValidationServiceForValidRoute();
		given(modelMappingService.map(any(RouteResponse.class))).willReturn(prepareRouteDetailsList());
		launchActivity();
	}



}