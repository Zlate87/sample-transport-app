
package com.zlate87.sample_transport_app.feature.routing.model;

import java.util.ArrayList;
import java.util.List;

public class Segment {

	private String name;
	private Integer numStops;
	private List<Stop> stops = new ArrayList<Stop>();
	private String travelMode;
	private Object description;
	private String color;
	private String iconUrl;
	private String polyline;

	/**
	 * @return The name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name The name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return The numStops
	 */
	public Integer getNumStops() {
		return numStops;
	}

	/**
	 * @param numStops The num_stops
	 */
	public void setNumStops(Integer numStops) {
		this.numStops = numStops;
	}

	/**
	 * @return The stops
	 */
	public List<Stop> getStops() {
		return stops;
	}

	/**
	 * @param stops The stops
	 */
	public void setStops(List<Stop> stops) {
		this.stops = stops;
	}

	/**
	 * @return The travelMode
	 */
	public String getTravelMode() {
		return travelMode;
	}

	/**
	 * @param travelMode The travel_mode
	 */
	public void setTravelMode(String travelMode) {
		this.travelMode = travelMode;
	}

	/**
	 * @return The description
	 */
	public Object getDescription() {
		return description;
	}

	/**
	 * @param description The description
	 */
	public void setDescription(Object description) {
		this.description = description;
	}

	/**
	 * @return The color
	 */
	public String getColor() {
		return color;
	}

	/**
	 * @param color The color
	 */
	public void setColor(String color) {
		this.color = color;
	}

	/**
	 * @return The iconUrl
	 */
	public String getIconUrl() {
		return iconUrl;
	}

	/**
	 * @param iconUrl The icon_url
	 */
	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}

	/**
	 * @return The polyline
	 */
	public String getPolyline() {
		return polyline;
	}

	/**
	 * @param polyline The polyline
	 */
	public void setPolyline(String polyline) {
		this.polyline = polyline;
	}

}
