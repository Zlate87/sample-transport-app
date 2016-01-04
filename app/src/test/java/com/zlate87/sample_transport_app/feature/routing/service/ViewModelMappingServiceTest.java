package com.zlate87.sample_transport_app.feature.routing.service;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.zlate87.sample_transport_app.TestApp;
import com.zlate87.sample_transport_app.TestBuildConfig;
import com.zlate87.sample_transport_app.TestHelper;
import com.zlate87.sample_transport_app.base.service.TimeHelperService;
import com.zlate87.sample_transport_app.feature.routing.model.RouteResponse;
import com.zlate87.sample_transport_app.feature.routing.viewmodel.PolylineData;
import com.zlate87.sample_transport_app.feature.routing.viewmodel.RouteDetails;
import com.zlate87.sample_transport_app.feature.routing.viewmodel.RouteMapData;
import com.zlate87.sample_transport_app.feature.routing.viewmodel.RoutePreview;
import com.zlate87.sample_transport_app.feature.routing.viewmodel.RouteSegment;

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
import static org.hamcrest.core.IsNull.nullValue;

/**
 * Test class for {@code ViewModelMappingService}.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = TestBuildConfig.class, sdk = 21, application = TestApp.class)
public class ViewModelMappingServiceTest {

	private ViewModelMappingService viewModelMappingService;

	@Before
	public void setUp() {
		TimeHelperService timeHelperService = new TimeHelperService();
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
		assertFirstRoute(routeDetails);
	}

	private void assertFirstRoute(RouteDetails routeDetails) {
		RoutePreview routePreview = routeDetails.getRoutePreview();
		assertFirstRoutePreview(routePreview);
		RouteMapData routeMapData = routeDetails.getRouteMapData();
		assertFirstRouteMapData(routeMapData);
		List<RouteSegment> routeSegments = routeDetails.getRouteSegments();
		assertFirstRouteSegments(routeSegments);
	}

	private void assertFirstRouteSegments(List<RouteSegment> routeSegments) {
		assertThat(routeSegments.size(), is(2));
		RouteSegment firstRouteSegment = routeSegments.get(0);
		assertThat(firstRouteSegment.getName(), is("Unknown address"));
		assertThat(firstRouteSegment.getDescription(), is(nullValue()));
		assertThat(firstRouteSegment.getDuration(), is("8min"));
		assertThat(firstRouteSegment.getColor(), is("#b1becc"));
		assertThat(firstRouteSegment.getTravelMode(), is("Walking"));
		assertThat(firstRouteSegment.getSegmentStops().size(), is(2));
		assertThat(firstRouteSegment.getSegmentStops().get(0).getName(), is("Unknown address"));
		assertThat(firstRouteSegment.getSegmentStops().get(0).getTime(), is("13:30"));
		assertThat(firstRouteSegment.getSegmentStops().get(1).getName(), is("U Rosa-Luxemburg-Platz"));
		RouteSegment secondRouteSegment = routeSegments.get(1);
		assertThat(secondRouteSegment.getName(), is("U2"));
		assertThat(secondRouteSegment.getDuration(), is("7 stops, 13min"));
		assertThat(secondRouteSegment.getDescription(), is("S+U Potsdamer Platz"));
		assertThat(secondRouteSegment.getColor(), is("#d64820"));
		assertThat(secondRouteSegment.getTravelMode(), is("Subway"));
	}

	private void assertFirstRouteMapData(RouteMapData routeMapData) {
		List<PolylineData> polylineDataList = routeMapData.getPolylineDataList();
		PolylineData polylineData = polylineDataList.get(0);
		assertThat(polylineDataList.size(), is(2));
		assertThat(polylineData.getEncodedValue(), is("uvr_I{yxpABuAFcAp@yHvAwNr@iGPwAh@a@jAg@"));
		assertThat(polylineData.getColor(), is("#b1becc"));
	}

	private void assertFirstRoutePreview(RoutePreview routePreview) {
		assertThat(routePreview.getType(), is("Public Transport"));
		assertThat(routePreview.getPrice(), is("EUR 270"));
		assertThat(routePreview.getLeaveTime(), is("13:30"));
		assertThat(routePreview.getArriveTime(), is("13:51"));
		assertThat(routePreview.getDuration(), is("21min"));
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