package com.zlate87.sample_transport_app.feature.routing.service;

import com.zlate87.sample_transport_app.BuildConfig;
import com.zlate87.sample_transport_app.TestApp;
import com.zlate87.sample_transport_app.TestHelper;
import com.zlate87.sample_transport_app.base.service.FileReaderService;
import com.zlate87.sample_transport_app.feature.routing.model.Callabike;
import com.zlate87.sample_transport_app.feature.routing.model.Car2go;
import com.zlate87.sample_transport_app.feature.routing.model.Drivenow;
import com.zlate87.sample_transport_app.feature.routing.model.Google;
import com.zlate87.sample_transport_app.feature.routing.model.Nextbike;
import com.zlate87.sample_transport_app.feature.routing.model.Provider_attributes;
import com.zlate87.sample_transport_app.feature.routing.model.Route;
import com.zlate87.sample_transport_app.feature.routing.model.RouteResponse;
import com.zlate87.sample_transport_app.feature.routing.model.Segment;
import com.zlate87.sample_transport_app.feature.routing.model.Stop;
import com.zlate87.sample_transport_app.feature.routing.model.Vbb;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.nullValue;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

/**
 * Test class for {@code DummyRoutingService}.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21, application = TestApp.class)
public class DummyRoutingServiceTest {

	private DummyRoutingService dummyRoutingService;
	private FileReaderService fileReaderService;

	@Before
	public void setUp() {
		fileReaderService = mock(FileReaderService.class);
		dummyRoutingService = new DummyRoutingService(fileReaderService);
	}

	@Test
	public void shouldProvideRouteResponse() {
		// given
		String filePath = "data.json";
		String json = new TestHelper().getTextContentFromResourcesFile(filePath);
		given(fileReaderService.readTextFileFromAssets(filePath)).willReturn(json);

		// when
		RouteResponse route = dummyRoutingService.route(null);

		// then
		List<Route> routes = route.getRoutes();
		assertThat(routes.size(), is(9));
		Route firstRoute = routes.get(0);
		Segment firstSegment = firstRoute.getSegments().get(0);
		Stop firstStop = firstSegment.getStops().get(0);
		Provider_attributes provider_attributes = route.getProvider_attributes();
		Vbb vbb = provider_attributes.getVbb();
		Drivenow drivenow = provider_attributes.getDrivenow();
		Car2go car2go = provider_attributes.getCar2go();
		Google google = provider_attributes.getGoogle();
		Nextbike nextbike = provider_attributes.getNextbike();
		Callabike callabike = provider_attributes.getCallabike();

		assertThat(firstRoute.getType(), is("public_transport"));
		assertThat(firstRoute.getProvider(), is("vbb"));
		assertThat(firstRoute.getSegments().size(), is(2));
		assertThat(firstSegment.getNum_stops(), is(0));
		assertThat(firstSegment.getStops().size(), is(2));
		assertThat(firstStop.getLat(), is(52.530227));
		assertThat(firstStop.getLng(), is(13.403356));
		assertThat(firstStop.getDatetime(), is("2015-04-17T13:30:00+02:00"));
		assertThat(firstSegment.getStops().get(1).getName(), is("U Rosa-Luxemburg-Platz"));
		assertThat(firstStop.getProperties(), is(nullValue()));
		assertThat(firstSegment.getTravel_mode(), is("walking"));
		assertThat(firstRoute.getSegments().get(1).getName(), is("U2"));
		assertThat(firstSegment.getColor(), is("#b1becc"));
		assertThat(firstSegment.getIcon_url(), is("https://d3m2tfu2xpiope.cloudfront.net/vehicles/walking.svg"));
		assertThat(firstSegment.getPolyline(), is("uvr_I{yxpABuAFcAp@yHvAwNr@iGPwAh@a@jAg@"));
		assertThat(firstRoute.getProperties(), is(nullValue()));
		assertThat(firstRoute.getPrice().getCurrency(), is("EUR"));
		assertThat(firstRoute.getPrice().getAmount(), is(270));
		assertThat(vbb.getProvider_icon_url(), is("https://d3m2tfu2xpiope.cloudfront.net/providers/vbb.svg"));
		assertThat(vbb.getDisclaimer(), is("Our data is as live and real-time as possible."));
		assertThat(drivenow.getProvider_icon_url(), is("https://d3m2tfu2xpiope.cloudfront.net/providers/drivenow.svg"));
		assertThat(drivenow.getDisclaimer(), is("Our data is as live and real-time as possible."));
		assertThat(drivenow.getAndroid_package_name(), is("com.dn.drivenow"));
		assertThat(drivenow.getDisplay_name(), is("Drivenow"));
		assertThat(car2go.getProvider_icon_url(), is("https://d3m2tfu2xpiope.cloudfront.net/providers/car2go.svg"));
		assertThat(car2go.getDisclaimer(), is("Our data is as live and real-time as possible."));
		assertThat(car2go.getAndroid_package_name(), is("com.car2go"));
		assertThat(car2go.getDisplay_name(), is("Car2go"));
		assertThat(google.getProvider_icon_url(), is("https://d3m2tfu2xpiope.cloudfront.net/providers/google.svg"));
		assertThat(google.getDisclaimer(), is("Our data is as live and real-time as possible."));
		assertThat(nextbike.getProvider_icon_url(), is("https://d3m2tfu2xpiope.cloudfront.net/providers/nextbike.svg"));
		assertThat(nextbike.getDisclaimer(), is("Our data is as live and real-time as possible."));
		assertThat(nextbike.getAndroid_package_name(), is("de.nextbike"));
		assertThat(nextbike.getDisplay_name(), is("Nextbike"));
		assertThat(callabike.getProvider_icon_url(), is("https://d3m2tfu2xpiope.cloudfront.net/providers/callabike.svg"));
		assertThat(callabike.getDisclaimer(), is("Our data is as live and real-time as possible."));
		assertThat(callabike.getAndroid_package_name(), is("de.bahn.callabike"));
		assertThat(callabike.getDisplay_name(), is("Call a Bike"));
	}
}