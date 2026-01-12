package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {

	private WebDriver driver;

	public HomePage(WebDriver driver) {

		this.driver = driver;
	}

	By btn_MyAccount = By.xpath("//span[normalize-space()='My Account']");
	By btn_Register = By.xpath("//a[normalize-space()='Register']");
	By btn_Login = By.xpath("//a[normalize-space()='Login']");
	
	public void clickMyAC() {
		driver.findElement(btn_MyAccount).click();
	}

	public void clickRegistration() {
		driver.findElement(btn_Register).click();
	}
	
	public void clickLogin() {
		driver.findElement(btn_Login).click();
	}
	
	
}
