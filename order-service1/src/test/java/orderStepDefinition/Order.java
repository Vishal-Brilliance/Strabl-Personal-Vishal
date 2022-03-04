package orderStepDefinition;

import com.strabl.service.order.bdd.Detailslist;
import com.strabl.service.order.bdd.Orderlist;


import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Order {
	
	private Orderlist order;
	private Detailslist details;
	
	@Given("Buyer is on checkout page")
	public void buyer_is_on_checkout_page() 
	{
	System.out.println("step 1: Buyer is on checkout page ");  
	}

	@When("Buyer click {string} for ordered product")
	public void buyer_click_for_ordered_product(String orderdetail) {
		System.out.println("step 2: Buyer will click on order detail ");
		order = new Orderlist(orderdetail, orderdetail, orderdetail, orderdetail, orderdetail, null);
		
	}

	@Then("Buyer should see {string}, {string} , {string} , {string} and {int} as result")
	public void buyer_should_see_and_as_result(String string, String string2, String string3, String string4, Integer int1) {
		try {
			details = new Detailslist();
			  String data = details.orderdetails(order);
	System.out.println("step 4: Browsed product will be displayed" + data );
		}
		catch(Exception e)
	  {
	      System.out.println("Exception has Caught");
	  }
	}

	
	}


