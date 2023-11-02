package portmar;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import portmar.DataIO.XlsxIO;
import portmar.DynamicComponents.NodeExcel;
import portmar.DynamicComponents.NodeMaster;
import java.util.ArrayList;
import portmar.DynamicComponents.NodeDB;

public class MainFXMLController implements Initializable {

    NodeMaster node = new NodeMaster();
    int id = 0;
    @FXML
    private TabPane mainTabPane;

    @FXML
    private TreeView<String> treeDBList;

    @FXML
    private TreeView<String> treeFileList;

    @FXML
    private TreeView<String> treeMapList;

    @FXML
    private void createDB(ActionEvent event) {
        node.dbTree.get(0).test();
        node.dbTree.get(1).test();
    }

    @FXML
    private void openFile(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        FileChooser chooseFile = new FileChooser();
        chooseFile.setTitle("Dosya Seç");
        File excelFile = chooseFile.showOpenDialog(stage);

        node.dbTree.put(id, new NodeDB(excelFile.getName()));

        treeFileList.getRoot().getChildren().get(0).getChildren().add((TreeItem) node.dbTree.get(id));

        FXMLLoader loader = new FXMLLoader(getClass().getResource("DynamicComponents\\TabDBFXML.fxml"));
        loader.setController(node.dbTree.get(id).dbController);
        Tab tab = new Tab(excelFile.getName(), loader.load());
        mainTabPane.getTabs().add(tab);
        id++;
        System.out.println(mainTabPane.getTabs().indexOf(tab));
        
        if (excelFile != null) {
            XlsxIO test = new XlsxIO();
            test.importExcel(excelFile);
            test.writeImport();
            test.writeMapper();
            test.writeTableBoundaries();
            test.clearWBInstance();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.print("controller initialize...\n");
        
        treeFileList.setRoot(new TreeItem<>("root"));
        TreeItem<String> excelRoot = new TreeItem<>("Excel Dosyaları");
        treeFileList.getRoot().getChildren().add(excelRoot);
        
    }

}
