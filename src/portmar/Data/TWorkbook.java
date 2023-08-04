// Xlsx Data Container
package portmar.Data;

import java.util.ArrayList;

class TCell {

    public Object cell;
    public int key;

    public TCell(Object cell) {
        this.cell = cell;
    }

}

class TRow {

    public ArrayList<TCell> row;
    public int key;

    TRow() {
        this.row = new ArrayList<>();
    }

}

class TTable {

    public ArrayList<TRow> table;
    public int key;

    public TTable() {
        this.table = new ArrayList<>();
    }

}

class TSheet {

    public ArrayList<TTable> sheet;
    public int key;

    public TSheet() {
        this.sheet = new ArrayList<>();
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
