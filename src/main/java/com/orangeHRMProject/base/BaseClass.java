package com.orangeHRMProject.base;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.orangeHRMProject.ActionDriver.ActionDriver;
import com.orangeHRMProject.utilities.ExtendsManager;
import com.orangeHRMProject.utilities.LoggerManager;
import org.apache.logging.log4j.Logger;

public class BaseClass {
	protected static Properties prop;
	//change as per the thread

	//protected static WebDriver driver;
	//protected static ActionDriver actionDriver;
	private static ThreadLocal<WebDriver>driver=new ThreadLocal<>();
	private static ThreadLocal<ActionDriver>actionDriver=new ThreadLocal<>();
	public static final Logger Logger=LoggerManager.getLogger(BaseClass.class);
	
	@BeforeSuite
	public void loadConfig() throws IOException {
		//load the file
		prop =new Properties();
		FileInputStream fis= new FileInputStream("src\\main\\resources\\config.properties");
		prop.load(fis);
		Logger.info("properties file lodding");
//		ExtendsManager.getReportre();//maintain test listern
		
	}
	
	@BeforeMethod
	public void setUp() throws IOException {
		System.out.println("Setting up the webdriver"+this.getClass().getSimpleName());
		launchBrowser();
		configBrowser();
		staticWait(2);
		Logger.info("WebDriver Initialization");
		Logger.trace("reace messe");
		Logger.warn("warn Message");
		Logger.debug("Debug");
		/*if(actionDriver==null) {
			actionDriver=new ActionDriver(driver);
			Logger.info("ActionDriver instace is created");
		}*/
		actionDriver.set(new ActionDriver(getDriver()));
		Logger.info("ActionDriver initialise by thread:"+Thread.currentThread().getId());
		
		
	
	}
	private synchronized void launchBrowser() {
		//initialize the webdriver on browser
				String browser=prop.getProperty("browser");
				if(browser.equalsIgnoreCase("chrome")) {
//					driver=new ChromeDriver();
					driver.set(new ChromeDriver());
					
					Logger.info("Chrome Initialize");
					ExtendsManager.registerDriver(getDriver());
				}else if(browser.equalsIgnoreCase("Firefox")) {
//					driver=new FirefoxDriver();
					driver.set(new FirefoxDriver());
					ExtendsManager.registerDriver(getDriver());
				}else if(browser.equalsIgnoreCase("edge")) {
//					driver=new EdgeDriver();
					driver.set(new EdgeDriver());
					ExtendsManager.registerDriver(getDriver());
					
				}else {
					throw new IllegalArgumentException("Browser not supported" +browser);
				}
	}
	private void configBrowser() {
		//implicite wait
		int Implicitewait=Integer.parseInt(prop.getProperty("implicitewait"));
		getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(Implicitewait));
		getDriver().manage().window().maximize();
		try {
			getDriver().get(prop.getProperty("url"));
		} catch (Exception e) {
			System.out.println("Navigate to the url	fail"+e.getMessage());
		}
	}
	
	
	@AfterMethod
	public synchronized void tearDown() {
		if(getDriver()!=null) {
			try {
				getDriver().quit();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println("unable to quite:"+e.getMessage());
			}
		}
		driver.remove();
		actionDriver.remove();
//		ExtendsManager.endTest();
	
//		driver=null;
//		actionDriver =null;
		
				
	}
	public static Properties getProp() {
		return prop;
	}
	
	
	//getter method
//	public WebDriver getDriver() {
//		return driver;
//	}
	//getter method
	public static WebDriver getDriver() {
		if(driver.get()==null) {
			System.out.println("WebDriver is initialize");
			throw new IllegalStateException("WebDriver is initialized");
			
		}
		return driver.get();
	}
	
	public static ActionDriver getActionDriver() {
		if(actionDriver.get()==null) {
			System.out.println("ActionDriver is initialize");
			throw new IllegalStateException("WebDriver is initialized");
			
		}
		return actionDriver.get();
	}
		public void setDriver(ThreadLocal<WebDriver> driver) {
			this.driver=driver;
		}
	public void staticWait(int second) {
		LockSupport.parkNanos(TimeUnit.SECONDS.toNanos(second));
	}
}

