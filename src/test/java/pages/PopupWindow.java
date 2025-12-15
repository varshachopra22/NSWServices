package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PopupWindow {
	WebDriver driver;

    By popupTitle = By.xpath("//h4[contains(text(),'Motor vehicle registration')]");
    By vehicleAnswer = By.xpath("//td[contains(text(),'Is this registration for a passenger vehicle?')]//following-sibling::td");
    By purchaseValue = By.xpath("//td[contains(text(),'Purchase price or value')]//following-sibling::td");
    By dutyPayable = By.xpath("//td[contains(text(),'Duty payable')]//following-sibling::td");

 	WebDriverWait wait;
 	
 	public PopupWindow(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public String getVehicleAnswer() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(vehicleAnswer)).getText();
    }

    public String getPurchaseValue() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(purchaseValue)).getText();
    }

    public String getDutyPayable() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(dutyPayable)).getText();
    }

    public boolean isDisplayed() {
    	
    	return wait.until(ExpectedConditions.visibilityOfElementLocated(popupTitle)).isDisplayed();
    }
    //pushing to repo
}
