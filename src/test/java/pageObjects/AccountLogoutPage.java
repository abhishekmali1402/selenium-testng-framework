package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AccountLogoutPage {
	
	WebDriver driver; 
	
	public AccountLogoutPage(WebDriver driver) {
		this.driver = driver; 
	}
	
	By btn_Home = By.xpath("//i[@class='fa fa-home']"); 
	
	public void clickHome() {
		driver.findElement(btn_Home).click();
	}
}
