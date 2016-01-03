package com.zlate87.sample_transport_app.feature.routing.viewmodel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * View model cass for route map data.
 */
public class RouteMapData implements Serializable {

	private List<PolylineData> polylineDataList = new ArrayList<>();

	public List<PolylineData> getPolylineDataList() {
		return polylineDataList;
	}
}
