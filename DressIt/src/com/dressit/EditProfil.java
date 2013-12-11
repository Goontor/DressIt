package com.dressit;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.List;


import com.stackmob.sdk.api.StackMobFile;
import com.stackmob.sdk.callback.StackMobQueryCallback;
import com.stackmob.sdk.exception.StackMobException;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class EditProfil extends Activity implements OnClickListener{
	private static final int REQUEST_PICK_FILE = 1;

    private TextView mFilePathTextView;
    private ImageButton mStartActivityButton;
    private Button saveButton;
    private View detailPanel;
    private File selectedFile;
    private User user;
    
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.edit_profil_layout);
		mFilePathTextView = (TextView)findViewById(R.id.file_path_text_view);
        saveButton = (Button) findViewById(R.id.editProfilSave);
		mStartActivityButton = (ImageButton)findViewById(R.id.start_file_picker_button);
        mStartActivityButton.setOnClickListener((OnClickListener) this);
        saveButton.setOnClickListener((OnClickListener) this);
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
		
	}
	 public void onClick(View v) {
	        switch(v.getId()) {
	        case R.id.start_file_picker_button:
	            // Create a new Intent for the file picker activity
	        	
	            Intent intent = new Intent(this, FilePickerActivity.class);

	            //Set the initial directory to be the sdcard
	            //intent.putExtra(FilePickerActivity.EXTRA_FILE_PATH, Environment.getExternalStorageDirectory());

	            // Show hidden files
	            //intent.putExtra(FilePickerActivity.EXTRA_SHOW_HIDDEN_FILES, true);

	            // Only make .jpg files visible
	            //ArrayList<String> extensions = new ArrayList<String>();
	            //extensions.add(".jpg");
	            //intent.putExtra(FilePickerActivity.EXTRA_ACCEPTED_FILE_EXTENSIONS, extensions);

	            // Start the activity
	            startActivityForResult(intent, REQUEST_PICK_FILE);
	            break;
	        case R.id.editProfilSave:
	        	try{
	        		refreshActualUser();
	        		ByteArrayOutputStream stream = new ByteArrayOutputStream();
					Bitmap newbit = BitmapFactory.decodeFile(mFilePathTextView.getText().toString());
					newbit.compress(Bitmap.CompressFormat.JPEG, 100, stream);
					byte[] bitseq = stream.toByteArray();
	        		user.setPhoto(new StackMobFile("image/jpeg","profilPic",bitseq));
	        		user.save();
	        		Intent intent2 =new Intent(this, ProfilPage.class);
	        		startActivity(intent2);
	        		break;
	        	}
	        	catch(Exception e){
	        		Log.d("eeeee",e.toString());
	        	}
	        }
	    }
	 protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	        if(resultCode == RESULT_OK) {
	            switch(requestCode) {
	            case REQUEST_PICK_FILE:
	                if(data.hasExtra(FilePickerActivity.EXTRA_FILE_PATH)) {
	                    // Get the file path
	                    selectedFile = new File(data.getStringExtra(FilePickerActivity.EXTRA_FILE_PATH));
	                    // Set the file path text view
	                    mFilePathTextView.setText(selectedFile.getPath());  
	                    //Now I have my selected file, You can do your additional requirement with file.
	                    mStartActivityButton.setImageBitmap(BitmapFactory.decodeFile(selectedFile.getPath()));
	                    mStartActivityButton.setBackgroundResource(0);
	                }
	            }
	        }
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
					mStartActivityButton.setImageBitmap(bmp);
                    mStartActivityButton.setBackgroundResource(0);
                
				    Log.d("eeeee4","eeeee4");
				}
				catch(Exception e){
					Log.d("eeeee5","eeeee5"+e.toString());
				}
			}
		 }
	 private void refreshActualUser(){
		 User.getLoggedInUser(User.class,new StackMobQueryCallback<User>(){

				public void failure(StackMobException e) {
					// TODO Auto-generated method stub
					
				}
				@Override
				public void success(List<User> list) {
					
					final User loggedUserf = list.get(0);
					runOnUiThread(new Runnable(){

						@Override
						public void run() {
							setUser(loggedUserf);
						}
						
					});
					
					}
				
				});
	 }
	 private void setUser(User myuser){
		user = new User(myuser.getUsername());
		
	 }
}
