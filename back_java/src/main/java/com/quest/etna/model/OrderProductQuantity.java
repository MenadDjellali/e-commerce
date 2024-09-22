package com.quest.etna.model;

public class OrderProductQuantity {
	
	private Integer productId;
	private Integer quantity;
	public OrderProductQuantity() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(long l) {
		this.productId = (int) l;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
	

}
