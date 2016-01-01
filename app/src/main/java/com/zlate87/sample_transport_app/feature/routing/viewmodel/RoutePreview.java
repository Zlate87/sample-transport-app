package com.zlate87.sample_transport_app.feature.routing.viewmodel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * View model class for displaying route preview
 */
public class RoutePreview implements Serializable {

	private String type;
	private String price;
	private String leaveTime;
	private String arriveTime;
	private String duration;

	private List<String> icons = new ArrayList<>();

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getLeaveTime() {
		return leaveTime;
	}

	public void setLeaveTime(String leaveTime) {
		this.leaveTime = leaveTime;
	}

	public String getArriveTime() {
		return arriveTime;
	}

	public void setArriveTime(String arriveTime) {
		this.arriveTime = arriveTime;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public List<String> getIcons() {
		return icons;
	}
}
