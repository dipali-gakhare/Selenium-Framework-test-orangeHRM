package com.orangeHRMProject.ActionDriver;

import java.time.Duration;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.orangeHRMProject.base.BaseClass;
import com.orangeHRMProject.utilities.ExtendsManager;
import com.orangeHRMProject.utilities.LoggerManager;

public class ActionDriver {
	private WebDriver driver;

	private WebDriverWait wait;
	public static final Logger Logger = BaseClass.Logger;

	public ActionDriver(WebDriver driver) {
		this.driver = driver;
		int explicitwait = Integer.parseInt(BaseClass.getProp().getProperty("ExpliciteWait"));
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(explicitwait));
		Logger.info("Webdriver instance in created");
	}

	public void click(By by) {
		String elementDiscription=  getElementDriscription(by);
		try {
			driver.findElement(by).click();
			Logger.info("Clicked an Elemenr"+elementDiscription);
			ExtendsManager.logstrp("cliked an Elemenr");
			applyBorder(by,"green");
		} catch (Exception e) {
			System.out.println("unable to click element" + e.getMessage());
			// TODO Auto-generated catch block
			Logger.info("unable to click element:" + e.getMessage());
			applyBorder(by,"red");
			ExtendsManager.logfailure(BaseClass.getDriver(), "unable to click element", elementDiscription+"unable to clied");
		}
	}

	// Enter Text
	public void enterText(By by, String value) {
		String elementDiscription=  getElementDriscription(by);
		driver.findElement(by).clear();
		try {
			driver.findElement(by).sendKeys(value);
			applyBorder(by,"green");
		} catch (Exception e) {
			
			System.out.println("unable to enter the value:" + e.getMessage());
			applyBorder(by,"red");
			Logger.info("unable to enter the value:" + elementDiscription);
		}

	}

	public String getText(By by) {
		
		try {
			waitForElementToBeVisibliti(by);
			applyBorder(by,"green");
			return driver.findElement(by).getText();
		} catch (Exception e) {
			Logger.info("unalb to get text:" + e.getMessage());
			applyBorder(by,"red");
			return "";
		}

	}

	public boolean compareText(By by, String ExpectedText) {
		try {
			waitForElementToBeVisibliti(by);
			String ActualText = driver.findElement(by).getText();
			if (ExpectedText.equals(ActualText)) {
				applyBorder(by,"green");
				System.out.println("Text ar matching:" + ActualText + "eqauls" + ExpectedText);
				ExtendsManager.logscrinshot(BaseClass.getDriver(), "copaired Text"+ActualText, "Test verifired sussefully"+ExpectedText);
				return true;
			} else {
				applyBorder(by,"red");
				System.out.println("Text ar not matching:" + ActualText + "eqauls" + ExpectedText);
				ExtendsManager.logfailure(BaseClass.getDriver(), "copaired not Text"+ActualText, "Test verifired not sussefully"+ExpectedText);
				return false;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("methods not compare:" + e.getMessage());
		}
		return false;

	}

	public boolean IsDisplay(By by) {
		try {
			waitForElementToBeVisibliti(by);
			
			boolean IsDisplay = driver.findElement(by).isDisplayed();
			if (IsDisplay) {
				applyBorder(by,"green");
				System.out.println("Elment is visible");
				ExtendsManager.logstrp("Is visible");
				ExtendsManager.logscrinshot(BaseClass.getDriver(), "Elment is visible", "Elment is visible"+IsDisplay);
				return IsDisplay;
			} else {
				applyBorder(by,"red");
				return IsDisplay;
			}
		} catch (Exception e) {
			System.out.println("unable to display" + e.getMessage());
			ExtendsManager.logfailure(BaseClass.getDriver(), "uname to visible", e.getMessage()+"not visible");
			return false;
		}

	}

	public void scrollElement(By by) {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			WebElement element = driver.findElement(by);
			js.executeAsyncScript("argument[0],scrollIntoView(true)", element);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("unable to scroll" + e.getMessage());
		}

	}

	private void waitForElementToBeClickable(By by) {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(by));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			Logger.error("Element to not clickable:" + e.getMessage());
		}
	}

	private void waitForElementToBeVisibliti(By by) {
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(by));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			Logger.error("Element to not visible:" + e.getMessage());
		}

	}

	public String getElementDriscription(By Locator) {
		try {
			if (driver == null)
				return "driver is null";
			if (Locator == null)
				return "Locator is null";
			WebElement element = driver.findElement(Locator);
			String name = element.getDomAttribute("name");
			String id = element.getDomAttribute("id");
			String text = element.getText();
			String classnemr = element.getDomAttribute("class");
			String placeholder = element.getDomAttribute("Placeholder");
			if(isnotEmpty(name)) {
				return "element is name:"+name;
			}
			else if(isnotEmpty(id)) {
				return "element is id:"+id;
			}
			else if(isnotEmpty(text)) {
				return "element is tex:"+text;
			}
			else if(isnotEmpty(classnemr)) {
				return "element is classnemr:"+classnemr;
			}
			else if(isnotEmpty(placeholder)) {
				return "element is tex:"+placeholder;
			}
			return placeholder;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			Logger.error("Unable to discribe the element:"+e.getMessage());
		}
		return "Unable to discribe the element";
		

	}

	private boolean isnotEmpty(String value) {
		return value != null && !value.isEmpty();
	}
	public void applyBorder(By by,String color) {
		try {
			WebElement element=driver.findElement(by);
			String script="arguments[0].style.border='3px solid "+color+"'";
			JavascriptExecutor js=(JavascriptExecutor)driver;
			js.executeScript(script, element);
			Logger.info("Applied border with color"+color+"to the"+getElementDriscription(by));
		} catch (Exception e) {
			Logger.warn("failed to apply the border"+getElementDriscription(by));
			}
		
		
	}

}
