package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

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