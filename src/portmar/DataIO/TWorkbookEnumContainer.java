package portmar.DataIO;

public class TWorkbookEnumContainer {

    public enum cellTrait {
        KEY,
        INDEX,
        POS_ROW,
        POS_COLUMN
    }

    public enum rowTrait {
        KEY,
        LIVECELLCOUNT
    }

    public enum tableTrait {
        KEY,
        NUM_ROW,
        NUM_COLUMN,
        POS_X,
        POS_Y
    }

    public enum sheetTrait {
        KEY
    }
}
