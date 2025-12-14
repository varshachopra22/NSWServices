package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RevenueCalculatorPage {
	WebDriver driver;

    By yesRadio = By.xpath("//*[@for='passenger_Y']");
    By purchasePriceInput = By.id("purchasePrice");
    By calculateBtn = By.xpath("//*[@type='submit']");
    
    WebDriverWait wait;	  

    public RevenueCalculatorPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public boolean isLoaded() {
        return driver.getTitle().contains("Motor vehicle registration duty calculator");
    }

    public void clickYes() {
//        wait.until(ExpectedConditions.elementToBeClickable(yesRadio)).click();4
    	wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        WebElement yesRadio1 = wait.until(
            ExpectedConditions.elementToBeClickable(yesRadio)
        );

        // Scroll into view (important for CI)
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block:'center'});", yesRadio1);

        try {
            yesRadio1.click();
        } catch (Exception e) {
            // CI-safe fallback
        	((JavascriptExecutor) driver)
            .executeScript("arguments[0].click();", yesRadio1);
        }
    }

    public void enterPurchasePrice(String value) {
    	wait.until(ExpectedConditions.elementToBeClickable(purchasePriceInput)).clear();
    	wait.until(ExpectedConditions.elementToBeClickable(purchasePriceInput)).sendKeys(value);
    }

    public void clickCalculate(){    	

    	((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", 
    			wait.until(ExpectedConditions.elementToBeClickable(calculateBtn)));
    	
    	driver.findElement(calculateBtn).click();
    }
}
