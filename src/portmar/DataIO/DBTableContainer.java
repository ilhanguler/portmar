package portmar.DataIO;

import java.util.ArrayList;

public class DBTableContainer {

    public static int MAX_EXPOSED;
    public int columnCount;
    public String tableName = new String();

    public ArrayList<String> columns = new ArrayList();
    public ArrayList<Class> columnType = new ArrayList();
    public ArrayList<ArrayList<Object>> exposedContent = new ArrayList();

    public DBTableContainer() {

    }

}
