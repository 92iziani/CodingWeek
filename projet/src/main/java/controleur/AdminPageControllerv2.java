package controleur;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminPageControllerv2 {

    @FXML
    VBox vboxRDVAttente;

    @FXML
    Button close;


     public void closeApplication(){
        Stage stage = (Stage) close.getScene().getWindow();
        stage.close();
    }
}
