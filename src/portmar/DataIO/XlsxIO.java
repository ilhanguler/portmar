package portmar.DataIO;

import java.io.*;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashMap;
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
import portmar.DataIO.TWorkbookEnumContainer.*;

public class XlsxIO {

    TWorkbookInstance excel = new TWorkbookInstance();
    TWorkbookInstance excelLayout = new TWorkbookInstance();
    ArrayList<tablePointer> tableBnd = new ArrayList();
    ArrayList<ArrayList<Boolean>> excelMapper = new ArrayList();


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

    public void importExcel(String excelFile) {
        EnumMap<cellTrait, Object> cellContent = null;
        EnumMap<rowTrait, Object> rowContent = null;
        EnumMap<tableTrait, Object> tableContent = null;
        EnumMap<sheetTrait, Object> sheetContent = null;

        try {
            FileInputStream file = new FileInputStream(new File(excelFile));
            Workbook workbook = new XSSFWorkbook(file);
            int sheetctr = 0;

            for (Iterator<Sheet> its = workbook.iterator(); its.hasNext(); sheetctr++) {
                Sheet sheet = its.next();
                excelLayout.addTSheet(sheetContent);
                excelLayout.addTTable(sheetctr, tableContent);
                int rowctr = 0;

                for (Iterator<Row> itr = sheet.iterator(); itr.hasNext(); rowctr++) {
                    Row row = itr.next();
                    excelLayout.addTRow(sheetctr, 0, rowContent);
                    int cellctr = 0;

                    excelMapper.add(new ArrayList());
                    for (int i = 0; i < row.getLastCellNum(); i++) {
                        excelMapper.get(excelMapper.size()-1).add(false);
                    }

                    for (Iterator<Cell> itc = row.iterator(); itc.hasNext(); cellctr++) {
                        Cell cell = itc.next();
                        excelLayout.addTCell(sheetctr, 0, rowctr, null, cellContent);
                        excelMapper.get(excelMapper.size()-1).set(cell.getColumnIndex(), true);
                    }
                    
                    for (int c = 0; c < excelLayout.getTRow(sheetctr, 0, rowctr).size(); c++) {
                        var selectedCell = excelLayout.getTCellContainer(sheetctr, 0, rowctr, c);
                        var leftCell = excelLayout.getTCellContainer(sheetctr, 0, rowctr, c-1);
                        var topCell = excelLayout.getTCellContainer(sheetctr, 0, rowctr-1, c);
                        excelMapper.get(rowctr-1).get(c);
                        if (c != 0 && leftCell.pos_column == selectedCell.pos_column - 1) {
                            if (rowctr != 0 && topCell.pos_row == selectedCell.pos_row - 1) {

                            } else if(true) {
                                
                            }
                        }
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

    class tablePointer {

        static int counter = 0;
        int id;
        int pos_colhead;
        int pos_rowhead;
        int pos_coltail;
        int pos_rowtail;

        public tablePointer(int pos_colhead, int pos_rowhead, int pos_coltail, int pos_rowtail) {
            this.id = counter;
            counter++;
            this.pos_colhead = pos_colhead;
            this.pos_rowhead = pos_rowhead;
            this.pos_coltail = pos_coltail;
            this.pos_rowtail = pos_rowtail;
        }

        public void expandTable(int pos_row, int pos_column) {
            if (pos_column > pos_coltail) {
                pos_coltail++;
            }
            if (pos_row > pos_rowtail) {
                pos_rowtail++;
            }
        }
    }
}
