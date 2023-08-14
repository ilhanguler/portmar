// Xlsx Data Container
package portmar.DataIO;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashMap;
import portmar.DataIO.TWorkbookEnumContainer.*;

class TCell {

    public Object cell;
    public String dataType;
    public int key;
    public int index;
    static int counter = 0;
    public int pos_row, pos_column;

    public TCell(EnumMap<cellTrait, Object> cellContent, Object cell) {
        this.cell = cell;
        if (cell != null) {
            dataType = cell.getClass().getTypeName();
        }
        index = counter;
        counter++;
    }

}

class TRow {

    public ArrayList<TCell> row;
    public int key;
    public int index;
    static int counter = 0;
    public int relative_pos;
    public int real_pos;

    public TRow(EnumMap<rowTrait, Object> rowContent) {
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

    public TTable(EnumMap<tableTrait, Object> tableContent) {
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

    public TSheet(EnumMap<sheetTrait, Object> sheetContent) {
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
