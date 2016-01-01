package com.zlate87.sample_transport_app.feature.routing.service;

import com.zlate87.sample_transport_app.feature.routing.model.RouteQueryParameters;
import com.zlate87.sample_transport_app.feature.routing.model.RouteResponse;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscriber;

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

	/**
	 * Method responsible for providing a {@code Observable<RouteResponse} for a given {@code RouteQueryParameters}.
	 *
	 * @param routeQueryParameters the query parameters for the route
	 * @return the routing options observable for the given route query parameters
	 */
	public Observable<RouteResponse> route(final RouteQueryParameters routeQueryParameters) {
		Observable.OnSubscribe<RouteResponse> observable = new Observable.OnSubscribe<RouteResponse>() {
			@Override
			public void call(Subscriber<? super RouteResponse> subscriber) {
				try {
					RouteResponse routeResponse = routingService.route(routeQueryParameters);
					subscriber.onNext(routeResponse);
				} catch (RuntimeException e) {
					subscriber.onError(e);
				}
				subscriber.onCompleted();
			}
		};
		return Observable.create(observable);
	}

}
