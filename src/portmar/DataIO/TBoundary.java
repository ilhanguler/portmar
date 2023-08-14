package portmar.DataIO;

public class TBoundary {

        static int counter = 0;
        int id;
        int pos_colhead;
        int pos_rowhead;
        int pos_coltail;
        int pos_rowtail;

        public TBoundary(int pos_colhead, int pos_rowhead, int pos_coltail, int pos_rowtail) {
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
        
        public Boolean contains(int row, int column){
            if(row>=pos_rowhead && row<= pos_rowtail && column >= pos_colhead && column <= pos_coltail){
                return true;
            }else{
                return false;
            }
        }
    }
