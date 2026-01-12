package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountLogoutPage;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC002_ACLoginTestValidInputs extends BaseClass{
	
	@Test (groups= {"Sanity", "Master"})
	public void ACLoginTestValidInput() throws InterruptedException {
		
		HomePage hpObj = new HomePage(driver);	
		hpObj.clickMyAC();
		hpObj.clickLogin();
		
		LoginPage lpObj = new LoginPage(driver); 
		lpObj.setEmail(p.getProperty("email"));
		lpObj.setPass(p.getProperty("password"));
		lpObj.clickLogin();
		
		MyAccountPage maObj= new MyAccountPage(driver); 
		
		
		Assert.assertTrue(maObj.validateTitle(), "Login failed");	
		
		maObj.clickLogout();
		
		AccountLogoutPage alObj = new AccountLogoutPage(driver); 
		alObj.clickHome();
		
	}
}

//small change for git commit

