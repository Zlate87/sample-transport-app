package com.zlate87.sample_transport_app.feature.routing.preview.controller;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.RecyclerView;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.widget.LinearLayout;

import com.zlate87.sample_transport_app.AbstractActivityTest;
import com.zlate87.sample_transport_app.R;
import com.zlate87.sample_transport_app.feature.routing.model.RouteResponse;
import com.zlate87.sample_transport_app.feature.routing.model.RouteValidationResult;
import com.zlate87.sample_transport_app.feature.routing.service.ValidationService;
import com.zlate87.sample_transport_app.feature.routing.service.ViewModelMappingService;
import com.zlate87.sample_transport_app.feature.routing.viewmodel.RouteDetails;
import com.zlate87.sample_transport_app.feature.routing.viewmodel.RoutePreview;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
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
		onView(iconAttributeView(R.id.routesRecyclerView, 0, R.id.iconsLinearLayout, 0))
						.check(matches(withDrawable(R.mipmap.walking)));
		onView(iconAttributeView(R.id.routesRecyclerView, 0, R.id.iconsLinearLayout, 1))
						.check(matches(withDrawable(R.mipmap.bus)));
		onView(iconAttributeView(R.id.routesRecyclerView, 0, R.id.iconsTextLinearLayout, 0))
						.check(matches(withText("")));
		onView(iconAttributeView(R.id.routesRecyclerView, 0, R.id.iconsTextLinearLayout, 1))
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


	/**
	 * Matches icon attribute view at route preview view in a recycler view for a given:
	 *
	 * @param recyclerViewId the id of the recycler view
	 * @param viewPosition   the position index of the route preview view in the recycler view
	 * @param iconsLayout    the id of the layout for the icon attributes
	 * @param iconPosition   the icon position inside the icons layout
	 * @return the matcher
	 */
	public static Matcher<View> iconAttributeView(final int recyclerViewId,
																								 final int viewPosition,
																								 final int iconsLayout,
																								 final int iconPosition) {
		return new TypeSafeMatcher<View>() {
			Resources resources = null;
			View routePreviewView;

			public void describeTo(Description description) {
				String idDescription = Integer.toString(recyclerViewId);
				if (resources != null) {
					try {
						idDescription = this.resources.getResourceName(recyclerViewId);
					} catch (Resources.NotFoundException var4) {
						idDescription = String.format("%d (resource name not found)", recyclerViewId);
					}
				}
				description.appendText("with id: " + idDescription);
			}

			public boolean matchesSafely(View view) {
				resources = view.getResources();

				if (routePreviewView == null) {
					RecyclerView recyclerView = (RecyclerView) view.getRootView().findViewById(recyclerViewId);
					if (recyclerView != null && recyclerView.getId() == recyclerViewId) {
						routePreviewView = recyclerView.getChildAt(viewPosition);
					} else {
						return false;
					}
				}

				LinearLayout iconsLinearLayout = (LinearLayout) routePreviewView.findViewById(iconsLayout);
				View targetView = iconsLinearLayout.getChildAt(iconPosition);
				return view == targetView;
			}
		};
	}

}