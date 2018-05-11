import jxl.write.WriteException;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import java.io.*;
import java.util.Iterator;

public class ReadExcelFileWithJavaPOI
{
    public static void main(String[] args)
    {
        try
        {
            //Open an excel file as input stream
            FileInputStream fileInputStream = new FileInputStream(new File("/Users/pritesh.patel/Documents/PriteshDocuments/Research/Java/JavaRetrofitExample/src/workbook.xls"));
            //Get the workbook instance for XLS file
            //define a workbook
            HSSFWorkbook hssfWorkbook = new HSSFWorkbook(fileInputStream);
            //Get first sheet from the workbook
            HSSFSheet sheet = hssfWorkbook.getSheetAt(0);
            //Get iterator to all the rows in the current sheet
            Iterator rowIterator = sheet.iterator();
            while (rowIterator.hasNext())
            {
                Row row = (Row) rowIterator.next();
                System.out.println("Row Number  " + row.getRowNum());
                //For each row, iterate through each columns
                Iterator cellIterator = row.cellIterator();
                while (cellIterator.hasNext())
                {
                    Cell cell = (Cell) cellIterator.next();
                    System.out.print(cell.getStringCellValue() + ",");
                }
                System.out.println("");
            }

            fileInputStream.close();
        }
        catch (IOException e )
        {
            e.printStackTrace();
        }
    }
}
