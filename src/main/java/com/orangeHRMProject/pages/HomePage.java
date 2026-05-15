package com.orangeHRMProject.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.orangeHRMProject.ActionDriver.ActionDriver;
import com.orangeHRMProject.base.BaseClass;

public class HomePage {
	public ActionDriver actionDriver;
	
	private By Adminuser= By.xpath("//span[text()='Admin']");
	private By userbtn= By.className("oxd-userdropdown-name");
	private By logoutbt = By.xpath("//a[text()='Logout']");
	private By Logoornge= By.xpath("//div[@class='oxd-brand-banner']//img");
	
	public HomePage(WebDriver driver) {
		this.actionDriver=BaseClass.getActionDriver();
		}
	
	public boolean Userdisplay() {
		return actionDriver.IsDisplay(Adminuser);
		
	}
	
	public void Logout() {
		actionDriver.click(userbtn);
		actionDriver.click(logoutbt);
		
	}
	public boolean Logodisplay() {
		return actionDriver.IsDisplay(Logoornge);
		
	}
	
	

}
