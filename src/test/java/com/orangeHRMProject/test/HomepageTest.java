package com.orangeHRMProject.test;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.orangeHRMProject.base.BaseClass;
import com.orangeHRMProject.pages.HomePage;
import com.orangeHRMProject.pages.LoginPage;
import com.orangeHRMProject.utilities.ExtendsManager;

public class HomepageTest extends BaseClass {
	private LoginPage loginpage;
	private HomePage homepage;
	
	@BeforeMethod
	public void setup() {
		loginpage= new LoginPage(getDriver());
		homepage= new HomePage(getDriver());
	}
	
	@Test
	public void verifylogo() {
		ExtendsManager.statTest("Valid LoginHome Test");
		ExtendsManager.logstrp("navigate to login page and enter username and password");
		loginpage.login("Admin","admin123");
		ExtendsManager.logstrp("Admin tab open");
		Assert.assertTrue(homepage.Logodisplay(), "logo is visible");
		ExtendsManager.logstrp("validation Logo successfuly");
		ExtendsManager.logstrp("logged out successfuly");
		
	}
	

}
