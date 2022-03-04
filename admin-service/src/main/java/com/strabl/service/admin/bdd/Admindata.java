package com.strabl.service.admin.bdd;

public class Admindata {

	public String showdata(Adminlist add){
	    if(add.adminManagement().contains(add.getViewUser())) 
	    {
				return add.getViewUser();
		}
			return null;
		}
	
	public String displaydata(Adminlist add){
	    if(add.sellerProducts().contains(add.getPublish())) 
	    {
				return add.getPublish();
		}
			return null;
		}
}
