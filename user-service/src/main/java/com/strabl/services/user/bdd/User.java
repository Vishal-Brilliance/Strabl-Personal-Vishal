package com.strabl.services.user.bdd;

import java.util.ArrayList;
import java.util.List;

public class User {

	private String emailid;
	private String password;
	private String fullName;
	private String mobileNumber;
	
	public User(String emailid, String password, String fullName, String mobileNumber) {
		super();
		this.emailid = emailid;
		this.password = password;
		this.fullName = fullName;
		this.mobileNumber = mobileNumber;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}
        public List<String> getUserdetails(){
        	   List<String> Userlist = new ArrayList<>();
        	   Userlist.add(emailid);
        	   Userlist.add(password);
        	   Userlist.add(fullName);
        	   Userlist.add(mobileNumber);
        	   return  Userlist;
        	   
        }

private String newPassword;
private String confirmPassword;

public User(String newPassword, String confirmPassword) {
	super();
	this.newPassword = newPassword;
	this.confirmPassword = confirmPassword;
}


public String getNewPassword() {
	return newPassword;
}
public void setNewPassword(String newPassword) {
	this.newPassword = newPassword;
}
public String getConfirmPassword() {
	return confirmPassword;
}
public void setConfirmPassword(String confirmPassword) {
	this.confirmPassword = confirmPassword;
}
public List<String> getPassworddetails(){
	   List<String> Userdetails= new ArrayList<>();
	   Userdetails.add(newPassword);
	   Userdetails.add(confirmPassword);
	
	  return Userdetails;
}



private String filter;

public String getFilter() {
	return filter;
}
public void setFilter(String filter) {
	this.filter = filter;
}
public User(String filter) {
	super();
	this.filter = filter;
}
public List<String> getfilter(){
	List<String> displayfilter = new ArrayList<>();
	displayfilter.add(filter);
	
	return displayfilter;
	
}
}

