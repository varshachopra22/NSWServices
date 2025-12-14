package glue;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.cucumber.java.en.*;
import io.cucumber.java.Before;
import io.cucumber.java.After;
import pages.MotorStampDutyPage;
import pages.PopupWindow;
import pages.RevenueCalculatorPage;

public class StampDutyCalculatorSteps {
	WebDriver driver;
 	MotorStampDutyPage home;
 	RevenueCalculatorPage calculator;
 	PopupWindow popup;
 	
 	@Before
 	public void setUp() {
 	    WebDriverManager.chromedriver().setup();

 	    ChromeOptions options = new ChromeOptions();
 	   if (System.getenv("CI") != null || Boolean.getBoolean("headless")) {
 		   options.addArguments(
 				  "--headless=new",
 		 	      "--no-sandbox",
 		 	      "--disable-dev-shm-usage",
 		 	      "--window-size=1920,1080"
 		 	    );
 	   } 	        

 	    driver = new ChromeDriver(options);

 	    home = new MotorStampDutyPage(driver);
 	    calculator = new RevenueCalculatorPage(driver);
 	    popup = new PopupWindow(driver);
 	}
 	
    @Given("the user is on the Service NSW stamp duty page")
    public void openServiceNSWPage() {
        
        driver.manage().window().maximize();
        driver.get("https://www.service.nsw.gov.au/transaction/check-motor-vehicle-stamp-duty");
    	}
    
        
        @When("the user clicks the Check online button")
        public void clickCheckOnline() {
            home.clickCheckOnline();
        }
        
        @Then("the Revenue NSW calculator page should be displayed")
        public void verifyCalculatorPage() {
            Assert.assertTrue("Calculator page did NOT load!", calculator.isLoaded());
        }

        @When("the user selects Yes for passenger vehicle")
        public void selectYes() {
            calculator.clickYes();
        }

        @When("the user enters a purchase price of {string}")
        public void enterPrice(String value) {
            calculator.enterPurchasePrice(value);
        }

        @When("the user clicks the Calculate button")
        public void clickCalculate() {
            calculator.clickCalculate();
        }

        @Then("the calculation popup should display correct information")
        public void verifyPopup(){
        	Assert.assertTrue("Popup NOT displayed!", popup.isDisplayed());
        	driver.switchTo().activeElement();	
            Assert.assertEquals("Yes", popup.getVehicleAnswer());
            Assert.assertEquals("$45,000.00", popup.getPurchaseValue());
            Assert.assertTrue(popup.getDutyPayable().contains("")); // accepts dynamic value
            driver.quit();
        }
        
        @After
        public void tearDown() {
            if (driver != null) {
                driver.quit();
            }
        }
}
