package com.zlate87.sample_transport_app.feature.routing.viewmodel;

import java.io.Serializable;

/**
 * View model cass for route polyline data.
 */
public class PolylineData implements Serializable {

	private String encodedValue;
	private String color;

	public String getEncodedValue() {
		return encodedValue;
	}

	public void setEncodedValue(String encodedValue) {
		this.encodedValue = encodedValue;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}
}
