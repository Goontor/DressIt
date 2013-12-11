package com.dressit;

import java.util.List;

import com.stackmob.android.sdk.common.StackMobAndroid;
import com.stackmob.sdk.api.StackMob;
import com.stackmob.sdk.callback.StackMobCountCallback;
import com.stackmob.sdk.callback.StackMobModelCallback;
import com.stackmob.sdk.callback.StackMobQueryCallback;
import com.stackmob.sdk.exception.StackMobException;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {
	private Button registerParsell;
	private Button loginParsell;
	private Button logOffParsell;
	private Button profilParsell;
	private TextView welcomeText;
	private User loggedUser;
	DressItApplication myapp;
	@Override
	//hhhhh
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		if (StackMob.getStackMob() == null) 
		{	StackMobAndroid.init(getApplicationContext(), 0,
					"f3054145-2d44-4f26-a94f-31118890f4ed");
		}
		myapp = (DressItApplication) getApplication();
		registerParsell = (Button) findViewById(R.id.registerLead);
		loginParsell = (Button) findViewById(R.id.LoginLead);
		logOffParsell = (Button) findViewById(R.id.LogOff);
		profilParsell = (Button) findViewById(R.id.Profile);
		registerParsell.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent registerIntent = new Intent(MainActivity.this,
						SignUpActivity.class);
				startActivity(registerIntent);
			}
		});
		
		loginParsell.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent registerIntent = new Intent(MainActivity.this,
						LoginActivity.class);
				startActivity(registerIntent);
			}
		});
		profilParsell.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent registerIntent = new Intent(MainActivity.this,
						ProfilPage.class);
				startActivity(registerIntent);
			}
		});
		
		logOffParsell.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				

				User myuser = new User(StackMob.getStackMob().getSession()
						.getUserIdName(), "", "");
				myuser.logout(new StackMobModelCallback() {
					public void success() {
						
					}

					public void failure(StackMobException e) {
					
					}
				});
				Intent registerIntent = new Intent(MainActivity.this,
						MainActivity.class);
				startActivity(registerIntent);
			}
		});
		welcomeText = (TextView) findViewById(R.id.welcomeHead);
		
		if (StackMob.getStackMob().isLoggedIn()) {
			loginParsell.setVisibility(View.GONE);
			registerParsell.setVisibility(View.GONE);
			
			User.getLoggedInUser(User.class,new StackMobQueryCallback<User>(){

				public void failure(StackMobException e) {
					// TODO Auto-generated method stub
					
				}
				@Override
				public void success(List<User> list) {
					
					final User loggedUserf = list.get(0);
					runOnUiThread(new Runnable() {
						  @Override
						  public void run() {
						    setMyAppUser(loggedUserf);
						  }
						});
					}
				
				});
			
			
		} else {
			logOffParsell.setVisibility(View.GONE);
			welcomeText.setVisibility(View.GONE);
		}
		welcomeText.invalidate();
		
	}
	public void setMyAppUser(User user){
		myapp.setUser(user);
		String welcome = welcomeText.getText().toString();
		welcome = welcome+" "+myapp.getUser().getPseudo();
		welcomeText.setText(welcome);
		
	}
	
	

}
