package com.dressit;

import com.stackmob.sdk.model.StackMobModel;

public class Company extends StackMobModel{
	private int id;
	private String name;
	
	protected Company(int id, String name) {
		super(Company.class); 
		this.id = id;
		this.name = name;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCompanyName() {
		return name;
	}
	public void setCompanyName(String name) {
		this.name = name;
	}

}
