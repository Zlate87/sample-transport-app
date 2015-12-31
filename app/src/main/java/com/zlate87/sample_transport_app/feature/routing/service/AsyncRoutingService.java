package com.zlate87.sample_transport_app.feature.routing.service;

import com.zlate87.sample_transport_app.feature.routing.model.RouteQueryParameters;
import com.zlate87.sample_transport_app.feature.routing.model.RouteResponse;

import javax.inject.Inject;

import rx.Observable;

/**
 * Class responsible for handling the {@code RoutingService} calls asynchronously.
 */
public class AsyncRoutingService {

	private RoutingService routingService;

	/**
	 * Constructor.
	 * @param routingService the routing service
	 */
	@Inject
	public AsyncRoutingService(RoutingService routingService) {
		this.routingService = routingService;
	}

	public Observable<RouteResponse> route(RouteQueryParameters routeQueryParameters) {
		return Observable.create(subscriber -> {
			try {
				RouteResponse routeResponse = routingService.route(routeQueryParameters);
				subscriber.onNext(routeResponse);
			} catch (RuntimeException e) {
				subscriber.onError(e);
			}
			subscriber.onCompleted();
		});
	}

}
