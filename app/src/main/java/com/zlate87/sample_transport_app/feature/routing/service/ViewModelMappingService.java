package com.zlate87.sample_transport_app.feature.routing.service;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.zlate87.sample_transport_app.base.service.TimeHelperService;
import com.zlate87.sample_transport_app.feature.routing.model.Price;
import com.zlate87.sample_transport_app.feature.routing.model.Route;
import com.zlate87.sample_transport_app.feature.routing.model.RouteResponse;
import com.zlate87.sample_transport_app.feature.routing.model.Segment;
import com.zlate87.sample_transport_app.feature.routing.model.Stop;
import com.zlate87.sample_transport_app.feature.routing.viewmodel.RouteDetails;
import com.zlate87.sample_transport_app.feature.routing.viewmodel.RoutePreview;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Service class responsible for mapping the model to view model.
 */
public class ViewModelMappingService {

	private static final String TAG = ViewModelMappingService.class.getSimpleName();

	private final TimeHelperService timeHelperService;
	private final Context context;

	/**
	 * Constructor
	 *
	 * @param timeHelperService service
	 */
	@Inject
	public ViewModelMappingService(TimeHelperService timeHelperService, Context context) {
		this.timeHelperService = timeHelperService;
		this.context = context;
	}

	/**
	 * Maps a {@code RouteResponse} model to a {@code List<RouteDetails>} view model.
	 *
	 * @param routeResponse the model
	 * @return the view model
	 */
	public List<RouteDetails> map(RouteResponse routeResponse) {
		List<RouteDetails> routeDetailsList = new ArrayList<>();
		if (checkForNoRoutes(routeResponse)) {
			return routeDetailsList;
		}

		List<Route> routes = routeResponse.getRoutes();
		for (Route route : routes) {
			RouteDetails routeDetails = mapToRouteDetails(route);
			routeDetailsList.add(routeDetails);
		}

		return routeDetailsList;
	}

	@NonNull
	private RouteDetails mapToRouteDetails(Route route) {
		RouteDetails routeDetails = new RouteDetails();
		RoutePreview routePreview = mapToRoutePreview(route);
		routeDetails.setRoutePreview(routePreview);
		// TODO: 1/1/2016 map the rest of the details
		return routeDetails;
	}

	@NonNull
	private RoutePreview mapToRoutePreview(Route route) {
		RoutePreview routePreview = new RoutePreview();
		String type = getStringTextFromStringId(route.getType());
		routePreview.setType(type);
		addPriceToViewModel(route, routePreview);
		addTimesToViewModel(route, routePreview);
		addIconsToViewModel(route, routePreview);
		return routePreview;
	}

	@NonNull
	private String getStringTextFromStringId(String typeStringKey) {
		int id = context.getResources().getIdentifier(typeStringKey, "string", context.getApplicationInfo().packageName);
		if (id != 0) {
			return context.getString(id);
		} else {
			Log.e(TAG, String.format("getStringTextFromStringId: typeStringKey [%s] not found", typeStringKey));
			return typeStringKey;
		}
	}

	private void addIconsToViewModel(Route route, RoutePreview routePreview) {
		List<Segment> segments = route.getSegments();
		for (Segment segment : segments) {
			routePreview.getIcons().add(segment.getTravel_mode());
			routePreview.getIconsColors().add(segment.getColor());
			routePreview.getIconsText().add(segment.getName() != null ? segment.getName() : "");
		}
	}

	private void addTimesToViewModel(Route route, RoutePreview routePreview) {
		List<Segment> segments = route.getSegments();

		Segment firstSegment = segments.get(0);
		Stop firstStop = firstSegment.getStops().get(0);

		Segment lastSegment = segments.get(segments.size() - 1);
		Stop lastStop = lastSegment.getStops().get(lastSegment.getStops().size() - 1);

		String leaveTime = firstStop.getDatetime();
		String arriveTime = lastStop.getDatetime();

		routePreview.setLeaveTime(timeHelperService.justTime(leaveTime));
		routePreview.setArriveTime(timeHelperService.justTime(arriveTime));

		String duration = timeHelperService.calculateDuration(leaveTime, arriveTime);
		routePreview.setDuration(duration);
	}

	private void addPriceToViewModel(Route route, RoutePreview routePreview) {
		Price price = route.getPrice();
		if (price != null) {
			String currency = price.getCurrency();
			int amount = price.getAmount();
			String formattedPrice = String.format("%s %d", currency, amount);
			routePreview.setPrice(formattedPrice);
		}
	}

	private boolean checkForNoRoutes(RouteResponse routeResponse) {
		if (routeResponse == null) {
			Log.w(TAG, "map: routeResponse was null");
			return true;
		}
		if (routeResponse.getRoutes() == null) {
			Log.w(TAG, "map: routeResponse.getRoutes() was null");
			return true;
		}
		if (routeResponse.getRoutes().isEmpty()) {
			Log.w(TAG, "map: routeResponse.getRoutes() was empty");
			return true;
		}
		return false;
	}
}
