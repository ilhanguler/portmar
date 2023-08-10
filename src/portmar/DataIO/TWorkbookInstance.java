package portmar.DataIO;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashMap;
import org.apache.commons.collections4.map.HashedMap;
import portmar.DataIO.TWorkbookEnumContainer.*;

public class TWorkbookInstance extends TWorkbook {
    
    public TSheet getTSheetContainer(int sheetnum) {
        return workbook.get(sheetnum);
    }

    public TTable getTTableContainer(int sheetnum, int tablenum) {
        return getTSheet(sheetnum).get(tablenum);
    }

    public TRow getTRowContainer(int sheetnum, int tablenum, int rownum) {
        return getTTable(sheetnum, tablenum).get(rownum);
    }

    public TCell getTCellContainer(int sheetnum, int tablenum, int rownum, int cellnum) {
        return getTRow(sheetnum, tablenum, rownum).get(cellnum);
    }

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

    public void addTSheet(EnumMap<sheetTrait,Object> sheetContent) {
        workbook.add(new TSheet(sheetContent));
    }

    public void addTTable(int sheetnum, EnumMap<tableTrait,Object> tableContent) {
        if (workbook.size() > sheetnum) {
            getTSheet(sheetnum).add(new TTable(tableContent));
        }
    }

    public void addTRow(int sheetnum, int tablenum, EnumMap<rowTrait,Object> rowContent) {
        if (workbook.size() > sheetnum && getTSheet(sheetnum).size() > tablenum) {
            getTTable(sheetnum, tablenum).add(new TRow(rowContent));
        }
    }

    public void addTCell(int sheetnum, int tablenum, int rownum, Object data, EnumMap<cellTrait,Object> cellContent) {
        if (workbook.size() > sheetnum && getTSheet(sheetnum).size() > tablenum && getTTable(sheetnum, tablenum).size() > rownum) {
            getTRow(sheetnum, tablenum, rownum).add(new TCell(cellContent, data));
        }
    }

    public void detectTable() {

    }

}
