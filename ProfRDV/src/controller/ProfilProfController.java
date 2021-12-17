package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import modele.User;
import javafx.application.Platform;

public class ProfilProfController {

    Connection connection = null;
    PreparedStatement pst;
    ResultSet rs;
    ResultSet rrs;

    User user = main.Main.user;
    
    @FXML
    Label nomprenom;

    @FXML
    Label loginn;
    
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

    @FXML
    TextField pass;

    @FXML
    TextField type;




    public void initialize(){
        System.out.println("Hello World !");
        try{
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection( "jdbc:sqlite:ProfRDV/src/database/data-2.db" );
            pst = connection.prepareStatement("select * from users where uId = (?)");
            pst.setString(1, AdminPageControllerv2.Id);
    
            rrs = pst.executeQuery();
    
            id.setText(rrs.getString("uID"));
            nom.setText(rrs.getString("Nom")); 
            prenom.setText(rrs.getString("Prenom")); 
            type.setText(rrs.getString("Type")); 
            email.setText(rrs.getString("Email")); 
            login.setText(rrs.getString("Login")); 
            pass.setText(rrs.getString("Password")); 


            while (rrs.next()){
                this.nomprenom.setText(rrs.getString("Nom")+" "+rs.getString("Prenom"));
                this.loginn.setText(rrs.getString("Login"));
            }


            connection.close();
        }
        catch (Exception e){
            System.out.println(""+e.getMessage());
        }
        //id.setText(user.prof.getpId());
        //email.setText(user.prof.getpEmail());
        //login.setText(user.prof.getpEmail());
    }

    public void closeApplication(){
        Platform.exit();
    }

    @FXML
    public void seDeconnecter() throws IOException {
        Stage stage = main.Main.getStage();
        main.Main.user = new User();
        Parent fxmlLoader = FXMLLoader.load(getClass().getResource("../view/login.fxml"));
        stage.setScene(new Scene(fxmlLoader, 600, 500));
    }

    
    public void type(){

        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:ProfRDV/src/database/data-2.db");
            pst = connection.prepareStatement("UPDATE users SET Type = (?) where uId = (?);");
            pst.setString(1, type.getText());
            pst.setString(2, id.getText());
            pst.executeUpdate();
 
        } catch (Exception e) {
            System.out.println("" + e.getMessage());
        }
    }

    public void email(){

        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:ProfRDV/src/database/data-2.db");
            pst = connection.prepareStatement("UPDATE users SET Email = (?) where uId = (?);");
            pst.setString(1, email.getText());
            pst.setString(2, id.getText());
            pst.executeUpdate();
 
        } catch (Exception e) {
            System.out.println("" + e.getMessage());
        }

    }

    public void pass(){

        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:ProfRDV/src/database/data-2.db");
            pst = connection.prepareStatement("UPDATE users SET Password = (?) where uId = (?);");
            pst.setString(1, pass.getText());
            pst.setString(2, id.getText());
            pst.executeUpdate();
 
        } catch (Exception e) {
            System.out.println("" + e.getMessage());
        }

    }

    public void nom(){

        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:ProfRDV/src/database/data-2.db");
            pst = connection.prepareStatement("UPDATE users SET nom = (?) where uId = (?);");
            pst.setString(1, nom.getText());
            pst.setString(2, id.getText());
            pst.executeUpdate();
 
        } catch (Exception e) {
            System.out.println("" + e.getMessage());
        }

    }

    public void prenom(){

        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:ProfRDV/src/database/data-2.db");
            pst = connection.prepareStatement("UPDATE users SET Prenom = (?) where uId = (?);");
            pst.setString(1, prenom.getText());
            pst.setString(2, id.getText());
            pst.executeUpdate();
 
        } catch (Exception e) {
            System.out.println("" + e.getMessage());
        }

    }

    public void login(){
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:ProfRDV/src/database/data-2.db");
            pst = connection.prepareStatement("UPDATE users SET Login = (?) where uId = (?);");
            pst.setString(1, login.getText());
            pst.setString(2, id.getText());
            pst.executeUpdate();
 
        } catch (Exception e) {
            System.out.println("" + e.getMessage());
        }
    }

    public void id(){
        
    }

}
