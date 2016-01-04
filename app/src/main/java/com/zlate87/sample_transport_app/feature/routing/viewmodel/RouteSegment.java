package com.zlate87.sample_transport_app.feature.routing.viewmodel;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * View model class for a route segment.
 */
public class RouteSegment implements Serializable {

	private String name;
	private String description;
	private String travelMode;
	private String duration;
	private String color;
	private List<SegmentStop> segmentStops = new ArrayList<>();

	private boolean isExpanded = false;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTravelMode() {
		return travelMode;
	}

	public void setTravelMode(String travelMode) {
		this.travelMode = travelMode;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public List<SegmentStop> getSegmentStops() {
		return segmentStops;
	}

	public boolean isExpanded() {
		return isExpanded;
	}

	public void setIsExpanded(boolean isExpanded) {
		this.isExpanded = isExpanded;
	}
}
