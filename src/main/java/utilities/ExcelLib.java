package utilities;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelLib {
	public static FileInputStream fis;
	public static XSSFWorkbook wb;
	public static XSSFSheet ws;
	public static XSSFRow row;
	public static XSSFCell cell;
	public static CellStyle style;

	public static int getRowCount(String filePath, String sheetName) throws IOException {
		fis = new FileInputStream(filePath);
		wb = new XSSFWorkbook(fis);
		ws = wb.getSheet(sheetName);
		int rowCount = ws.getLastRowNum();
		if (rowCount != 0)
			System.out.println("Total Rows in Selected Excel File :-  " + (rowCount + 1));
		else
			System.out.println("Total Rows in Selected Excel File :-  " + (rowCount));
		fis.close();
		return rowCount;
	}

	public static int getCellCount(String filePath, String sheetName) throws IOException {
		fis = new FileInputStream(filePath);
		wb = new XSSFWorkbook(fis);
		ws = wb.getSheet(sheetName);
		int lastRowNum = ws.getLastRowNum();
		row = ws.getRow(lastRowNum);
		int cellCount = row.getLastCellNum();
		System.out.println("Total Columns In Selected Excel File :-  " + cellCount);
		fis.close();
		return cellCount;
	}

	public static String[][] getCellDatas(String filePath, String sheetName) throws IOException {
		fis = new FileInputStream(filePath);
		wb = new XSSFWorkbook(fis);
		ws = wb.getSheet(sheetName);
		int rowCount = ExcelLib.getRowCount(filePath, sheetName);
		int cellCount = ExcelLib.getCellCount(filePath, sheetName);
		String[][] excelData = new String[rowCount][cellCount];
		try {
			for (int i = 1; i < rowCount + 1; i++) {
				for (int j = 0; j < cellCount; j++) {
					if ((ws.getRow(i).getCell(j)).getCellStyle() != null) {
						switch ((ws.getRow(i).getCell(j)).getCellType()) {
						case XSSFCell.CELL_TYPE_FORMULA:
							if ((ws.getRow(i).getCell(j)).getCellFormula() != null) {
								excelData[i - 1][j] = ws.getRow(i).getCell(j).getCellFormula();
							}
							break;
						case XSSFCell.CELL_TYPE_STRING:
							if ((ws.getRow(i).getCell(j)).getStringCellValue() != null) {
								excelData[i - 1][j] = ws.getRow(i).getCell(j).getStringCellValue();
							}
							break;
						case XSSFCell.CELL_TYPE_NUMERIC:
							if ((ws.getRow(i).getCell(j)).getCellType() == XSSFCell.CELL_TYPE_NUMERIC) {
								excelData[i - 1][j] = ws.getRow(i).getCell(j).getNumericCellValue() + "";
							}
							break;
						case XSSFCell.CELL_TYPE_BLANK:
							if ((ws.getRow(i).getCell(j)).getCellType() == XSSFCell.CELL_TYPE_BLANK) {
								excelData[i - 1][j] = ws.getRow(i).getCell(j).getStringCellValue();
							}
							break;
						case XSSFCell.CELL_TYPE_BOOLEAN:
							if ((ws.getRow(i).getCell(j)).getBooleanCellValue()) {
								excelData[i - 1][j] = ws.getRow(i).getCell(j).getStringCellValue();
							}
							break;
						default:
							excelData[i - 1][j] = ws.getRow(i).getCell(j).getStringCellValue();
						}
					}
					excelData[i - 1][j] = ws.getRow(i).getCell(j).getStringCellValue();
				}
			}
		} catch (Exception e) {
			excelData = null;
			e.printStackTrace();
		} finally {
			wb.close();
			fis.close();
		}
		return excelData;
	}
}
