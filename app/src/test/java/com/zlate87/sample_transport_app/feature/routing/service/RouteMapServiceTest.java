package com.zlate87.sample_transport_app.feature.routing.service;

import android.graphics.Color;

import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.PolylineOptions;
import com.zlate87.sample_transport_app.TestApp;
import com.zlate87.sample_transport_app.TestBuildConfig;
import com.zlate87.sample_transport_app.feature.routing.viewmodel.PolylineData;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Test class for {@code RouteMapService}.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = TestBuildConfig.class, sdk = 21, application = TestApp.class)
public class RouteMapServiceTest {

	private RouteMapService routeMapService;

	@Before
	public void setUp() {
		routeMapService = new RouteMapService();
	}

	@Test
	public void shouldGetPolylineOptions() {
		// given
		PolylineData polylineData = new PolylineData();
		polylineData.setEncodedValue("uvr_I{yxpABuAFcAp@yHvAwNr@iGPwAh@a@jAg@");
		polylineData.setColor("#b1becc");
		LatLngBounds.Builder latLngBoundsBuilder = new LatLngBounds.Builder();

		// when
		PolylineOptions polylineOptions = routeMapService.getPolylineOptions(latLngBoundsBuilder, polylineData);

		// then
		assertPolylineOptions(polylineOptions);
		assertLatLngBounds(latLngBoundsBuilder);

	}

	public void assertPolylineOptions(PolylineOptions polylineOptions) {
		assertThat(polylineOptions.getColor(), is(Color.parseColor("#b1becc")));
		assertThat(polylineOptions.getPoints().size(), is(9));
		assertThat(polylineOptions.getPoints().get(0).latitude, is(52.52987));
		assertThat(polylineOptions.getPoints().get(0).longitude, is(13.403340000000002));
		assertThat(polylineOptions.getPoints().get(3).latitude, is(52.529560000000004));
		assertThat(polylineOptions.getPoints().get(3).longitude, is(13.40568));
		assertThat(polylineOptions.getPoints().get(7).latitude, is(52.528560000000006));
		assertThat(polylineOptions.getPoints().get(7).longitude, is(13.410140000000002));
	}

	public void assertLatLngBounds(LatLngBounds.Builder latLngBoundsBuilder) {
		LatLngBounds latLngBounds = latLngBoundsBuilder.build();
		assertThat(latLngBounds.getCenter().latitude, is(52.529025000000004));
		assertThat(latLngBounds.getCenter().longitude, is(13.406840000000003));
	}

}