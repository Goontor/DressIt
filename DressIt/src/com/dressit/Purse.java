package com.dressit;

import com.stackmob.sdk.api.StackMobFile;
import com.stackmob.sdk.model.StackMobModel;
import com.stackmob.sdk.model.StackMobUser;

public class Purse extends StackMobModel{
	private Float amount;
	private User owner;
	public Purse(User owner){
		super(Purse.class);
		this.setOwner(owner);
	}
	public Float getAmount() {
		return amount;
	}
	public void setAmount(Float amount) {
		this.amount = amount;
	}
	public User getOwner() {
		return owner;
	}
	public void setOwner(User owner) {
		this.owner = owner;
	}
}
