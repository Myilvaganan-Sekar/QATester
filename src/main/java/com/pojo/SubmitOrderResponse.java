package com.pojo;

public class SubmitOrderResponse {

	private boolean created;
	private String orderId;
	
	public SubmitOrderResponse() {
		
	}
	
	
	public boolean isCreated() {
		return created;
	}
	public void setCreated(boolean created) {
		this.created = created;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	
}
