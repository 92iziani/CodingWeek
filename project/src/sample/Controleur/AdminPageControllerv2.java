package sample.Controleur;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javax.swing.text.html.HTML;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AdminPageControllerv2 {

    Connection connection = null;
    PreparedStatement pst;
    ResultSet rs;
    ResultSet rrs;

    @FXML
    VBox vboxRDVAttente;

    @FXML
    Button close;

    @FXML
    TextField id;

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

    @FXML
    VBox Utilisateurs;


     public void closeApplication(){
        Stage stage = (Stage) close.getScene().getWindow();
        stage.close();
    }

    public void getnom(){
         System.out.println(nom.getText());
    }

    public void getid(){
        System.out.println(id.getText());
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
            pst = connection.prepareStatement("INSERT INTO users (uId, Prenom, Nom, Type, Email, Login, Password) VALUES ((?), (?),(?),(?),(?),(?),(?));");
            pst.setString(1, id.getText());
            pst.setString(2, nom.getText());
            pst.setString(3, prenom.getText());
            pst.setString(4, type.getText());
            pst.setString(5, email.getText());
            pst.setString(6, login.getText());
            pst.setString(7, password.getText());

            pst.executeUpdate();

        } catch (Exception e) {
            System.out.println("" + e.getMessage());
        }
    }

    public void refresh(){
         
         Utilisateurs.getChildren().clear();

        try{
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection( "jdbc:sqlite:src/sample/Database/data.db" );
            pst = connection.prepareStatement("select * from users");

            rrs = pst.executeQuery();

            while (rrs.next()) {
                VBox v = new VBox();
                String id = rrs.getString("uID");
                String name = rrs.getString("Nom");
                String pre = rrs.getString("Prenom");
                System.out.println(name);
                Button b = new Button("supprimmer: " + id );
                b.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        delete(id);
                        refresh();
                    }
                });
                v.getChildren().addAll(new Label(name), new Label(pre));
                v.getChildren().addAll(b);
                this.Utilisateurs.getChildren().addAll(v);

            }
            connection.close();
        }
        catch (Exception e){
            System.out.println(""+e.getMessage());
        }
    }

    public void delete(String id){
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:src/sample/Database/data.db");
            pst = connection.prepareStatement("DELETE FROM users WHERE uId = (?);");
            pst.setString(1, id);


            pst.executeUpdate();

        } catch (Exception e) {
            System.out.println("" + e.getMessage());
        }
    }

}
