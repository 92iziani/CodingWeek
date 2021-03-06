package controller;

import java.io.IOException;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import modele.User;

public class AdminPageController {

    @FXML
    VBox vboxRDVAttente;

    @FXML
    Button close;


    public void changeSceneAjoutEleve() throws IOException {
        Stage stage = main.Main.stage;
        Parent root = FXMLLoader.load(getClass().getResource("../view/priserdv.fxml"));
        stage.setTitle("Hello World");
        stage.setScene(new Scene(root, 600, 500));
    }

    public void changeSceneAjoutProf() throws IOException {
        Stage stage = main.Main.stage;
        Parent root = FXMLLoader.load(getClass().getResource("../view/priserdv.fxml"));
        stage.setTitle("Hello World");
        stage.setScene(new Scene(root, 600, 500));
    }

    public void changeSceneEnleverEleve() throws IOException {
        Stage stage = main.Main.stage;
        Parent root = FXMLLoader.load(getClass().getResource("../view/priserdv.fxml"));
        stage.setTitle("Hello World");
        stage.setScene(new Scene(root, 600, 500));
    }

    public void changeSceneEnleverProf() throws IOException {
        Stage stage = main.Main.stage;
        Parent root = FXMLLoader.load(getClass().getResource("../view/priserdv.fxml"));
        stage.setTitle("Hello World");
        stage.setScene(new Scene(root, 600, 500));
    }

    @FXML
    public void closeApplication(){
        Platform.exit();
    }

    @FXML
    public void seDeconnecter() throws IOException{
        Stage stage = main.Main.getStage();
        main.Main.user = new User();
        Parent fxmlLoader = FXMLLoader.load(getClass().getResource("../view/login.fxml"));
        stage.setScene(new Scene(fxmlLoader, 600, 500));
    }
}
