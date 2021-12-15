package sample.Controleur;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.w3c.dom.Text;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AdminPageControllerv2 {

    Connection connection = null;
    PreparedStatement pst;
    ResultSet rs;

    @FXML
    VBox vboxRDVAttente;

    @FXML
    Button close;

    @FXML
    TextField nom;

    @FXML
    TextField prenom;

    @FXML
    TextField type;

    @FXML
    TextField email;

    @FXML
    TextField login;

    @FXML
    TextField password;

    @FXML
    Button ajouter;

    @FXML
    Button Refresh;


     public void closeApplication(){
        Stage stage = (Stage) close.getScene().getWindow();
        stage.close();
    }

    public void getnom(){
         System.out.println(nom.getText());
    }

    public void getprenom(){
        System.out.println(prenom.getText());
    }

    public void gettype(){
        System.out.println(type.getText());
    }

    public void getemail(){
        System.out.println(email.getText());
    }

    public void getlogin(){
        System.out.println(login.getText());
    }

    public void getpassword(){
        System.out.println(password.getText());
    }


    public void ajouter() {

        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:src/sample/Database/data.db");
            pst = connection.prepareStatement("INSERT INTO users (uId, Prenom, Nom, Type, Email, Login, Password) VALUES(1111, (?),(?),(?),(?),(?),(?));");
            pst.setString(1, nom.getText());
            pst.setString(2, prenom.getText());
            pst.setString(3, type.getText());
            pst.setString(4, email.getText());
            pst.setString(5, login.getText());
            pst.setString(6, password.getText());

            pst.executeUpdate();

        } catch (Exception e) {
            System.out.println("" + e.getMessage());
        }
    }

    public void refresh(){

        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:src/sample/Database/data.db");
            pst = connection.prepareStatement("SELECT * FROM users;");
            pst.setString(1, nom.getText());
            pst.setString(2, prenom.getText());
            pst.setString(3, type.getText());
            pst.setString(4, email.getText());
            pst.setString(5, login.getText());
            pst.setString(6, password.getText());

            pst.executeUpdate();

        } catch (Exception e) {
            System.out.println("" + e.getMessage());
        }
    }

}
