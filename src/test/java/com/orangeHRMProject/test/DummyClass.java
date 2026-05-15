package com.orangeHRMProject.test;

import org.testng.SkipException;
import org.testng.annotations.Test;

import com.orangeHRMProject.base.BaseClass;
import com.orangeHRMProject.utilities.ExtendsManager;

public class DummyClass extends BaseClass{
	@Test
	public void Dummy() {
		//ExtendsManager.statTest("Valid dummy Test");
		String title=getDriver().getTitle();
		ExtendsManager.logstrp("verify the title");
		assert title.equals("OrangeHRM"):"Test failed-Title notmatching";
		System.out.println("Test passed");
//		throw new SkipException("Skipping the test");
		
	}
	
	

}
