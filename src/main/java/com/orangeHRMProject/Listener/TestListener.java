package com.orangeHRMProject.Listener;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.orangeHRMProject.base.BaseClass;
import com.orangeHRMProject.utilities.ExtendsManager;

public class TestListener implements ITestListener {

	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		String TestName=result.getMethod().getMethodName();
		ExtendsManager.statTest(TestName);
		ExtendsManager.logstrp("Test Started"+TestName);
		
//		ITestListener.super.onTestStart(result);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		String TestName=result.getMethod().getMethodName();
		if(!result.getTestClass().getName().toLowerCase().contains("api")) {
			ExtendsManager.logscrinshot(BaseClass.getDriver(), "Test Passed!", "Test End:"+TestName+"- !Test Pass");
			
		}else {
			ExtendsManager.VerifylogscrinshotforAPI("Test passed"+TestName+"- !Test Pass");
		}
		
		
		
	}
//triggerd test faile
	@Override
	public void onTestFailure(ITestResult result) {
		String TestName=result.getMethod().getMethodName();
		String failure=result.getThrowable().getMessage();
		ExtendsManager.logstrp(failure);
		if(!result.getTestClass().getName().toLowerCase().contains("api")) {
			ExtendsManager.logfailure(BaseClass.getDriver(), "Test Failed!", "Test Failed:"+TestName+"- !Test Failed");			
		}else {
			ExtendsManager.logfailureforAPI( "Test Failed:"+TestName+"- !Test Failed");
		}
		
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		String TestName=result.getMethod().getMethodName();
		ExtendsManager.logSkip("TestSKip"+TestName);
		
	}

	@Override
	public void onStart(ITestContext context) {
		ExtendsManager.getReportre();
	}

	@Override
	public void onFinish(ITestContext context) {
		//flush report
		
		ExtendsManager.endTest();
	}
	

}
