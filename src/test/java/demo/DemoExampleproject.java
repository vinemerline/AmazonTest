package demo;

import in.amazon.utils.DataDriven;

public class DemoExampleproject {
	
public  static void main(String[] args) throws Exception {
	
	DataDriven excelDriver = new DataDriven();
	
	String filename ="C:/Users/Vine/workspace/MavenExample1/inputFiles/testData.xlsx";
	String sheetname = "TestData";
	excelDriver.createWorkbook(filename);
	excelDriver.openWorkbook(filename);
	excelDriver.createSheet(sheetname);
	excelDriver.setCellData(sheetname, 0, 0, "AgamaSolutions");
	excelDriver.setCellData(sheetname, 1, 0, "Vine");
	excelDriver.setCellData(sheetname, 1, 1, "Deepika");
	excelDriver.setCellData(sheetname, 2, 0, "Manish");
	excelDriver.setCellData(sheetname, 2, 1, "Binod");
	excelDriver.saveFile();
	excelDriver.closeWorkbook();

	
}
}
