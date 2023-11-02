package portmar.DynamicComponents;

import java.util.ArrayList;
import javafx.scene.Node;
import portmar.MetaData.MetaDataExcel;
import javafx.scene.control.TreeItem;

public class NodeExcel extends TreeItem<String> {

    public TabExcelFXMLController excelControllers = new TabExcelFXMLController();
    MetaDataExcel excelMeta = new MetaDataExcel();
    Boolean isTotalAbstract = false;
    Boolean isSafeToDelete = true;
    Integer hash = null;
    Integer hashTarget = null;
    ArrayList<Integer> hashDestinations = new ArrayList<>();

    public NodeExcel() {
    }

    public NodeExcel(String t) {
        super(t);
    }

    public NodeExcel(String t, Node node) {
        super(t, node);
    }
}
