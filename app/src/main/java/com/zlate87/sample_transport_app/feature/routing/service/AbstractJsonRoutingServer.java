package com.zlate87.sample_transport_app.feature.routing.service;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.zlate87.sample_transport_app.feature.routing.model.RouteResponse;

import java.io.IOException;

/**
 * An abstract {@code RoutingService} class responsible for the manipulation of JSON data.
 */
public abstract class AbstractJsonRoutingServer implements RoutingService {

	/**
	 * Method that will deserialize a JSON string into {@code RouteResponse}.
	 *
	 * @param routeResponseJson the JSON string
	 * @return the deserialized {@code RouteResponse}
	 *
	 * @throws RuntimeException if there is a problem with the deserialization of the JSON.
	 */
	protected RouteResponse deserializeJson(String routeResponseJson) {
		Moshi moshi = new Moshi.Builder().build();
		JsonAdapter<RouteResponse> jsonAdapter = moshi.adapter(RouteResponse.class);
		try {
			return jsonAdapter.fromJson(routeResponseJson);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
