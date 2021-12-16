package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import modele.RDV;

public class listerRDVProfControler {

    @FXML
    VBox vboxRDVAttente;

    @FXML
    Button close;


    public void addRDVenattente(){
        VBox v = new VBox();
        RDV r = new RDV();
        v.getChildren().addAll(new Label(r.geteId()));
        this.vboxRDVAttente.getChildren().add(v);
    }

    public void closeApplication(){
        Stage stage = (Stage) close.getScene().getWindow();
        stage.close();
    }
}
