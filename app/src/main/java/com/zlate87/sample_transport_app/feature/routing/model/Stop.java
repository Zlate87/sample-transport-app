
package com.zlate87.sample_transport_app.feature.routing.model;

public class Stop {

	private Float lat;
	private Float lng;
	private String datetime;
	private String name;
	private Object properties;

	/**
	 * @return The lat
	 */
	public Float getLat() {
		return lat;
	}

	/**
	 * @param lat The lat
	 */
	public void setLat(Float lat) {
		this.lat = lat;
	}

	/**
	 * @return The lng
	 */
	public Float getLng() {
		return lng;
	}

	/**
	 * @param lng The lng
	 */
	public void setLng(Float lng) {
		this.lng = lng;
	}

	/**
	 * @return The datetime
	 */
	public String getDatetime() {
		return datetime;
	}

	/**
	 * @param datetime The datetime
	 */
	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}

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
	 * @return The properties
	 */
	public Object getProperties() {
		return properties;
	}

	/**
	 * @param properties The properties
	 */
	public void setProperties(Object properties) {
		this.properties = properties;
	}

}
