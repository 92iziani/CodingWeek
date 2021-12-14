package sample.Controleur;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import sample.modele.RDV;

public class listerRDVProfControler {



    public void addRDVenattente(){
        Circle circle = new Circle(0.0, 0.0, 10);
        this.vboxRDVAttente.getChildren().add(circle);
    }

    public void closeApplication(){
        Stage stage = (Stage) close.getScene().getWindow();
        stage.close();
    }

}
