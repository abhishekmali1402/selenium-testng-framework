package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

	private WebDriver driver;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}

	By txt_Email = By.xpath("//input[@id='input-email']");
	By txt_Password = By.xpath("//input[@id='input-password']");
	By btn_Login = By.xpath("//input[@value='Login']");
	By btn_Home = By.xpath("//i[@class='fa fa-home']");

	
	public void setEmail(String email) {

		driver.findElement(txt_Email).sendKeys(email);
	}

	public void setPass(String pass) {
		driver.findElement(txt_Password).sendKeys(pass);
	}

	public void clickLogin() {
		driver.findElement(btn_Login).click();
	}
	
	public void clickHome() {
		driver.findElement(btn_Home).click();
	}


}
