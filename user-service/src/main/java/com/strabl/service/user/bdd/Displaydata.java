package com.strabl.service.user.bdd;

public class Displaydata {	
	public String showdata(Userdata add){
    if(add.addMultipleCard().contains(add.getCardholdername())) 
    {
			return add.getCardholdername();
	}
		return null;
	}


	
	
	public String showProduct(Userdata add){	
    if(add.showRelatedProducts().contains(add.getSearch()))  {
return add.getSearch(); 
    	}
    return null;
	}
	
	
	
	public String showaccount(Userdata add) {
    if(add.googleAccount().contains(add.getEmailaccount())) 
    {
			return add.getEmailaccount();
	}
		return null;
	}


	
	}



    