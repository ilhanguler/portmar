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

    private TWorkbookInstance excelLayout = new TWorkbookInstance();
    private ArrayList<TBoundary> tableBoundaries = new ArrayList();
    private ArrayList<ArrayList<Boolean>> excelMapper = new ArrayList();

    public void clearWBInstance() {
        excelLayout.workbook.clear();
        tableBoundaries.clear();
        excelMapper.clear();
    }

    public void writeTableBoundaries() {
        for (TBoundary boundary : tableBoundaries) {
            System.out.println(boundary.pos_rowhead + " " + boundary.pos_colhead + " || " + boundary.pos_rowtail + " " + boundary.pos_coltail);
        }
    }

    public void writeImport() {
        for (int i = 0; i < excelLayout.workbook.size(); i++) {
            for (int j = 0; j < excelLayout.getTSheet(i).size(); j++) {
                for (int k = 0; k < excelLayout.getTTable(i, j).size(); k++) {
                    for (int l = 0; l < excelLayout.getTRow(i, j, k).size(); l++) {
                        System.out.print(excelLayout.getTCellContainer(i, j, k, l).pos_row
                                + ": "
                                + excelLayout.getTCellContainer(i, j, k, l).pos_column
                                + ": "
                                + excelLayout.getTCell(i, j, k, l) + " ");
                    }
                    System.out.println();
                }
            }
        }
    }

    public void writeMapper() {
        for (var row : excelMapper) {
            for (var cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
    }

    public Boolean isOutOfBounds(int request, int targetSize) {
        return request >= targetSize;
    }

    public void importExcel(String excelFile) {
        EnumMap<cellTrait, Object> cellContent = new EnumMap<>(cellTrait.class);
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

                    for (int i = excelMapper.size() - 1; i < row.getRowNum(); i++) {
                        excelMapper.add(new ArrayList());
                    }

                    for (int i = 0; i < row.getLastCellNum(); i++) {
                        excelMapper.get(excelMapper.size() - 1).add(false);
                    }

                    for (Iterator<Cell> itc = row.iterator(); itc.hasNext(); cellctr++) {
                        Cell cell = itc.next();
                        cellContent.put(cellTrait.POS_ROW, cell.getRowIndex());
                        cellContent.put(cellTrait.POS_COLUMN, cell.getColumnIndex());
                        excelLayout.addTCell(sheetctr, 0, rowctr, cell, cellContent);
                        excelMapper.get(excelMapper.size() - 1).set(cell.getColumnIndex(), true);
                    }

                    for (int c = 0; c < excelLayout.getTRow(sheetctr, 0, rowctr).size(); c++) {
                        int selectedColumn = excelLayout.getTCellContainer(sheetctr, 0, rowctr, c).pos_column;
                        int selectedRow = excelLayout.getTCellContainer(sheetctr, 0, rowctr, c).pos_row;

                        if (selectedColumn != 0
                                && !isOutOfBounds(selectedColumn - 1, excelMapper.get(selectedRow).size())
                                && !isOutOfBounds(selectedRow, excelMapper.size())
                                && excelMapper.get(selectedRow).get(selectedColumn - 1)) {
                            if (selectedRow != 0
                                    && !isOutOfBounds(selectedColumn, excelMapper.get(selectedRow - 1).size())
                                    && !isOutOfBounds(selectedRow - 1, excelMapper.size())
                                    && excelMapper.get(selectedRow - 1).get(selectedColumn)) {
                                for (var boundary : tableBoundaries) {
                                    if (boundary.contains(selectedRow - 1, selectedColumn)) {
                                        boundary.expandTable(selectedRow, selectedColumn);
                                        break;
                                    }
                                }

                            } else {
                                int reachedRow = selectedRow;
                                for (; reachedRow != -1
                                        && !isOutOfBounds(selectedColumn, excelMapper.get(reachedRow).size())
                                        && !isOutOfBounds(reachedRow, excelMapper.size())
                                        && !excelMapper.get(reachedRow).get(selectedColumn); reachedRow--) {
                                    //Reaching available most TOP
                                }
                                Boolean re_route = true;
                                if (reachedRow > -1) {
                                    for (var boundary : tableBoundaries) {
                                        if (boundary.containsBoth(selectedRow, selectedColumn - 1, reachedRow, selectedColumn)) {
                                            boundary.expandTable(selectedRow, selectedColumn);
                                            re_route = false;
                                            break;
                                        }
                                    }
                                }
                                if (re_route) {
                                    for (var boundary : tableBoundaries) {
                                        if (boundary.contains(selectedRow, selectedColumn - 1)) {
                                            if (boundary.contains(selectedRow - 1, selectedColumn - 1)) {
                                                tableBoundaries.add(new TBoundary(selectedRow, selectedColumn, selectedRow, selectedColumn));
                                                break;
                                            } else {
                                                boundary.expandTable(selectedRow, selectedColumn);
                                                break;
                                            }
                                        }
                                    }
                                }
                            }
                        } else {
                            if (selectedRow != 0
                                    && !isOutOfBounds(selectedColumn, excelMapper.get(selectedRow - 1).size())
                                    && !isOutOfBounds(selectedRow - 1, excelMapper.size())
                                    && excelMapper.get(selectedRow - 1).get(selectedColumn)) {
                                for (var boundary : tableBoundaries) {
                                    if (boundary.contains(selectedRow - 1, selectedColumn)) {
                                        boundary.expandTable(selectedRow, selectedColumn);
                                        break;
                                    }
                                }
                            } else {
                                int reachedRow = selectedRow;
                                int reachedColumn = selectedColumn;
                                Boolean re_route = true;
                                for (; reachedRow != -1
                                        && !isOutOfBounds(selectedColumn, excelMapper.get(reachedRow).size())
                                        && !isOutOfBounds(reachedRow, excelMapper.size())
                                        && !excelMapper.get(reachedRow).get(selectedColumn); reachedRow--) {
                                    //Reaching available most TOP
                                }
                                for (; reachedColumn != -1 && !excelMapper.get(selectedRow).get(reachedColumn); reachedColumn--) {
                                    //Reaching available most LEFT
                                }
                                if (reachedRow > -1) {
                                    for (var boundary : tableBoundaries) {
                                        if (boundary.containsBoth(selectedRow - 1, selectedColumn, reachedRow, selectedColumn)) {
                                            boundary.expandTable(selectedRow, selectedColumn);
                                            re_route = false;
                                            break;
                                        } else if (reachedColumn > -1 && boundary.containsBoth(reachedRow, selectedColumn, selectedRow, reachedColumn)) {
                                            boundary.expandTable(selectedRow, selectedColumn);
                                            re_route = false;
                                            break;
                                        }
                                    }
                                }
                                if (re_route) {
                                    tableBoundaries.add(new TBoundary(selectedRow, selectedColumn, selectedRow, selectedColumn));
                                }
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

    public void importExcel(File excelFile) {
        EnumMap<cellTrait, Object> cellContent = new EnumMap<>(cellTrait.class);
        EnumMap<rowTrait, Object> rowContent = null;
        EnumMap<tableTrait, Object> tableContent = null;
        EnumMap<sheetTrait, Object> sheetContent = null;

        try {
            FileInputStream file = new FileInputStream(excelFile);
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

                    for (int i = excelMapper.size() - 1; i < row.getRowNum(); i++) {
                        excelMapper.add(new ArrayList());
                    }

                    for (int i = 0; i < row.getLastCellNum(); i++) {
                        excelMapper.get(excelMapper.size() - 1).add(false);
                    }

                    for (Iterator<Cell> itc = row.iterator(); itc.hasNext(); cellctr++) {
                        Cell cell = itc.next();
                        cellContent.put(cellTrait.POS_ROW, cell.getRowIndex());
                        cellContent.put(cellTrait.POS_COLUMN, cell.getColumnIndex());
                        excelLayout.addTCell(sheetctr, 0, rowctr, cell, cellContent);
                        excelMapper.get(excelMapper.size() - 1).set(cell.getColumnIndex(), true);
                    }

                    for (int c = 0; c < excelLayout.getTRow(sheetctr, 0, rowctr).size(); c++) {
                        int selectedColumn = excelLayout.getTCellContainer(sheetctr, 0, rowctr, c).pos_column;
                        int selectedRow = excelLayout.getTCellContainer(sheetctr, 0, rowctr, c).pos_row;

                        if (selectedColumn != 0
                                && !isOutOfBounds(selectedColumn - 1, excelMapper.get(selectedRow).size())
                                && !isOutOfBounds(selectedRow, excelMapper.size())
                                && excelMapper.get(selectedRow).get(selectedColumn - 1)) {
                            if (selectedRow != 0
                                    && !isOutOfBounds(selectedColumn, excelMapper.get(selectedRow - 1).size())
                                    && !isOutOfBounds(selectedRow - 1, excelMapper.size())
                                    && excelMapper.get(selectedRow - 1).get(selectedColumn)) {
                                for (var boundary : tableBoundaries) {
                                    if (boundary.contains(selectedRow - 1, selectedColumn)) {
                                        boundary.expandTable(selectedRow, selectedColumn);
                                        break;
                                    }
                                }

                            } else {
                                int reachedRow = selectedRow;
                                for (; reachedRow != -1
                                        && !isOutOfBounds(selectedColumn, excelMapper.get(reachedRow).size())
                                        && !isOutOfBounds(reachedRow, excelMapper.size())
                                        && !excelMapper.get(reachedRow).get(selectedColumn); reachedRow--) {
                                    //Reaching available most TOP
                                }
                                Boolean re_route = true;
                                if (reachedRow > -1) {
                                    for (var boundary : tableBoundaries) {
                                        if (boundary.containsBoth(selectedRow, selectedColumn - 1, reachedRow, selectedColumn)) {
                                            boundary.expandTable(selectedRow, selectedColumn);
                                            re_route = false;
                                            break;
                                        }
                                    }
                                }
                                if (re_route) {
                                    for (var boundary : tableBoundaries) {
                                        if (boundary.contains(selectedRow, selectedColumn - 1)) {
                                            if (boundary.contains(selectedRow - 1, selectedColumn - 1)) {
                                                tableBoundaries.add(new TBoundary(selectedRow, selectedColumn, selectedRow, selectedColumn));
                                                break;
                                            } else {
                                                boundary.expandTable(selectedRow, selectedColumn);
                                                break;
                                            }
                                        }
                                    }
                                }
                            }
                        } else {
                            if (selectedRow != 0
                                    && !isOutOfBounds(selectedColumn, excelMapper.get(selectedRow - 1).size())
                                    && !isOutOfBounds(selectedRow - 1, excelMapper.size())
                                    && excelMapper.get(selectedRow - 1).get(selectedColumn)) {
                                for (var boundary : tableBoundaries) {
                                    if (boundary.contains(selectedRow - 1, selectedColumn)) {
                                        boundary.expandTable(selectedRow, selectedColumn);
                                        break;
                                    }
                                }
                            } else {
                                int reachedRow = selectedRow;
                                int reachedColumn = selectedColumn;
                                Boolean re_route = true;
                                for (; reachedRow != -1
                                        && !isOutOfBounds(selectedColumn, excelMapper.get(reachedRow).size())
                                        && !isOutOfBounds(reachedRow, excelMapper.size())
                                        && !excelMapper.get(reachedRow).get(selectedColumn); reachedRow--) {
                                    //Reaching available most TOP
                                }
                                for (; reachedColumn != -1 && !excelMapper.get(selectedRow).get(reachedColumn); reachedColumn--) {
                                    //Reaching available most LEFT
                                }
                                if (reachedRow > -1) {
                                    for (var boundary : tableBoundaries) {
                                        if (boundary.containsBoth(selectedRow - 1, selectedColumn, reachedRow, selectedColumn)) {
                                            boundary.expandTable(selectedRow, selectedColumn);
                                            re_route = false;
                                            break;
                                        } else if (reachedColumn > -1 && boundary.containsBoth(reachedRow, selectedColumn, selectedRow, reachedColumn)) {
                                            boundary.expandTable(selectedRow, selectedColumn);
                                            re_route = false;
                                            break;
                                        }
                                    }
                                }
                                if (re_route) {
                                    tableBoundaries.add(new TBoundary(selectedRow, selectedColumn, selectedRow, selectedColumn));
                                }
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

}
