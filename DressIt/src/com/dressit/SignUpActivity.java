package com.dressit;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Text;

import com.stackmob.android.sdk.common.StackMobAndroid;
import com.stackmob.sdk.api.StackMobQuery;
import com.stackmob.sdk.api.StackMobQueryField;
import com.stackmob.sdk.callback.StackMobCountCallback;
import com.stackmob.sdk.callback.StackMobQueryCallback;
import com.stackmob.sdk.exception.StackMobException;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUpActivity extends Activity {
	public EditText userNameFiller;
	public EditText emailFiller;
	public EditText passwordFiller;
	public EditText passwordConfFiller;
	public Button registerButton;
	List<User> userList;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.signg_up_layout);
		userList = new ArrayList<User>();
		userNameFiller = (EditText) findViewById(R.id.nameInput);
		emailFiller = (EditText) findViewById(R.id.emailInput);
		passwordFiller = (EditText) findViewById(R.id.password);
		passwordConfFiller = (EditText) findViewById(R.id.passwordConf);
		registerButton = (Button)  findViewById(R.id.Register);
		
		registerButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String myname = userNameFiller.getText().toString();
				String myemail = emailFiller.getText().toString();
				String mypassword = passwordFiller.getText().toString();
				String mypasswordConf= passwordConfFiller.getText().toString();
				if(!mypassword.equals(mypasswordConf)){
					Toast.makeText(SignUpActivity.this,"Passwords do not match", 3000).show();
				}
				else if(checkUserUnicity()){
					Toast.makeText(SignUpActivity.this,"Your username is already taken", 3000).show();
				}
				else if(myemail.isEmpty()){
					Toast.makeText(SignUpActivity.this,"Please fill in your mail", 3000).show();
				}
				else if(myname.isEmpty()){
					Toast.makeText(SignUpActivity.this,"Please fill in your Name", 3000).show();
				}
				else if(mypassword.isEmpty()){
					Toast.makeText(SignUpActivity.this,"Please fill in your password", 3000).show();
				}
				else if(mypasswordConf.isEmpty()){
					Toast.makeText(SignUpActivity.this,"Please confirm your password", 3000).show();
				}
				else{
					User myUser= new User(myname,mypassword,myemail);
					myUser.save();
					Toast.makeText(SignUpActivity.this,"The user has been had", 3000).show();
					Intent registerIntent = new Intent(SignUpActivity.this,MainActivity.class);
					startActivity(registerIntent);
				}
				
			}
			public Boolean checkUserUnicity(){
				Integer countme;
				
				User.query(User.class, 
							new StackMobQuery().field(new StackMobQueryField("username").isEqualTo(userNameFiller.getText().toString())), 
				        	new StackMobQueryCallback<User>() {
							    @Override
							    public void success(List<User> result) {
							    	userList = result;
							    }
							 
							    @Override
							    public void failure(StackMobException e) {
							    }
							}
						);
				
				if(userList.size()!=0){
					return true;
				}
				else{
					return false;
				}
			}
		});
		
	}
}
