package portmar.DynamicComponents;

import portmar.MetaData.MetaDataMap;
import java.util.ArrayList;
import javafx.scene.Node;
import javafx.scene.control.TreeItem;

public class NodeMap extends TreeItem<String> {

    TabMapFXMLController mapController = new TabMapFXMLController();
    MetaDataMap mapMeta = new MetaDataMap();
    Boolean isSafeToDelete = false;
    Integer hash = null;
    ArrayList<Integer> hashTargets = new ArrayList<>();
    ArrayList<Integer> hashDestinations = new ArrayList<>();

    public NodeMap() {
    }

    public NodeMap(String t) {
        super(t);
    }

    public NodeMap(String t, Node node) {
        super(t, node);
    }

    void addTarget(Integer hash) {
        hashTargets.add(hash);
    }

    void addDestination(Integer hash) {
        hashDestinations.add(hash);
    }

    void setMeta(MetaDataMap meta) {
        mapMeta = meta;
    }
}
