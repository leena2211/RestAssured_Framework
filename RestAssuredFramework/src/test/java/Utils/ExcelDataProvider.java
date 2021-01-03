package Utils;


import org.testng.annotations.DataProvider;

public class ExcelDataProvider {
	
	//@Test(dataProvider="testData1")
	public void test1(String testCase_ID, String description,String name, String job,String statusCode) {
		System.out.println(testCase_ID+"|"+description+"|"+name+"|"+job+"|"+statusCode);
	}
	
	@DataProvider(name="testData1")
	public Object[][] getData() {
		String excelPath = "C:\\Users\\Leena\\git\\RestAssured_Framework\\RestAssuredFramework\\data\\Testdata.xlsx";
		Object data[][]=testData(excelPath,"Sheet1");
		return data;
		
	}
	
	public Object[][] testData(String excelPath,String sheetName) {
		ExcelUtils excel = new ExcelUtils(excelPath,sheetName);
		
		int rowCount = excel.getRowCount();
		int colCount = excel.getColCount();
		
		Object data[][] = new Object[rowCount-1][colCount];
		
		for(int i=1;i<rowCount;i++) {
			for(int j=0;j<colCount;j++) {
				
				String cellData = excel.getCellData(i, j);
				System.out.println(cellData);
				data[i-1][j]=cellData;
			}
		}
		 return data;
		
	}

}
