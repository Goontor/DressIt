package com.dressit;

import com.stackmob.sdk.api.StackMobFile;
import com.stackmob.sdk.model.StackMobModel;

public class Object extends StackMobModel{
	private int id;
	private String object_name;
	private StackMobFile object_picture;
	private float object_price;
	
	protected Object(int id, String object_name, float object_price) {
		super(Object.class); 
		this.setId(id);
		this.setObject_name(object_name);
		this.setObject_price(object_price);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getObject_name() {
		return object_name;
	}

	public void setObject_name(String object_name) {
		this.object_name = object_name;
	}

	public StackMobFile getObject_picture() {
		return object_picture;
	}

	public void setObject_picture(StackMobFile object_picture) {
		this.object_picture = object_picture;
	}

	public float getObject_price() {
		return object_price;
	}

	public void setObject_price(float object_price) {
		this.object_price = object_price;
	}

}
