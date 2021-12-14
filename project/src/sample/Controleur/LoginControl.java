package sample.Controleur;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginControl {

    public void ConfirmBouttonEtudiant() throws IOException {
        Stage stage = sample.Main.stage;
        Parent root = FXMLLoader.load(getClass().getResource("../view/listerdvProf.fxml"));
        stage.setTitle("Hello World");
        stage.setScene(new Scene(root, 600, 500));


    }
}
