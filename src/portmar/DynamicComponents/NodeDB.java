package portmar.DynamicComponents;

import java.util.ArrayList;
import javafx.scene.Node;
import portmar.MetaData.MetaDataDB;
import javafx.scene.control.TreeItem;

public class NodeDB extends TreeItem<String> {

    public TabDBFXMLController dbController = new TabDBFXMLController();
    MetaDataDB dbMeta = new MetaDataDB();
    Boolean isTotalAbstract = false;
    Boolean isSafeToDelete = true;
    Integer hash = null;
    Integer hashTarget = null;
    ArrayList<Integer> hashDestinations = new ArrayList<>();

    public NodeDB() {
    }

    public NodeDB(String t) {
        super(t);
    }

    public NodeDB(String t, Node node) {
        super(t, node);
    }
    
    public void test(){
        System.out.println(dbController.counter);
    }

}
