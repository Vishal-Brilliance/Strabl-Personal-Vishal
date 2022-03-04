package StepDefinition;


import com.strabl.service.product.bdd.Display;
import com.strabl.service.product.bdd.Itemlist;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Product
{
	private Itemlist item;
	private Display display;
	

@Given("Buyer is on home page")
public void buyer_is_on_home_page() 
	{
	   System.out.println("step 1: Buyer is on home page");  
	}

@Given("Buyer will select a Product")
public void buyer_will_select_a_Product() 
	{
	   System.out.println("step 2: Buyer will select a Product");
	}

@When("Buyer will click on Product Details {int} , {string} and {string}")
public void buyer_will_click_on_Product_Details_and(Integer price, String category, String subcategory) 
	{
	   System.out.println("step 3: The product details option is clicked");
	   item = new Itemlist(price, category, subcategory);
	}   

@Then("Full Product Details will be Displayed")
public void full_Product_Details_will_be_Displayed()
	{
	  try { 
	  display = new Display();
	  String data = display.displayitem(item);
	  System.out.println("step 4: Full Product Details will be Displayed " + data);
	  }
	  catch(Exception e)
      {
          System.out.println("Exception has Caught");
      }
	  
	  }
	
@Given("Buyer is on Home page and searched for a Product")
public void buyer_is_on_Home_page_and_searched_for_a_Product() 
{
     System.out.println("step 1: Buyer is on Home page and searched for a Product");   
}

@Given("Buyer has Selected a Product")
public void buyer_has_Selected_a_Product() 
{
	 System.out.println("step 2: Buyer has Selected a Product");     
}
@When("Buyer will click on {string} option")
public void buyer_will_click_on_option(String addProduct) 
{
	 System.out.println("step 3: Buyer will click on add to cart option for selected Product");    
	 item = new Itemlist(addProduct);
}

@Then("The selected Product will be added to Cart")
public void the_selected_Product_will_be_added_to_Cart() 
{
	try {
	display = new Display();
	  String data = display.getproduct(item);
	  System.out.println("step 4: Product will be added to Cart " + data);    

	}
	
	catch(Exception e)
    {
        System.out.println("Exception has Caught");
    }
  
  }


@Given("Buyer is on landing page without sign-up")
public void buyer_is_on_landing_page_without_sign_up() {
  System.out.println("step 1: Buyer is on landing page without sign-up");
}

@When("Buyer will {string} for product")
public void buyer_will_for_product(String browse) {
  System.out.println("step 2:Buyer will browse for product");  
  item = new Itemlist(browse);
}

@Then("The searched product will be displayed")
public void the_searched_product_will_be_displayed() {
	try {
		display = new Display();
		  String data = display.getbrowse(item);
  System.out.println("step 3: Browsed product will be displayed" + data );
	}
	catch(Exception e)
    {
        System.out.println("Exception has Caught");
    }
}


	}

