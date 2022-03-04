package com.strabl.service.admin.bdd;

import java.util.ArrayList;
import java.util.List;

public class Adminlist {

	private String addUser;
	private String viewUser;
	public Adminlist(String addUser, String viewUser) {
		super();
		this.addUser = addUser;
		this.viewUser = viewUser;
		this.publish = publish;
		this.unpublish = unpublish;
	}
	public String getAddUser() {
		return addUser;
	}
	public void setAddUser(String addUser) {
		this.addUser = addUser;
	}
	public String getViewUser() {
		return viewUser;
	}
	public void setViewUser(String viewUser) {
		this.viewUser = viewUser;
	}
	
	public List<String>adminManagement(){
		List<String> adminList = new ArrayList<>();
		adminList.add(addUser);
		adminList.add(viewUser);
		return adminList;
		
	}
	
	private String publish;
	private String unpublish;
	
	public String getPublish() {
		return publish;
	}
	public void setPublish(String publish) {
		this.publish = publish;
	}
	public String getUnpublish() {
		return unpublish;
	}
	public void setUnpublish(String unpublish) {
		this.unpublish = unpublish;
	}
	
	public List<String>sellerProducts(){
		List<String> sellerProductList = new ArrayList<>();
		sellerProductList.add(publish);
		sellerProductList.add(unpublish);
		return sellerProductList;
		
	}
	
	
}
