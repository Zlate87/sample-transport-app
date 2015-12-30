package com.zlate87.sample_transport_app.feature.routing.service;

import android.util.Log;

import com.zlate87.sample_transport_app.base.service.FileReaderService;
import com.zlate87.sample_transport_app.feature.routing.model.RouteQueryParameters;
import com.zlate87.sample_transport_app.feature.routing.model.RouteResponse;

import javax.inject.Inject;

/**
 * A dummy implementation of a {@code RoutingService} class.
 * It uses a static JSON file that is pre-bundled with the application to provide the routing data.
 */
public class DummyRoutingService extends AbstractJsonRoutingServer {

	private static final String TAG = DummyRoutingService.class.getSimpleName();

	private static final String PATH_TO_DUMMY_JSON_FILE = "data.json";

	private FileReaderService fileReaderService;

	/**
	 * Constructor.
	 *
	 * @param fileReaderService the fileReaderService
	 */
	@Inject
	public DummyRoutingService(FileReaderService fileReaderService) {
		this.fileReaderService = fileReaderService;
	}

	@Override
	public RouteResponse route(RouteQueryParameters routeQueryParameters) {
		String routeResponseJson = fileReaderService.readTextFileFromAssets(PATH_TO_DUMMY_JSON_FILE);
		Log.d(TAG, String.format("route: retrieved dummy JSON:\n[%s]", routeResponseJson));
		return deserializeJson(routeResponseJson);
	}
}
