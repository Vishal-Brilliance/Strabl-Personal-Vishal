package com.strabl.services.user.bdd;

public class Search {
     public String displayUser(User user) {
		if(user.getPassword().contains(user.getPassword())) {
			return user.getPassword();
		}
    	 
    	 
    	 
    	 return null;
    	 
     }


public String displayPassworddetails(User user) {
	if (user.getPassworddetails().contains(user.getPassword()))
	{
		return user.getPassword();
	}

return null;

}


public String displaydisplayfilter(User user) {
	   if(user.getFilter().contains(user.getFilter())) {
		   
	   }
	return user.getFilter();
}
}