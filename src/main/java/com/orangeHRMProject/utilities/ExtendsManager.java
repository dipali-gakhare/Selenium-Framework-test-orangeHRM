package com.orangeHRMProject.utilities;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.util.Base64;

public class ExtendsManager {
	private static ExtentReports extent;
	private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();
	private static Map<Long, WebDriver> driverMap = new HashMap<>();

	public synchronized static ExtentReports getReportre() {
		if (extent == null) {
			String reportPath = System.getProperty("user.dir") + "/src/test/resources/ExtendReport/ExtendReport.html";
			ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);
			spark.config().setReportName("Automation report");
			spark.config().setDocumentTitle("orangeHRM");
			spark.config().setTheme(Theme.DARK);
			extent = new ExtentReports();
			extent.attachReporter(spark);
			extent.setSystemInfo("Operating System", System.getProperty("os.name"));
			extent.setSystemInfo("java version", System.getProperty("java.version"));
			extent.setSystemInfo("user name", System.getProperty("user.name"));

		}
		return extent;

	}

	// Start thr test
	public synchronized static ExtentTest statTest(String testName) {
		ExtentTest extentTest = getReportre().createTest(testName);
		test.set(extentTest);
		return extentTest;

	}

	// end the test
	public synchronized static void endTest() {
		getReportre().flush();
	}

	public static ExtentTest getTest() {
		return test.get();

	}

	public static String getTestName() {
		ExtentTest currentTest = getTest();
		if (currentTest != null) {
			return currentTest.getModel().getName();
		} else {
			return "No test is currenlty for his threesd";
		}
	}

	// log step
	public static void logstrp(String logmessage) {
		getTest().info(logmessage);
	}

	// log validate sc
	public static void logscrinshot(WebDriver driver, String logmessage, String Screenshot) {
		getTest().pass(logmessage);
		attachescreenshot(driver, Screenshot);
		// screenshot method

	}

	// log step with ss API
	public static void VerifylogscrinshotforAPI(String logmessage) {
		getTest().pass(logmessage);

		// screenshot method

	}

	public static void logfailure(WebDriver driver, String logmessage, String Screenshot) {
		String colorMessage = String.format("<span style='color:red;'>%s</span>", logmessage);
		getTest().fail(colorMessage);
		attachescreenshot(driver, Screenshot);
	}

	public static void logfailureforAPI(String logmessage) {
		String colorMessage = String.format("<span style='color:red;'>%s</span>", logmessage);
		getTest().fail(colorMessage);
	}

	public static void logSkip(String logmessage) {
		String colorMessage = String.format("<span style='color:orange;'>%s</span>", logmessage);
		getTest().skip(colorMessage);
	}

	// take screenshot
	public synchronized static String takescreenshot(WebDriver driver, String Screenshotname) {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		String timestap = new SimpleDateFormat("yyyy-mm-dd_HH-mm-ss").format(new Date());
		String destnpath = System.getProperty("user.dir") + "/src/test/resources/Screenshot/Screenshot/"
				+ Screenshotname + "_" + timestap + ".png";
		File finalpath = new File(destnpath);
		try {
			FileUtils.copyFile(src, finalpath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// covert ss to base64
		String base64Format = convertToBase64(src);

		return base64Format;
	}

	public static String convertToBase64(File screenShotFile) {
		String base64Format = "";
		// read the filecontent into array
		byte[] filecontent;
		try {
			filecontent = FileUtils.readFileToByteArray(screenShotFile);
//			return base64Format= Base64.encode(filecontent);

			return base64Format = Base64.getEncoder().encodeToString(filecontent);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		return base64Format= Base64.getEncoder().encodeToString(filecontent);
		return base64Format;

	}

	// attach screenshoor
	public synchronized static void attachescreenshot(WebDriver driver, String message) {
		try {
			String ScreenshotBse64 = takescreenshot(driver, getTestName());
			getTest().info(message, com.aventstack.extentreports.MediaEntityBuilder
					.createScreenCaptureFromBase64String(ScreenshotBse64).build());
		} catch (Exception e) {
			getTest().fail("Failed attached screenshot");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void registerDriver(WebDriver driver) {
		driverMap.put(Thread.currentThread().getId(), driver);
	}

}
