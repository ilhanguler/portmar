
// Table Data Structure

package portmar.Data;

import java.util.ArrayList;

// Row
class TRow {
    public ArrayList<String> row;
    public int key;

    TRow() {
        this.row = new ArrayList<>();
    }
    
}

// Collection of Rows = Table
class TableContent {
    public ArrayList<TRow> table;
    public int key;

    public TableContent() {
        this.table = new ArrayList<>();
    }
    
}

// Collection of Tables = Sheet
public class TCollection{
    ArrayList<TableContent> tCollection;
    public int key;

    public TCollection() {
        this.tCollection = new ArrayList<>();
    }
    
}
