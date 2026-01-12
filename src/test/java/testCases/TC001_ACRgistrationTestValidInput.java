package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.RegisterAccountPage;
import testBase.BaseClass;

public class TC001_ACRgistrationTestValidInput extends BaseClass{
	
	@Test (groups= {"Regression", "Master"})
	public void ACRgistrationTestValidInput() throws InterruptedException {
		
		logger.info("******Strting the test case******");
		HomePage hpObj = new HomePage(driver); 
		
		
		hpObj.clickMyAC();
		logger.info("clicked My Ac");
		hpObj.clickRegistration();
		

		RegisterAccountPage rpObj = new RegisterAccountPage(driver); 
		rpObj.fillAllDetails(); 
		logger.debug("filled all deatils");
		
		rpObj.clickContinue();
		
		Thread.sleep(5000);
		Assert.assertTrue(driver.getTitle().equals("Your Account Has Been Created!")); 

	}
	
}
