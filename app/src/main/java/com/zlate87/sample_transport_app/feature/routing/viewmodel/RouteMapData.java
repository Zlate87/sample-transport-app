package com.zlate87.sample_transport_app.feature.routing.viewmodel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * View model cass for route map data.
 */
public class RouteMapData implements Serializable {

	private Position startPoint;
	private Position endPoint;

	private List<PolylineData> polylineDataList = new ArrayList<>();

	public Position getStartPoint() {
		return startPoint;
	}

	public void setStartPoint(Position startPoint) {
		this.startPoint = startPoint;
	}

	public Position getEndPoint() {
		return endPoint;
	}

	public void setEndPoint(Position endPoint) {
		this.endPoint = endPoint;
	}

	public List<PolylineData> getPolylineDataList() {
		return polylineDataList;
	}
}
