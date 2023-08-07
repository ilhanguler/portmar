package portmar.DataIO;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage; // OPC daha az hafıza kullanıyor !!!!!!!!!!!!!!!!!!!!!!!!!!!!
import org.apache.poi.ss.formula.functions.Rows;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import portmar.Data.TWorkbookInstance;

public class XlsxIO {

    TWorkbookInstance excelinstance = new TWorkbookInstance();

    public void ScanExcel(String excelFile) {
        try {
            FileInputStream file = new FileInputStream(new File(excelFile));
            Workbook workbook = new XSSFWorkbook(file);
            int sheetctr = 0;
            for (Iterator<Sheet> its = workbook.iterator(); its.hasNext(); sheetctr++) {
                Sheet sheet = its.next();
                excelinstance.addTSheet();
                excelinstance.addTTable(sheetctr);

                int rowctr = 0;
                for (Iterator<Row> itr = sheet.iterator(); itr.hasNext(); rowctr++) {
                    Row row = itr.next();
                    System.out.println(sheetctr);
                    excelinstance.addTRow(sheetctr, 0);
                    int cellctr = 0;
                    for (Iterator<Cell> itc = row.iterator(); itc.hasNext(); cellctr++) {
                        Cell cell = itc.next();
                        excelinstance.addTCell(sheetctr, 0, rowctr,cell);

                    }
                }
            }
            workbook.close();
            file.close();

        } catch (FileNotFoundException ex) {
            Logger.getLogger(XlsxIO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(XlsxIO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void testOutput() {
        for (int i = 0; i < excelinstance.workbook.size(); i++) {
            for (int j = 0; j < excelinstance.getTSheet(i).size(); j++) {
                for (int k = 0; k < excelinstance.getTTable(i, j).size(); k++) {
                    for (int l = 0; l < excelinstance.getTRow(i, j, k).size(); l++) {
                        System.err.println(excelinstance.getTCell(i, j, k, l));
                    }
                }
            }
        }
    }
}
