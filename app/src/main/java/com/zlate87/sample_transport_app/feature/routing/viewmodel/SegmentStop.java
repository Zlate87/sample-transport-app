package com.zlate87.sample_transport_app.feature.routing.viewmodel;

import java.io.Serializable;

/**
 * View model for a segment stop.
 */
public class SegmentStop implements Serializable {

	private String name;
	private String time;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}
}
