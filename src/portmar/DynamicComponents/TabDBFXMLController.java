package portmar.DynamicComponents;

import portmar.MetaData.MetaDataDB;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class TabDBFXMLController implements Initializable {
    
    int counter = 0;
    
    @FXML
    void buttonEkle(ActionEvent event) {
        counter++;
    }
    
    @FXML
    void buttonIptal(ActionEvent event) {
        System.out.println(counter);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
}
