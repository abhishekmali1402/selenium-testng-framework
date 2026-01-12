package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountLogoutPage;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC003_LoginDDT extends BaseClass{

	@Test(dataProvider="LoginData", dataProviderClass=DataProviders.class , groups= {"DataDriven", "Master"})
	public void verifyLogin_DDT(String email, String pwd, String exRes) {
		
	//👆 the parameters we pass here are automatically taken as values in each column in sequence from excel file
	//Datadriven method implementation using testNG itself will run the things in loop
		
		HomePage hpObj = new HomePage(driver);
		hpObj.clickMyAC();
		hpObj.clickLogin();

		LoginPage lpObj = new LoginPage(driver);
		lpObj.setEmail(email); // here one by one values of 1st column will be captured and passed in mail text box
		lpObj.setPass(pwd); // here one by one values of 2nd column will be captured and passed in password text box
		lpObj.clickLogin();

		MyAccountPage maObj = new MyAccountPage(driver);
		
	//below are the conditions for validation - according to our requirement - 
	
		if (exRes.equalsIgnoreCase("Valid")) {
		//if 3rd column says valid(...)
			
			if (maObj.validateTitle() == true) {
		//(...) if 3rd column says valid and if the title is also correct(...)
				maObj.clickLogout();
				AccountLogoutPage alObj = new AccountLogoutPage(driver);
				alObj.clickHome();
				
				Assert.assertTrue(true); //(...) then test case passed
			}
			else {
				
		//(...) but if 3rd column says valid and if the title is incorrect then test case failed
				Assert.assertTrue(false);
			}

		}
		
		if (exRes.equalsIgnoreCase("Invalid")) {
			
			if(maObj.validateTitle() == false) {
		//(...) if 3rd column says invalid and if the title is also incorrect(...)	
				lpObj.clickHome();
				Assert.assertTrue(true); //(...) then test case passed. bcz, our expectations are that user should not login with incorrect credentials
			}
			else {
				
				maObj.clickLogout();
				AccountLogoutPage alObj = new AccountLogoutPage(driver);
				alObj.clickHome();
			
			//(...) but if 3rd column says invalid and if the title is correct then test case failed. we don't want the user to login with incorrect credentials. 
				Assert.assertTrue(false);
				
			}
		}

	}

}
