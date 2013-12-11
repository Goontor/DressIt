package com.dressit;

import com.stackmob.sdk.api.StackMobFile;
import com.stackmob.sdk.api.StackMobQuery;
import com.stackmob.sdk.callback.StackMobCountCallback;
import com.stackmob.sdk.model.StackMobUser;

public class User extends StackMobUser {
	private String email;
	private String firstName;
	private String lastName;
	private String pseudo;
	private String sexe;
	private Float walletAmount;
	private StackMobFile photo;
	
	protected User(String myusername){
		super(User.class,myusername);
	}
	protected User(String myusername, String mypassword, String myemail){
		super(User.class,myusername,mypassword);
		email = myemail;
		this.walletAmount = (float) 0;
		
	}
	protected User(String myusername, String mypassword, String myemail,String pseudo,String firstName,String lastName,String sexe){
		super(User.class,myusername,mypassword);
		email = myemail;
		this.setPseudo(pseudo);
		this.setLastName(lastName);
		this.setFirstName(firstName);
		this.setSexe(sexe);
		this.walletAmount = (float) 0;
		
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
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getPseudo() {
		return pseudo;
	}
	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}
	public String getSexe() {
		return sexe;
	}
	public void setSexe(String sexe) {
		this.sexe = sexe;
	}
	
	

}
