package com.orangeHRMProject.utilities;

import java.util.List;

import org.testng.annotations.DataProvider;

public class DataProviders {
	private static final String FILE_PATH=System.getProperty("user.dir")+"/src/test/resources/testdata/TestDatanew.xlsx";
	@DataProvider(name="validLoginData")
	public static Object[][]validLoginData(){
		return getSheetData("validLoginData");
		
	}
	@DataProvider(name="invalidLoginData")
	public static Object[][]invalidLoginData(){
		return getSheetData("invalidLoginData");
		
	}
	private static Object[][]getSheetData(String SheetName){
		List<String[]> sheetData=ExeclReaderUtility.getSheetData(FILE_PATH, SheetName);
		Object[][] data= new Object[sheetData.size()][sheetData.get(0).length];
		for(int i=0;i<sheetData.size();i++) {
			data[i]=sheetData.get(i);
			
		}
		return data;
		
	}

}
