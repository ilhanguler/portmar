package portmar.DataIO;

import java.io.*;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import jdk.jfr.Experimental;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage; // OPC daha az hafıza kullanıyor !!!!!!!!!!!!!!!!!!!!!!!!!!!!
import org.apache.poi.ss.formula.functions.*;
import org.apache.poi.xssf.eventusermodel.*;
import org.apache.poi.xssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import portmar.Data.TWorkbookInstance;
import portmar.Data.TWorkbookEnumContainer.*;

public class XlsxIO {

    TWorkbookInstance excel = new TWorkbookInstance();

    public void scanExcel(String excelFile) {
        try {
            FileInputStream file = new FileInputStream(new File(excelFile));
            XSSFWorkbook  workbook = new XSSFWorkbook(file);
            int sheetctr = 0;
            XSSFSheet aaa = workbook.getSheetAt(sheetctr);
            for (Iterator<Sheet> its = workbook.iterator(); its.hasNext(); sheetctr++) {
                Sheet sheet = its.next();
                excel.addTSheet();
                excel.addTTable(sheetctr);
                int rowctr = 0;
                EnumMap<cellTrait, Object> a;
                for (Iterator<Row> itr = sheet.iterator(); itr.hasNext(); rowctr++) {
                    Row row = itr.next();
                    System.out.println(sheetctr);
                    excel.addTRow(sheetctr, 0);
                    int cellctr = 0;
                    System.out.println("first: " + row.getFirstCellNum() + "\tlast: " + row.getLastCellNum());
                    System.out.println("existing cells: " + row.getPhysicalNumberOfCells());

                    for (Iterator<Cell> itc = row.iterator(); itc.hasNext(); cellctr++) {
                        Cell cell = itc.next();
                        excel.addTCell(sheetctr, 0, rowctr, cell);
                        System.out.println("cell position: " + cell.getAddress());
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

    public void clearWBInstance() {
        excel.workbook.clear();
    }

    public void writeImport() {
        for (int i = 0; i < excel.workbook.size(); i++) {
            for (int j = 0; j < excel.getTSheet(i).size(); j++) {
                for (int k = 0; k < excel.getTTable(i, j).size(); k++) {
                    for (int l = 0; l < excel.getTRow(i, j, k).size(); l++) {
                        System.out.print(excel.getTCell(i, j, k, l) + " ");
                    }
                    System.out.println();
                }
            }
        }
    }

}
