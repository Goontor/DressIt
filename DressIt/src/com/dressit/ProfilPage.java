package com.dressit;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.stackmob.sdk.callback.StackMobQueryCallback;
import com.stackmob.sdk.exception.StackMobException;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle; 
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ProfilPage extends Activity {

    Button editButton;
    TextView pseudo;
    TextView firstName;   
    TextView lastName;
    TextView phoneNumber;
    TextView sexe;   
    ImageView profilPic;
    
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.profile_layout);  
		pseudo = (TextView) findViewById(R.id.profilPseudo);
		firstName = (TextView) findViewById(R.id.profilfirstName);
		lastName = (TextView) findViewById(R.id.profilLastName);
		phoneNumber = (TextView) findViewById(R.id.profilPhone);
		sexe = (TextView) findViewById(R.id.profilsexe);
		editButton =(Button) findViewById(R.id.profilEditButton);
		profilPic = (ImageView) findViewById (R.id.profilPic);
		User.getLoggedInUser(User.class,new StackMobQueryCallback<User>(){

			public void failure(StackMobException e) {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void success(List<User> list) {
				
				final User loggedUserf = list.get(0);
				GetData get = new GetData();
				get.execute(new User[]{loggedUserf});
				
				}
			
			});
		
		
		
		
		
		editButton.setOnClickListener(new OnClickListener(){
		    
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(ProfilPage.this, EditProfil.class);
				startActivity(intent);
			}
			
		});
		
	}
	
	private class GetData extends AsyncTask<User, Void, Object[]> {

		   
		
		@Override
		protected Object[] doInBackground(User... users) {
			try{
				User user = users[0];
			    URL url = new URL(user.getPhoto().getS3Url());
			    InputStream is = (InputStream) url.getContent();
		        Bitmap bmp = BitmapFactory.decodeStream(is);
			    Log.d("eeeee2","eeeee2"+url.toString());
			    Object[] objectList = new Object[]{user,bmp}; 
			    return objectList;
				}
			catch(Exception e){ 
				Log.d("eeeee3","eeeee3"+e.toString());
				return null;
			}
		}
		protected void onPostExecute(Object[] objectList) {
			try{
				User user =(User) objectList[0];
				Bitmap bmp = (Bitmap) objectList[1];
				pseudo.setText(user.getPseudo());
			    firstName.setText(user.getFirstName());
			    lastName.setText(user.getLastName());
			    phoneNumber.setText(user.getUsername());
			    sexe.setText(user.getSexe());
			    profilPic.setImageBitmap(bmp);
			    Log.d("eeeee4","eeeee4");
			}
			catch(Exception e){
				Log.d("eeeee5","eeeee5"+e.toString());
			}
		}
	 }
	
}
