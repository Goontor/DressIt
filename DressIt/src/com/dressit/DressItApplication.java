package com.dressit;

import android.app.Application;

public class DressItApplication extends Application {
	private User user;
	
	public  void setUser(User myUser){
		user = myUser;
	}
	public User getUser(){
		return user;
	}
}
