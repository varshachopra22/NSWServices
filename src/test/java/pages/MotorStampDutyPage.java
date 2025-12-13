package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MotorStampDutyPage {
	 WebDriver driver;

	    By checkOnlineBtn = By.xpath("//a[contains(text(),'Check online')]");

	    public MotorStampDutyPage(WebDriver driver) {
	        this.driver = driver;
	    }

	    public void clickCheckOnline() {
	        driver.findElement(checkOnlineBtn).click();
	    }
}