package StepDefinition;


import com.strabl.service.user.bdd.Displaydata;
import com.strabl.service.user.bdd.Userdata;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class User
{
	private Userdata add;
	private Displaydata show;

	@Given("Buyer is on checkout page")
	public void buyer_is_on_checkout_page()
	{
	  System.out.println("step 1: Buyer is on checkout page ");

	}

	@When("Buyer will click on add new card")
	public void buyer_will_click_on_add_new_card() 
	{
      System.out.println("step 2: Buyer will click on add new card option ");
	}

	@When("buyer will enter card details {int} , {string} , {int} and {string}")
	public void buyer_will_enter_card_details_and(Integer cardnumber, String expdate, Integer cvvcode, String cardholdername) 
	{
	  System.out.println("step 3:buyer will enter the card details");
	  add = new Userdata (cardnumber, expdate, cvvcode, cardholdername);
	}

	@Then("card should be added")
	public void card_should_be_added() 
	{
		try {
		show = new Displaydata();
		  String data = show.showdata(add);
	  System.out.println("step 4: new card will be added " + data);
		}
		catch(Exception e)
        {
            System.out.println("Exception has Caught");
        }
	  
	  }
	
	
	
@Given("Buyer is on Product detail page")
	
	public void buyer_is_on_Product_detail_page() 
	{
		System.out.println("step 1: Buyer is on Product detail page");
	}

	@When("Buyer will {string} for a Product")
	
	public void buyer_will_for_a_Product(String search) 
	{
		System.out.println("step 2: Buyer will search for a product he/she want");
		
		add = new Userdata(search);
	}

	@When("If no Product is Found")
	
	public void if_no_Product_is_Found()
	{
		System.out.println("step 3: And if no Product is Found");
	}

	@Then("The {string} will be Displayed")
	
	public void the_will_be_Displayed(String RelatedProduct)
	{
		try {
		
		show = new Displaydata();
		 String data = show.showProduct(add);
		   System.out.println("step 4: Related Product will be Displayed " + data);
		}
		catch(Exception e)
        {
            System.out.println("Exception has Caught");
        }

	}
	
	
	
	@Given("Buyer is on Sign-up page")
	public void buyer_is_on_Sign_up_page() 
	{
		 System.out.println("step 1: Buyer is on Sign-up page");
	 
	}

	@Given("Buyer will click on Google Sign-up option")
	public void buyer_will_click_on_Google_Sign_up_option() 
	{
		 System.out.println("step 2: Buyer will click on Google Sign-up option");   
	  
	}

	@Given("google sign-up window will appear with all accounts of user")
	public void google_sign_up_window_will_appear_with_all_accounts_of_user()
	{
		 System.out.println("step 3: google sign-up window will appear with all accounts of user");   
	
	}

    @When("when Buyer will select {string}")
	public void when_Buyer_will_select(String emailaccount) 
	{
    	add = new Userdata(emailaccount);
		 System.out.println("step 4: when Buyer will select the google account");
	}

	@Then("Buyer will be signed-in through the selected account")
	public void buyer_will_be_signed_in_through_the_selected_account()
	{
	 
		 try {
		 show = new Displaydata();
		 String data = show.showaccount(add);
	    System.out.println("step 5: Buyer will be signed-in through the selected account " + data);
		 }
		 catch(Exception e)
         {
             System.out.println("Exception has Caught");
         }
     } 
}


