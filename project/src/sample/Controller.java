package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller {

    public void ConfirmBouttonEtudiant() throws IOException {
        Stage stage = Main.stage;
        Parent root = FXMLLoader.load(getClass().getResource("listerdv.fxml"));
        stage.setTitle("Hello World");
        stage.setScene(new Scene(root, 600, 500));


    }
}
