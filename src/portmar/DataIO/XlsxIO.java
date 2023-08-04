
package portmar.DataIO;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.ss.usermodel.Row;  
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import portmar.Data.TWorkbookInstance;

public class XlsxIO {
    TWorkbookInstance excelinstance;
    public void ScanExcel(String excelFile) {
        try {
            FileInputStream file = new FileInputStream(new File(excelFile));
            
            try {
                Workbook workbook = new  XSSFWorkbook(file);
                for(Sheet sheet: workbook){
                    
                    for(Row row: sheet){
                        
                    }
                }
            } catch (IOException ex) {
                Logger.getLogger(XlsxIO.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        } catch (FileNotFoundException e) {
            
        }
    }
}
