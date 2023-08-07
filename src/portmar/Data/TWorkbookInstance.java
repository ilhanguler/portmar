package portmar.Data;

import java.util.ArrayList;

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

    public void addTSheet() {
        workbook.add(new TSheet());
    }

    public void addTTable(int sheetnum) {
        if (workbook.size() > sheetnum) {
            getTSheet(sheetnum).add(new TTable());
        }
    }

    public void addTRow(int sheetnum, int tablenum) {
        if (workbook.size() > sheetnum & getTSheet(sheetnum).size() > tablenum) {
            getTTable(sheetnum, tablenum).add(new TRow());
        }
    }

    public void addTCell(int sheetnum, int tablenum, int rownum) {
        if (workbook.size() > sheetnum & getTSheet(sheetnum).size() > tablenum & getTTable(sheetnum, tablenum).size() > rownum) {
            getTRow(sheetnum, tablenum, rownum).add(new TCell(new Object()));
        }
    }
}
