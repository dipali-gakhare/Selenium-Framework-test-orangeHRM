package com.orangeHRMProject.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.orangeHRMProject.ActionDriver.ActionDriver;
import com.orangeHRMProject.base.BaseClass;

public class LoginPage {
	
	public ActionDriver actionDriver;
	
	private By UsernameField =By.name("username");
	private By passwordNameFiled =By.cssSelector("input[name='password']");
	private By LoginBtn =By.xpath("//button[@type='submit']");
	private By errorm = By.xpath("//p[text()='Invalid credentials']");
	
	
//	public LoginPage(WebDriver driver) {
//		this.actionDriver=new ActionDriver(driver);
//	}
	public LoginPage(WebDriver  driver) {
	 this.actionDriver=BaseClass.getActionDriver();
	}
	public void login(String username, String password) {
		actionDriver.enterText(UsernameField, username);
		actionDriver.enterText(passwordNameFiled, password);
		actionDriver.click(LoginBtn);
	}
	
	public boolean Errormessagedispaly() {
		return actionDriver.IsDisplay(errorm);
		
	}
	public String errormessage() {
		return actionDriver.getText(errorm);
		
	}
	
	public boolean verifyError(String Expected) {
		return actionDriver.compareText(errorm, Expected);
	}
	
	

}
