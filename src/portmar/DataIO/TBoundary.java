package portmar.DataIO;

public class TBoundary {

    static int counter = 0;
    int id;
    int pos_colhead;
    int pos_rowhead;
    int pos_coltail;
    int pos_rowtail;

    public TBoundary(int pos_rowhead, int pos_colhead, int pos_rowtail, int pos_coltail) {
        this.id = counter;
        counter++;
        this.pos_colhead = pos_colhead;
        this.pos_rowhead = pos_rowhead;
        this.pos_coltail = pos_coltail;
        this.pos_rowtail = pos_rowtail;
    }

    public void expandTable(int pos_row, int pos_column) {
        if (pos_column > pos_coltail) {
            pos_coltail++;
        }
        if (pos_row > pos_rowtail) {
            pos_rowtail++;
        }
    }

    public Boolean contains(int row, int column) {
        return row >= pos_rowhead && row <= pos_rowtail && column >= pos_colhead && column <= pos_coltail;
    }

    public Boolean containsBoth(int row1, int column1, int row2, int column2) {
        if (row1 >= pos_rowhead && row1 <= pos_rowtail && column1 >= pos_colhead && column1 <= pos_coltail) {
            return row2 >= pos_rowhead && row2 <= pos_rowtail && column2 >= pos_colhead && column2 <= pos_coltail;
        } else {
            return false;
        }
    }
}
