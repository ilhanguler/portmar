package portmar;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.fxml.*;
import javafx.stage.FileChooser;
import javax.swing.text.View;
import portmar.DataIO.XlsxIO;

public class Portmar extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        System.out.print("portmar start...\n");
        Parent root = FXMLLoader.load(getClass().getResource("MainFXML.fxml"));
        primaryStage.setTitle("PORTMAR");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void main(String[] args) {
        System.out.print("portmar main...\n");

        XlsxIO test = new XlsxIO();
        test.importExcel("test.xlsx");
        test.writeImport();
        test.writeMapper();
        test.writeTableBoundaries();
        test.clearWBInstance();
        launch(args);

    }

}
