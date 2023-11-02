// Xlsx Data Container
package portmar.DataIO;

import java.util.ArrayList;
import java.util.EnumMap;
import portmar.DataIO.TWorkbookEnumContainer.*;

class TCell {

    public Object cell;
    public String dataType;
    static int counter = 0;
    public int pos_row, pos_column;

    public TCell(EnumMap<cellTrait, Object> cellContent, Object cell) {
        this.cell = cell;
        if (cell != null) {
            dataType = cell.getClass().getTypeName();
        }
        counter++;
        pos_row = (int) cellContent.get(cellTrait.POS_ROW);
        pos_column = (int) cellContent.get(cellTrait.POS_COLUMN);
    }

}

class TRow {

    public ArrayList<TCell> row;
    static int counter = 0;

    public TRow(EnumMap<rowTrait, Object> rowContent) {
        this.row = new ArrayList<>();
        counter++;
    }

}

class TTable {

    public ArrayList<TRow> table;
    static int counter = 0;

    public TTable(EnumMap<tableTrait, Object> tableContent) {
        this.table = new ArrayList<>();
        counter++;
    }

}

class TSheet {

    public ArrayList<TTable> sheet;
    static int counter = 0;

    public TSheet(EnumMap<sheetTrait, Object> sheetContent) {
        this.sheet = new ArrayList<>();
        counter++;
    }

}

// Representing high level Excel file content
public class TWorkbook{

    public ArrayList<TSheet> workbook;

    public TWorkbook() {
        this.workbook = new ArrayList<>();
    }

}
