// Xlsx Data Container
package portmar.Data;

import java.util.ArrayList;
import java.util.HashMap;
import portmar.Data.TWorkbookEnumContainer.*;

class TCell {

    public Object cell;
    public String dataType;
    public int key;
    public int index;
    static int counter = 0;
    public int pos_row, pos_column;

    public TCell(HashMap<String, Object> cellContent, Object cell) {
        this.cell = cell;
        cell.getClass().getTypeName();
        index = counter;
        counter++;
    }

}

class TRow {

    public ArrayList<TCell> row;
    public int key;
    public int index;
    static int counter = 0;
    public int liveCellCount;

    public TRow(HashMap<String, Object> rowContent) {
        this.row = new ArrayList<>();
        index = counter;
        counter++;
    }

}

class TTable {

    public ArrayList<TRow> table;
    public int key;
    public int index;
    static int counter = 0;
    public int num_row, num_column;
    public int pos_x, pos_y;

    public TTable(HashMap<String, Object> tableContent) {
        this.table = new ArrayList<>();
        index = counter;
        counter++;
    }

}

class TSheet {

    public ArrayList<TTable> sheet;
    public int key;
    public int index;
    static int counter = 0;

    public TSheet(HashMap<String, Object> sheetContent) {
        this.sheet = new ArrayList<>();
        index = counter;
        counter++;
    }

}

// Representing high level Excel file content
public class TWorkbook {

    public ArrayList<TSheet> workbook;
    public int key;

    public TWorkbook() {
        this.workbook = new ArrayList<>();
    }

}
