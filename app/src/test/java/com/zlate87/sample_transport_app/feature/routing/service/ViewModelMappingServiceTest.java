package com.zlate87.sample_transport_app.feature.routing.service;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.zlate87.sample_transport_app.BuildConfig;
import com.zlate87.sample_transport_app.TestApp;
import com.zlate87.sample_transport_app.TestHelper;
import com.zlate87.sample_transport_app.base.service.TimeHelperService;
import com.zlate87.sample_transport_app.feature.routing.model.RouteResponse;
import com.zlate87.sample_transport_app.feature.routing.viewmodel.RouteDetails;
import com.zlate87.sample_transport_app.feature.routing.viewmodel.RoutePreview;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Test class for {@code ViewModelMappingService}.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21, application = TestApp.class)
public class ViewModelMappingServiceTest {

	private ViewModelMappingService viewModelMappingService;

	@Before
	public void setUp() {
		TimeHelperService timeHelperService = mock(TimeHelperService.class);
		when(timeHelperService.calculateDuration(anyString(), anyString())).thenReturn("1h 14min");
		when(timeHelperService.justTime(anyString())).thenReturn("15:23");
		viewModelMappingService = new ViewModelMappingService(timeHelperService, RuntimeEnvironment.application);
	}

	@Test
	public void shouldMapToViewModel() {
		// given
		RouteResponse routeResponse = prepareRouteResponse();

		// when
		List<RouteDetails> routeDetailsList = viewModelMappingService.map(routeResponse);

		// then
		assertRouteDetailsList(routeDetailsList);
	}

	private void assertRouteDetailsList(List<RouteDetails> routeDetailsList) {
		assertThat(routeDetailsList.size(), is(9));
		RouteDetails routeDetails = routeDetailsList.get(0);
		assertFirstRoutePreview(routeDetails);
	}

	private void assertFirstRoutePreview(RouteDetails routeDetails) {
		RoutePreview routePreview = routeDetails.getRoutePreview();
		assertThat(routePreview.getType(), is("public_transport"));
		assertThat(routePreview.getPrice(), is("EUR 270"));
		assertThat(routePreview.getArriveTime(), is("15:23"));
		assertThat(routePreview.getLeaveTime(), is("15:23"));
		assertThat(routePreview.getDuration(), is("1h 14min"));
		assertThat(routePreview.getIcons().size(), is(2));
		assertThat(routePreview.getIcons().get(0), is("walking"));
		assertThat(routePreview.getIconsColors().get(0), is("#b1becc"));
		assertThat(routePreview.getIconsText().get(0), is(""));
	}

	private RouteResponse prepareRouteResponse() {
		String json = new TestHelper().getTextContentFromResourcesFile("data.json");
		Moshi moshi = new Moshi.Builder().build();
		JsonAdapter<RouteResponse> jsonAdapter = moshi.adapter(RouteResponse.class);
		try {
			return jsonAdapter.fromJson(json);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}