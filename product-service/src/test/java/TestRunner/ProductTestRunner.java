package TestRunner;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
	

		features = "src/test/java/com/strabl/service/product/bdd/product.feature",
		glue = {"StepDefinition"}
		
)

public class ProductTestRunner

{


	
}
