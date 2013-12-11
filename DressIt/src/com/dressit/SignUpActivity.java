package com.dressit;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Text;

import com.stackmob.android.sdk.common.StackMobAndroid;
import com.stackmob.sdk.api.StackMobQuery;
import com.stackmob.sdk.api.StackMobQueryField;
import com.stackmob.sdk.api.StackMobFile;
import com.stackmob.sdk.callback.StackMobCountCallback;
import com.stackmob.sdk.callback.StackMobQueryCallback;
import com.stackmob.sdk.exception.StackMobException;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class SignUpActivity extends Activity {
	public EditText userNameFiller;
	public EditText emailFiller;
	public EditText lastNameFiller;
	public EditText firstNameFiller;
	public EditText pseudoFiller;
	public EditText passwordFiller;
	public EditText passwordConfFiller;
	public RadioGroup sexePicker;
	public RadioButton selectedSexe;
	public Button registerButton;
	List<User> userList;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.signg_up_layout);
		userList = new ArrayList<User>();
		userNameFiller = (EditText) findViewById(R.id.numberInput);
		emailFiller = (EditText) findViewById(R.id.emailInput);
		firstNameFiller = (EditText) findViewById(R.id.FirstNameInput);
		lastNameFiller = (EditText) findViewById(R.id.LastNameInput);
		pseudoFiller = (EditText) findViewById(R.id.pseudoInput);
		passwordFiller = (EditText) findViewById(R.id.password);
		passwordConfFiller = (EditText) findViewById(R.id.passwordConf);
		sexePicker =(RadioGroup) findViewById(R.id.sexeSelect);
		
		registerButton = (Button)  findViewById(R.id.Register);
		
		registerButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String myname = userNameFiller.getText().toString();
				String mypseudo = pseudoFiller.getText().toString();
				String myfirstname = firstNameFiller.getText().toString();
				String mylastname = lastNameFiller.getText().toString();
				String myemail = emailFiller.getText().toString();
				selectedSexe = (RadioButton) findViewById(sexePicker.getCheckedRadioButtonId());
				String mysexe = selectedSexe.getText().toString();
				String mypassword = passwordFiller.getText().toString();
				String mypasswordConf= passwordConfFiller.getText().toString();
				if(!mypassword.equals(mypasswordConf)){
					Toast.makeText(SignUpActivity.this,"Passwords do not match", 3000).show();
				}
				else if(checkUserUnicity()){
					Toast.makeText(SignUpActivity.this,"Your Phone is already taken", 3000).show();
				}
				else if(mypseudo.isEmpty()){
					Toast.makeText(SignUpActivity.this,"Please fill in your pseudo", 3000).show();
				}
				else if(myfirstname.isEmpty()){
					Toast.makeText(SignUpActivity.this,"Please fill in your first name", 3000).show();
				}
				else if(mylastname.isEmpty()){
					Toast.makeText(SignUpActivity.this,"Please fill in your last name", 3000).show();
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
					
					User myUser= new User(myname,mypassword,myemail,mypseudo,myfirstname,mylastname,mysexe);
					if(myUser.getSexe()=="Male"){
						ByteArrayOutputStream stream = new ByteArrayOutputStream();
						Bitmap newbit = BitmapFactory.decodeResource(getResources(),R.drawable.malepic);
						newbit.compress(Bitmap.CompressFormat.JPEG, 100, stream);
						byte[] bitseq = stream.toByteArray();
						myUser.setPhoto(new StackMobFile("image/jpeg","profilPic",bitseq));
					}
					else{
						ByteArrayOutputStream stream = new ByteArrayOutputStream();
						Bitmap newbit = BitmapFactory.decodeResource(getResources(),R.drawable.femalepic);
						newbit.compress(Bitmap.CompressFormat.JPEG, 100, stream);
						byte[] bitseq = stream.toByteArray();
						myUser.setPhoto(new StackMobFile("image/jpeg","profilPic",bitseq));
					}
					myUser.save();
					Toast.makeText(SignUpActivity.this,"The user has been add", 3000).show();
					Intent registerIntent = new Intent(SignUpActivity.this,MainActivity.class);
					startActivity(registerIntent);
				}
				
			}
			public Boolean checkUserUnicity(){
				
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
