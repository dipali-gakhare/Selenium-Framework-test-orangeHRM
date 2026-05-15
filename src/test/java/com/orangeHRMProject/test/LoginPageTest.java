package com.orangeHRMProject.test;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.orangeHRMProject.base.BaseClass;
import com.orangeHRMProject.pages.HomePage;
import com.orangeHRMProject.pages.LoginPage;
import com.orangeHRMProject.utilities.DataProviders;
import com.orangeHRMProject.utilities.ExtendsManager;

public class LoginPageTest extends BaseClass {
	private LoginPage loginpage;
	private HomePage homepage;
	
	@BeforeMethod
	public void setup() {
		loginpage= new LoginPage(getDriver());
		homepage= new HomePage(getDriver());
	}
	
	@Test(dataProvider="validLoginData",dataProviderClass=DataProviders.class)
	public void verifytheTestlogin(String Username,String Password) {
		ExtendsManager.statTest("Valid Login Test");
		
		System.out.println("Running test method on thred:"+Thread.currentThread().getId());
		ExtendsManager.logstrp("navigate to login page and enter username and password");
		loginpage.login(Username,Password);
		ExtendsManager.logstrp("Admin tab open");
		Assert.assertTrue(homepage.Userdisplay(),"Display the Admin Logo");
		ExtendsManager.logstrp("validation successfuly");
		homepage.Logout();
		ExtendsManager.logstrp("logged out successfuly");
		staticWait(2);
	}
	
	@Test(dataProvider="invalidLoginData",dataProviderClass=DataProviders.class)
	public void verifyerrormessage(String Username,String password) {
		ExtendsManager.statTest("inValid Login Test");
		loginpage.login(Username,password);
		ExtendsManager.logstrp("navigate to login page and enter username and password");
		String ExpectedError="Invalid credentials";
		Assert.assertTrue(loginpage.verifyError(ExpectedError), "Invalid creadential not equals");
		ExtendsManager.logstrp("validation successfuly");
		ExtendsManager.logstrp("invalidation successfuly");
		
		
	}
	
	

}
