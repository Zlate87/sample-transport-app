package com.zlate87.sample_transport_app.feature.routing.service;

import android.content.Context;

import com.zlate87.sample_transport_app.base.helper.FileReaderHelper;
import com.zlate87.sample_transport_app.feature.routing.model.RouteQueryParameters;
import com.zlate87.sample_transport_app.feature.routing.model.RouteResponse;

/**
 * A dummy implementation of a {@code RoutingService} class.
 * It uses a static JSON file that is pre-bundled with the application to provide the routing data.
 */
// TODO: 12/29/2015 add tests
public class DummyRoutingService extends AbstractJsonRoutingServer {

	public static final String PATH_TO_DUMMY_JSON_FILE = "data.json";

	// TODO: 12/29/2015 inject?
	private FileReaderHelper fileReaderHelper;

	/**
	 * Constructor.
	 *
	 * @param context the context
	 */
	public DummyRoutingService(Context context) {
		fileReaderHelper = new FileReaderHelper(context);
	}

	@Override
	public RouteResponse route(RouteQueryParameters routeQueryParameters) {
		// TODO: 12/29/2015 LOG
		String routeResponseJson = fileReaderHelper.readTextFileFromAssets(PATH_TO_DUMMY_JSON_FILE);
		return deserializeJson(routeResponseJson);
	}
}
