package com.dressit;

import java.util.ArrayList;

import com.stackmob.sdk.callback.StackMobModelCallback;
import com.stackmob.sdk.exception.StackMobException;
import com.stackmob.sdk.model.StackMobUser;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends Activity {
	private EditText userNameFiller;
	private EditText passwordFiller;
	private Button loginButton;
	private DressItApplication myApp;
	private Handler handler = new Handler();

	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_layout);
		userNameFiller = (EditText) findViewById(R.id.nameInputLogin);
		passwordFiller = (EditText) findViewById(R.id.passwordLogin);
		loginButton = (Button) findViewById(R.id.LoginButton);
		loginButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				User myuser = new User(userNameFiller.getText().toString(), passwordFiller.getText().toString(),null);
				myuser.login(new StackMobModelCallback() {

					@Override
					public void success() {
						Log.d("error review","3.1");
						Intent registerIntent = new Intent(LoginActivity.this,MainActivity.class);
						Log.d("error review","3.2");
						startActivity(registerIntent);
					}

					@Override
					public void failure(StackMobException arg0) {
						
						runOnUiThread(new Runnable() {
							@Override
							public void run() {
								Builder builder = new AlertDialog.Builder(
										LoginActivity.this);
								builder.setTitle("Uh oh...");
								builder.setCancelable(true);
								builder.setMessage("There was an error loggingin.");
								AlertDialog dialog = builder.create();
								dialog.show();
							}
						});
					}
				});
			}

	
			
				

		});
	}
}

