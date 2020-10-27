package Utils;

import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {
	
	static XSSFWorkbook workbook;
	static XSSFSheet sheet;
	
	
	
	public ExcelUtils(String excelPath,String sheetName) {
	
		try{
			workbook = new XSSFWorkbook(excelPath);
			sheet = workbook.getSheet(sheetName);
		}catch (Exception exp) {
			
			System.out.println(exp.getMessage());
			System.out.println(exp.getCause());
			exp.printStackTrace();
			}
		
	}
	

	public static int getRowCount() {

		int rowsCount=0;
		try {
			
			
			rowsCount= sheet.getPhysicalNumberOfRows();//get row count
			//System.out.println("No. of rows : "+rowsCount);
			
		} catch (Exception exp) {
			
			System.out.println(exp.getMessage());
			System.out.println(exp.getCause());
			exp.printStackTrace();
		}
		return rowsCount;
	}
	
	public static int getColCount() {

		int colCount= 0;
		try {
			
			
			colCount= sheet.getRow(0).getPhysicalNumberOfCells();//get column count
			//System.out.println("No. of column : "+colCount);
			
		} catch (Exception exp) {
			
			System.out.println(exp.getMessage());
			System.out.println(exp.getCause());
			exp.printStackTrace();
		}
		return colCount;
	}
	
	
	public static String getCellData(int rowNum,int colNum) {
		String value = null ;//= new Object();//Object type as it accept any datatype value
		try {
				
			DataFormatter formatter = new DataFormatter();//Helps used to format the data	
			
			value=formatter.formatCellValue(sheet.getRow(rowNum).getCell(colNum));
			
			System.out.println(value);	
			
		} catch (Exception exp) {
			
			System.out.println(exp.getMessage());
			System.out.println(exp.getCause());
			exp.printStackTrace();
		}
		return value;
	}
		
	
}
