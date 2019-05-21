package in.amazon.utils;

import org.testng.annotations.DataProvider;

public class TestDataReader {
	DataDriven excelDriver;
	
	@DataProvider
	public Object[][] getData() throws Exception{
	
	String excelWorkbookName;
	String excelSheetName;
	
	excelWorkbookName = System.getProperty("user.dir")+"/inputFiles/GuruLogin.xlsx";
	excelSheetName= "TestDataSheet";
	excelDriver = new DataDriven();
	excelDriver.openWorkbook(excelWorkbookName);
	Object[][] data;
	int rowCount = excelDriver.getRowCount(excelSheetName);
	int cellCount = excelDriver.getCellCount(excelSheetName,0);
	data= new Object[rowCount +1][cellCount];
	for(int row=0;row<=rowCount;row++) {
		for(int cell=0;cell<cellCount;cell++) {
			data[row][cell] = excelDriver.getCellData(excelSheetName,row,cell);
		}
	}
	return data;
}

}