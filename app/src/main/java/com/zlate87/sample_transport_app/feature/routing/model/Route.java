
package com.zlate87.sample_transport_app.feature.routing.model;

import java.util.ArrayList;
import java.util.List;

public class Route {

	private String type;
	private String provider;
	private List<Segment> segments = new ArrayList<Segment>();
	private Properties properties;
	private Price price;

	/**
	 * @return The type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type The type
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return The provider
	 */
	public String getProvider() {
		return provider;
	}

	/**
	 * @param provider The provider
	 */
	public void setProvider(String provider) {
		this.provider = provider;
	}

	/**
	 * @return The segments
	 */
	public List<Segment> getSegments() {
		return segments;
	}

	/**
	 * @param segments The segments
	 */
	public void setSegments(List<Segment> segments) {
		this.segments = segments;
	}

	/**
	 * @return The properties
	 */
	public Properties getProperties() {
		return properties;
	}

	/**
	 * @param properties The properties
	 */
	public void setProperties(Properties properties) {
		this.properties = properties;
	}

	/**
	 * @return The price
	 */
	public Price getPrice() {
		return price;
	}

	/**
	 * @param price The price
	 */
	public void setPrice(Price price) {
		this.price = price;
	}

}
