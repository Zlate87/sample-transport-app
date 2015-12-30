package com.zlate87.sample_transport_app.feature.routing.service;

import com.zlate87.sample_transport_app.BuildConfig;
import com.zlate87.sample_transport_app.TestHelper;
import com.zlate87.sample_transport_app.base.service.FileReaderService;
import com.zlate87.sample_transport_app.feature.routing.model.RouteResponse;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

/**
 * Test class for {@code DummyRoutingService}.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
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
		assertThat(route.getRoutes().size(), is(9));
		// TODO: 12/30/2015 assset the full object
	}
}