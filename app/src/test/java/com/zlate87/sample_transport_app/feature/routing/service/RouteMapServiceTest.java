package com.zlate87.sample_transport_app.feature.routing.service;

import android.graphics.Color;
import android.support.annotation.NonNull;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.zlate87.sample_transport_app.TestApp;
import com.zlate87.sample_transport_app.TestBuildConfig;
import com.zlate87.sample_transport_app.feature.routing.viewmodel.PolylineData;
import com.zlate87.sample_transport_app.feature.routing.viewmodel.Position;
import com.zlate87.sample_transport_app.feature.routing.viewmodel.RouteMapData;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.rule.PowerMockRule;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

/**
 * Test class for {@code RouteMapService}.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = TestBuildConfig.class, sdk = 21, application = TestApp.class)
@PowerMockIgnore({ "org.mockito.*", "org.robolectric.*", "android.*" })
@PrepareForTest({GoogleMap.class, CameraUpdateFactory.class, CameraUpdate.class})
public class RouteMapServiceTest {

	@Rule
	public PowerMockRule rule = new PowerMockRule();

	private RouteMapService routeMapService;
	private GoogleMap googleMap;
	private CameraUpdate cameraUpdate;

	private Answer mapLoadedCallbackAnswer = new Answer() {
		@Override
		public Void answer(InvocationOnMock invocation) throws Throwable {
			GoogleMap.OnMapLoadedCallback callback = (GoogleMap.OnMapLoadedCallback) invocation
							.getArguments()[0];
			callback.onMapLoaded();
			return null;
		}
	};

	private Answer newLatLngBoundsAnswer = new Answer() {
		@Override
		public Void answer(InvocationOnMock invocation) throws Throwable {
			PolylineOptions polylineOptions = (PolylineOptions) invocation.getArguments()[0];
			assertPolylineOptions(polylineOptions);
			return null;
		}
	};

	private Answer addMarkerAnswer = new Answer() {
		@Override
		public Void answer(InvocationOnMock invocation) throws Throwable {
			MarkerOptions markerOptions = (MarkerOptions) invocation.getArguments()[0];
			assertThat(markerOptions.getTitle(), is("Berlin"));
			assertThat(markerOptions.getPosition().latitude, is(52.530227));
			assertThat(markerOptions.getPosition().longitude, is(13.403356));
			return null;
		}
	};

	@Before
	public void setUp() {
		googleMap = PowerMockito.mock(GoogleMap.class);
		cameraUpdate = PowerMockito.mock(CameraUpdate.class);
		PowerMockito.mockStatic(CameraUpdateFactory.class);
		routeMapService = new RouteMapService();
	}

	@Test
	public void shouldGetPolylineOptions() {
		// given
		PolylineData polylineData = preparePolylineData();
		LatLngBounds.Builder latLngBoundsBuilder = new LatLngBounds.Builder();

		// when
		PolylineOptions polylineOptions = routeMapService.getPolylineOptions(latLngBoundsBuilder, polylineData);

		// then
		assertPolylineOptions(polylineOptions);
		assertLatLngBounds(latLngBoundsBuilder);
	}

	@Test
	public void shouldAddMarkerToMap() {
		// given
		Position position = new Position();
		position.setLatitude(52.530227);
		position.setLongitude(13.403356);
		position.setName("Berlin");
		doAnswer(addMarkerAnswer).when(googleMap).addMarker(any(MarkerOptions.class));

		// when
		routeMapService.addMarker(position, googleMap);

		// then
		verify(googleMap, times(1)).addMarker(any(MarkerOptions.class));
	}

	@Test
	public void shouldAddRouteToMap() {
		// given
		RouteMapData routeMapData = prepareRouteMapData();
		doAnswer(mapLoadedCallbackAnswer).when(googleMap).setOnMapLoadedCallback(any(GoogleMap.OnMapLoadedCallback.class));
		doAnswer(newLatLngBoundsAnswer).when(googleMap).addPolyline(any(PolylineOptions.class));
		when(CameraUpdateFactory.newLatLngBounds(any(LatLngBounds.class), anyInt())).thenReturn(cameraUpdate);

		// when
		routeMapService.addRouteToMap(googleMap, routeMapData);

		// then
		verify(googleMap, times(1)).addPolyline(any(PolylineOptions.class));
		verify(googleMap, times(2)).addMarker(any(MarkerOptions.class));
		verify(googleMap, times(1)).animateCamera(any(CameraUpdate.class));
	}

	@NonNull
	public RouteMapData prepareRouteMapData() {
		RouteMapData routeMapData = new RouteMapData();
		Position startPoint = new Position();
		startPoint.setLatitude(52.530227);
		startPoint.setLongitude(13.403356);
		routeMapData.setStartPoint(startPoint);
		Position endPoint = new Position();
		endPoint.setLatitude(52.509067);
		endPoint.setLongitude(13.37798);
		routeMapData.setEndPoint(endPoint);
		routeMapData.getPolylineDataList().add(preparePolylineData());
		return routeMapData;
	}

	@NonNull
	public PolylineData preparePolylineData() {
		PolylineData polylineData = new PolylineData();
		polylineData.setEncodedValue("uvr_I{yxpABuAFcAp@yHvAwNr@iGPwAh@a@jAg@");
		polylineData.setColor("#b1becc");
		return polylineData;
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