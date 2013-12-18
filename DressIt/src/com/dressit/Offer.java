package com.dressit;

import com.stackmob.sdk.model.StackMobModel;

public class Offer extends StackMobModel{
	private int id;
	private String description;
	private float newPrice;
	
	protected Offer(int id, String description, float newPrice) {
		super(Offer.class); 
		this.id = id;
		this.description = description;
		this.newPrice = newPrice;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public float getNewPrice() {
		return newPrice;
	}

	public void setNewPrice(float newPrice) {
		this.newPrice = newPrice;
	}

}
