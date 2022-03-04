package orderTestRunner;


import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
	

		features = {"src/test/java/com/strabl/service/order/bdd/order.feature"},
		glue = {"src/test/java/orderStepDefinition"}
		
)

public class OrderTestRunner {

}
