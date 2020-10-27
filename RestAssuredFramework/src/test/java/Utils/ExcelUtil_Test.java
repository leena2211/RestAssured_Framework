package Utils;

public class ExcelUtil_Test {
	
	public static void main(String[] args) {
		
	String excelPath = "./data/Testdata.xlsx";
	String sheetName = "Sheet1";
	
	ExcelUtils excel = new ExcelUtils(excelPath,sheetName);
	
	excel.getRowCount();
    excel.getCellData(1, 0);
	excel.getCellData(1, 1);
	excel.getCellData(1, 2);
	excel.getCellData(1, 3);
	excel.getCellData(1, 4);

	}
	
	
}
