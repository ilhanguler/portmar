package portmar.Data;

import java.util.ArrayList;
import java.util.HashMap;
import org.apache.commons.collections4.map.HashedMap;

public class TWorkbookInstance extends TWorkbook {

    public ArrayList<TSheet> getTWorkBook() {
        return workbook;
    }

    public ArrayList<TTable> getTSheet(int sheetnum) {
        return workbook.get(sheetnum).sheet;
    }

    public ArrayList<TRow> getTTable(int sheetnum, int tablenum) {
        return getTSheet(sheetnum).get(tablenum).table;
    }

    public ArrayList<TCell> getTRow(int sheetnum, int tablenum, int rownum) {
        return getTTable(sheetnum, tablenum).get(rownum).row;
    }

    public Object getTCell(int sheetnum, int tablenum, int rownum, int cellnum) {
        return getTRow(sheetnum, tablenum, rownum).get(cellnum).cell;
    }

    public void setTCell(int sheetnum, int tablenum, int rownum, int cellnum, Object content) {
        getTRow(sheetnum, tablenum, rownum).get(cellnum).cell = content;
    }

    public void addTSheet(HashMap<String,Object> sheetContent) {
        workbook.add(new TSheet(sheetContent));
    }

    public void addTTable(int sheetnum, HashMap<String,Object> tableContent) {
        if (workbook.size() > sheetnum) {
            getTSheet(sheetnum).add(new TTable(tableContent));
        }
    }

    public void addTRow(int sheetnum, int tablenum, HashMap<String,Object> rowContent) {
        if (workbook.size() > sheetnum & getTSheet(sheetnum).size() > tablenum) {
            getTTable(sheetnum, tablenum).add(new TRow(rowContent));
        }
    }

    public void addTCell(int sheetnum, int tablenum, int rownum, Object content, HashMap<String,Object> cellContent) {
        if (workbook.size() > sheetnum & getTSheet(sheetnum).size() > tablenum & getTTable(sheetnum, tablenum).size() > rownum) {
            getTRow(sheetnum, tablenum, rownum).add(new TCell(cellContent, content));
        }
    }

    public void detectTable() {

    }

}
