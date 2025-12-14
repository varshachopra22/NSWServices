package testRunner;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
	    features = "src/test/resources/features",
	    glue = {"glue"},
	    plugin = {
	        "pretty",	        
	        "html:target/cucumber-reports/cucumber.html",
	        "json:target/cucumber-reports/cucumber.json",
	        "junit:target/cucumber-reports/cucumber.xml"
	    },
	    monochrome = true
	)

public class TestRunnerTest {}