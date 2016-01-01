package com.zlate87.sample_transport_app.feature.routing.model;

/**
 * Model class for the result of the route validation.
 */
public class RouteValidationResult {

	private boolean isValid;
	private int messageStringId;

	public RouteValidationResult(boolean isValid) {
		this.isValid = isValid;
	}

	public RouteValidationResult(boolean isValid, int messageStringId) {
		this.isValid = isValid;
		this.messageStringId = messageStringId;
	}

	public boolean isValid() {
		return isValid;
	}

	public void setIsValid(boolean isValid) {
		this.isValid = isValid;
	}

	public int getMessageStringId() {
		return messageStringId;
	}

	public void setMessageStringId(int messageStringId) {
		this.messageStringId = messageStringId;
	}
}
