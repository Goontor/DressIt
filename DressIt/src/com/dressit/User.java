package com.dressit;

import com.stackmob.sdk.api.StackMobFile;
import com.stackmob.sdk.api.StackMobQuery;
import com.stackmob.sdk.callback.StackMobCountCallback;
import com.stackmob.sdk.model.StackMobUser;

public class User extends StackMobUser {
	private String email;
	private StackMobFile photo;
	
	
	protected User(String myusername, String mypassword, String myemail){
		super(User.class,myusername,mypassword);
		email = myemail;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public StackMobFile getPhoto() {
		return photo;
	}
	public void setPhoto(StackMobFile photo) {
		this.photo = photo;
	}
	
	

}
