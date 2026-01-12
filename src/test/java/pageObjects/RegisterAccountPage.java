package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import testBase.BaseClass;

public class RegisterAccountPage {

	private WebDriver driver;

	public RegisterAccountPage(WebDriver driver) {

		this.driver = driver;
	}
	
	BaseClass objBase = new BaseClass(); 
	
	By txtFirstName = By.xpath("//input[@id='input-firstname']");
	By txtLastName = By.xpath("//input[@id='input-lastname']");
	By txtEmail = By.xpath("//input[@id='input-email']");
	By txtTelephone = By.xpath("//input[@id='input-telephone']");
	By txtPassword = By.xpath("//input[@id='input-password']");
	By txtConfirmPass = By.xpath("//input[@id='input-confirm']");

	By checkAgree = By.xpath("//input[@name='agree']");
	By btnContinue = By.xpath("//input[@value='Continue']");

	public void setFirstName() {
		driver.findElement(txtFirstName).sendKeys("Radha");
	}

	public void setLastName() {
		driver.findElement(txtLastName).sendKeys("Govind");
	}

	public void setEmail() {
		driver.findElement(txtEmail).sendKeys(objBase.randomString().toLowerCase() + "SrilaPrabhupad@gmail.com");
	}

	public void setTelephone() {
		driver.findElement(txtTelephone).sendKeys("1234567890");
	}

	public void setPassword() {
		driver.findElement(txtPassword).sendKeys("hari123");
	}

	public void setConfirmPass() {
		driver.findElement(txtConfirmPass).sendKeys("hari123");
	}
	
	public void checkAgree() {
		driver.findElement(checkAgree).click();
	}

	public void clickContinue() {
		driver.findElement(btnContinue).click();
	}

	public void fillAllDetails() {
		setFirstName();
		setLastName();
		setEmail();
		setTelephone();
		setPassword();
		setConfirmPass();
		clickContinue();
		checkAgree(); 
	}

}
