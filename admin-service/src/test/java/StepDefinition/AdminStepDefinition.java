package StepDefinition;

import com.strabl.service.admin.bdd.Admindata;
import com.strabl.service.admin.bdd.Adminlist;


import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AdminStepDefinition {
	
	private Adminlist add;
    private Admindata get;

@Given("Admin is on admin Dashboard")
public void admin_is_on_admin_Dashboard() {
    // Write code here that turns the phrase above into concrete actions
  System.out.println("step 1: Admin is on admin Dashboard ");
}

@When("Admin will navigate to {string} he will be able to add user and when Admin will navigate to {string} he will able to edit or delete user")
public void admin_will_navigate_to_he_will_be_able_to_add_user_and_when_Admin_will_navigate_to_he_will_able_to_edit_or_delete_user(String addUser, String viewUser) {
    // Write code here that turns the phrase above into concrete actions
  System.out.println("step 2: Admin will navigate to add User he will be able to add user and when Admin will navigate to view User he will able to edit or delete user");
   add = new Adminlist(addUser, viewUser);
}


@Then("The add\\/view\\/edit\\/delete user operations would be performed")
public void the_add_view_edit_delete_user_operations_would_be_performed() {
    // Write code here that turns the phrase above into concrete actions
	try {
		 get = new Admindata();
		 String user  = get.showdata(add);
	    System.out.println("step 3: Buyer will be logged out " + user);
		 }
		 catch(Exception e)
     {
         System.out.println("Exception has Caught");
     } 
}


@Given("Admin is on Admin Dashboard")
public void admin_is_on_Admin_Dashboard() {
    // Write code here that turns the phrase above into concrete actions
System.out.println("step 1: Admin is on Admin Dashboard ");
}

@When("Admin will navigate all products")
public void admin_will_navigate_all_products() {
    // Write code here that turns the phrase above into concrete actions
	System.out.println("step 2: Admin will navigate all products ");
}

@When("Admin will able to see {string} and {string} options")
public void admin_will_able_to_see_and_options(String publish, String unpublish) {
    // Write code here that turns the phrase above into concrete actions
	System.out.println("step 3: Admin will able to see publish and unpublish options ");
	add = new Adminlist(publish, unpublish);
}

@Then("Operation will be performed as per Admin")
public void operation_will_be_performed_as_per_Admin() {
    // Write code here that turns the phrase above into concrete actions
	try {
		 get = new Admindata();
		 String approve  = get.displaydata(add);
	    System.out.println("step 4: Operation will be performed as per Admin " + approve);
		 }
		 catch(Exception e)
    {
        System.out.println("Exception has Caught");
    } 
}

}
