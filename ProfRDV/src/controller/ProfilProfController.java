package controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import modele.User;

public class ProfilProfController {

    User user = main.Main.user;
    
    @FXML
    TextField nom;

    @FXML
    TextField prenom;

    @FXML
    TextField id;

    @FXML
    TextField email;

    @FXML
    TextField login;

    public void initialize(){
        nom.setText(user.prof.getpName());
        id.setText(user.prof.getpId());
        email.setText(user.prof.getpEmail());
        login.setText(user.prof.getpEmail());
    }

}
