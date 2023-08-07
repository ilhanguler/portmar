package portmar.DataIO;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import portmar.Data.TWorkbookInstance;

public class XlsxIO {

    TWorkbookInstance excelinstance;

    public void ScanExcel(String excelFile) {
        try {
            File fil = new File(excelFile);
            FileInputStream file = new FileInputStream(fil);
            
            
            XSSFWorkbook workbook = new XSSFWorkbook("C:\\Users\\HP\\Desktop\\test.xlsx");
            for (Sheet sheet : workbook) {
                
                for (Row row : sheet) {
                    System.out.println(row.toString() + "\n");
                    System.out.println(row.getCell(0).getStringCellValue() + "\n");
                    System.out.println(row.getCell(0) + "\n");
                    System.out.println(row.getCell(0).toString() + "\n");
                }
            }
            workbook.close();
            

        } catch (FileNotFoundException ex) {
            Logger.getLogger(XlsxIO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(XlsxIO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
