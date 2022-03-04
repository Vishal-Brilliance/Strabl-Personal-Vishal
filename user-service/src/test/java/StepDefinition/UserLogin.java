package StepDefinition;

import com.strabl.services.user.bdd.Search;
import com.strabl.services.user.bdd.User;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class UserLogin {
	 
	private User user;
	private Search search;
	@Given("User is on Home Page when he apply for registration")
	public void user_is_on_Home_Page_when_he_apply_for_registration()
	{
		System.out.println("Step 1 : User is on Home Page"); 
	    
		
	}

	@When("user enter personal data {string} , {string} , {string} and {string}")
	public void user_enter_personal_data_and(String emailid, String password, String fullName, String mobileNumber)
	{
		user = new User(emailid,password,fullName,mobileNumber);
		
		 System.out.println("Step 2 : Enter Personal Data");
	
		 
	}

	@Then("on users registered email id verification email will be sent")
	public void on_users_registered_email_id_verification_email_will_be_sent() 
	{
		 search = new Search();
		  String name = search.displayUser(user);
		  System.out.println("Step 3 : A Verification email is sent on users registered email " + name);

	}



@Given("Buyer is on Login Page")
public void buyer_is_on_Login_Page()
{
	  
	 System.out.println("Step 1 : Buyer is on Login Page");
}

@When("Buyer clicks on forgot password button {string} option will be showed, buyer enter email id a link will be sent on registered email")
public void buyer_clicks_on_forgot_password_button_option_will_be_showed_buyer_enter_email_id_a_link_will_be_sent_on_registered_email(String emailid)
{
	System.out.println("Step 2 : After clicking on forgot password button , option of email is shown , when buyer enters email a link will be sent  ");
    user = new User(emailid, null);
}

@When("Buyer proceeds buyer will enter {string} and {string}")
public void buyer_proceeds_buyer_will_enter_and(String newPassword, String confirmPassword) 
{
	 System.out.println("Step 3 : Enter New Pssword and Confirm Password");
     user = new User(newPassword,confirmPassword);
}

@Then("Password is changed to {string} and password will reset")
public void password_is_changed_to_and_password_will_reset(String newPassword)
{
	 search = new Search ();
	 String password = search.displayPassworddetails(user);
	 System.out.println("Step 4 :Password will Reset" + password);
}




@Given(": Buyer is on listing page")
public void buyer_is_on_listing_page() 
{
   System.out.println("Step 1: Buyer is on listing page");
}

@When(":  Buyer search for products various option to {string} should be displayed")
public void buyer_search_for_products_various_option_to_should_be_displayed(String filter) 
{
      user = new User(filter);
	
	System.out.println("Step 2 : Option to filter will be displayed "); 
}

@Then(":  Products related to selected filter will be displayed")
public void products_related_to_selected_filter_will_be_displayed() 
{
	search = new Search();
	String filter = search.displaydisplayfilter(user);
	
	
	System.out.println("Step 3 : Selected Filter will be displayed" + filter);
}

}



	