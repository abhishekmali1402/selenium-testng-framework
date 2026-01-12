package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MyAccountPage {
	
	private WebDriver driver;

	public MyAccountPage(WebDriver driver) {

		this.driver = driver;
	}
	
	By title_MyAccount = By.xpath("//h2[normalize-space()='My Account']");
	By btn_Logout = By.xpath("//a[text()='Logout' and @class='list-group-item']");
	
	public boolean validateTitle() {
		String title = driver.getTitle();
		return title.equals("My Account"); 
	}
	
	public void clickLogout() {
		driver.findElement(btn_Logout).click();
	}
		
}
