package utils;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.testng.annotations.DataProvider;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import static utils.MyPOIUtil.getCellValue;

//import utils.MyPOIUtil.getCellValue;

/**
 * @ClassName: MyDataProvider
 * @Description: Demo the mixing of DataProvider and Parameters
 * @author: qinjun
 */
public class MyDataProvider {
    /**
     * @Title: runTest
     * @Description:Prepare the test data from excel
     * @return: Object[][]
     */
    @DataProvider(name = "MyDataProvider")
    public static Object[][] getTestData() {
        String excelPath    = "D:\\SAF\\SAF_myversionbackup_before_usexiaolong\\SAF\\testcase\\testcase.xlsx";
        String excelSheet   = "test1";
        int rowIndex = 0;
        int colIndex = 0;

        Sheet naviSheet = getSheet(excelPath, excelSheet);
        int iLastRowIndex = naviSheet.getLastRowNum();
        Row row = naviSheet.getRow(0);
        int lastColIndex = row.getLastCellNum();
        Object excelData[][] = new Object[iLastRowIndex+1][lastColIndex];
        for (rowIndex = 0; rowIndex <= iLastRowIndex; rowIndex++) {
            for (colIndex = 0; colIndex < lastColIndex; colIndex++) {
                Cell cell = getCell(naviSheet, rowIndex, colIndex);
                String paramValue = getCellValue(cell);
                excelData[rowIndex][colIndex] = paramValue;
            }
        }
        return excelData;
    }

    /**
     * @Title: getSheet
     * @Description: Get the sheet from Excel
     * @return: Sheet
     */
    private  static Sheet getSheet(String path, String sheetname){
        InputStream fs = null;
        Sheet naviSheet = null;
        try {
            fs = new FileInputStream(path);
            Workbook wb = null;
            try {
                wb = WorkbookFactory.create(fs);
            } catch (InvalidFormatException e) {
                e.printStackTrace();
            }
            naviSheet = wb.getSheet(sheetname);
        }catch(IOException e)
        {
            e.printStackTrace();
        }
        finally {
            try {
                fs.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return naviSheet;
    }

    /**
     * @Title: getCell
     * @Description: Get the Cell from Excel
     * @return: Cell
     */
    private static Cell getCell(Sheet sheet, int rowIndex, int columnIndex) {
        Row row = sheet.getRow(rowIndex);
        if (row == null) {
            row = sheet.createRow(rowIndex);
        }
        Cell cell = row.getCell(columnIndex);
        if (cell == null) {
            cell = row.createCell((short) columnIndex);
        }
        return cell;
    }

    /**
     * @Title: getCellValue
     * @Description: Get the Cell value from Excel
     * @return: String
     */
//    private static String getCellValue(Cell cell) {
//        String arg = "";
//        DecimalFormat df = new DecimalFormat("#");
//        switch (cell.getCellType()) {
//	        case HSSFCell.CELL_TYPE_STRING:
//	            arg = (cell == null ? "" : cell.getStringCellValue());
//	            break;
//	        case HSSFCell.CELL_TYPE_NUMERIC:
//	            Double dvalue = (cell == null ? 0 : cell.getNumericCellValue());
//	            arg = String.valueOf(dvalue);
//	            if(arg.matches("\\d+.[0]*"))
//	            {
//	                int endIndex = arg.indexOf(".");
//	                arg = arg.substring(0, endIndex);
//	            }
//	            if(arg.matches("^((-?\\d+.?\\d*)[Ee]{1}(\\d+))$"))
//	            {
//	                arg = df.format(dvalue);
//	            }
//	            break;
//	        case HSSFCell.CELL_TYPE_BOOLEAN:
//	            Boolean bool = (cell == null ? false : cell.getBooleanCellValue());
//	            arg = bool.toString();
//	            break;
//	        case HSSFCell.CELL_TYPE_FORMULA:
//	            arg = (cell == null ? "" : cell.getCellFormula());
//	            break;
//	        case HSSFCell.CELL_TYPE_ERROR:
//	            arg =  (cell == null ? "" : Byte.toString(cell.getErrorCellValue()));
//	            break;
//	        case HSSFCell.CELL_TYPE_BLANK:
//	            arg = "";
//	            break;
//        }
//        return arg;
//    }

}
