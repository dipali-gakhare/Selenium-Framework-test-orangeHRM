package com.orangeHRMProject.test;

import org.testng.SkipException;
import org.testng.annotations.Test;

import com.orangeHRMProject.base.BaseClass;
import com.orangeHRMProject.utilities.ExtendsManager;

public class DummyClass2 extends BaseClass{
	@Test
	public void Dummy() {
		ExtendsManager.statTest("Valid dummy2 Test");
		String title=getDriver().getTitle();
		ExtendsManager.logstrp("verify the title");
		assert title.equals("OrangeHRM"):"Test failed-Title notmatching";
		System.out.println("Test passed2");
//		ExtendsManager.logSkip("this test is skip");
//		throw new SkipException("Skipping2 the test");
		
	}
	
	

}
