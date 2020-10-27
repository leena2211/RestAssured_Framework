package com.test;


import org.testng.annotations.DataProvider;

public class TestDataProvider extends BaseClass {
	@DataProvider(name="DataForPost")
	public Object[][] dataToPost() {
//	Object[][] data= new Object[2][2];
//	data[0][0]= "MS Dhoni";
//	data[0][1]= "Cricketer";
//	
//	
//	data[1][0]= "Arjun";
//	data[1][1]= "Businessman";
//	
//	return data;
		
		return new Object[][] {
			{"MS Dhoni","Cricketer"},
			{"Arjun","Businessman"}
		};
	}

}
