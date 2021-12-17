package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import modele.User;

public class listerRDVProfControler implements Initializable{

    User user= main.Main.user;
    Connection connection = null;
    PreparedStatement pst;
   // ResultSet rs;

    @FXML
    VBox vboxRDVAttente;

    @FXML
    Button close;

    @FXML
    VBox confirme;

    @FXML
    Label nom; 

    @FXML 
    Label login;

    @FXML
    Button go;

    public void addRDVenattente(){
        vboxRDVAttente.getChildren().clear();

        try{
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection( "jdbc:sqlite:ProfRDV/src/database/data-2.db" );
            pst = connection.prepareStatement("select * from rdv join users on rdv.eId = users.uId where pId = (?) and Etat = (?) ");
            pst.setString(1, user.prof.getpId());
            pst.setString(2, "En attente");
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                VBox v = new VBox();
                String id = rs.getString("rId");
                String nom = rs.getString("Nom");
                String prenom = rs.getString("Prenom");
                String date = rs.getString("Date");
                String heure = rs.getString("Heure");
                String motif = rs.getString("Motif");

                Button b = new Button("Confirmer "+" \u2714 ");

                b.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        accepter(id);
                        addRDVenattente();
                    }
                });
                Button bb = new Button("Refuser "+" \u2716 ");
                bb.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        refuser(id);
                        addRDVenattente();
                    }
                });
                v.getChildren().addAll( new Label(nom+"  "+prenom), new Label("Date : "+date), new Label("Heure : "+heure),new Label("Motif : "+motif),b, bb);
                this.vboxRDVAttente.getChildren().addAll(v);

            }
            connection.close();
        }
        catch (Exception e){
            System.out.println(""+e.getMessage());
        }
    }

    public void addRDVencomfirme(){
        confirme.getChildren().clear();

        try{
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection( "jdbc:sqlite:ProfRDV/src/database/data-2.db" );
            pst = connection.prepareStatement("select * from rdv join users on rdv.eId = users.uId where pId = (?) and Etat = (?)");
            pst.setString(1, user.prof.getpId());
            pst.setString(2, "Confirme");
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                VBox v = new VBox();
                String id = rs.getString("rId");
                String nom = rs.getString("Nom");
                String prenom = rs.getString("Prenom");
                String date = rs.getString("Date");
                String heure = rs.getString("Heure");
                String motif = rs.getString("Motif");
                Button b = new Button("Annuler "+" \u2716 "); 
                b.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        delete(id);
                        addRDVencomfirme();
                    }
                });

                v.getChildren().addAll( new Label(nom+"  "+prenom), new Label("Date : "+date), new Label("Heure : "+heure),new Label("Motif : "+motif),b);
                this.confirme.getChildren().addAll(v);

            }
            connection.close();
        }
        catch (Exception e){
            System.out.println(""+e.getMessage());
        }

    }

    public void accepter(String id){
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:ProfRDV/src/database/data-2.db");
            pst = connection.prepareStatement("Update rdv set Etat = (?) WHERE rId = (?);");
            pst.setString(1, "Confirme");
            pst.setString(2, id);
            pst.executeUpdate();
            connection.close();
        } catch (Exception e) {
            System.out.println("" + e.getMessage());
        }
    }

    public void refuser(String id){
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:ProfRDV/src/database/data-2.db");
            pst = connection.prepareStatement("DELETE FROM rdv WHERE rId = (?);");
            pst.setString(1, id);
            pst.executeUpdate();
            connection.close();
        } catch (Exception e) {
            System.out.println("" + e.getMessage());
        }
    }

    public void delete(String id){
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:ProfRDV/src/database/data-2.db");
            pst = connection.prepareStatement("DELETE FROM rdv WHERE rId = (?);");
            pst.setString(1, id);
            pst.executeUpdate();
            connection.close();
        } catch (Exception e) {
            System.out.println("" + e.getMessage());
        }
    }




    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try{
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection( "jdbc:sqlite:ProfRDV/src/database/data-2.db" );
             PreparedStatement pst = connection.prepareStatement("select * from users  where uId = (?) ");
            pst.setString(1, user.prof.getpId());
            ResultSet rs = pst.executeQuery();

            while (rs.next()){
                this.nom.setText(rs.getString("Nom")+" "+rs.getString("Prenom"));
                this.login.setText(rs.getString("Login"));
            }
        } catch (Exception e) {
            System.out.println("" + e.getMessage());
        }
    
        addRDVencomfirme();
        addRDVenattente();

        
       

        
    }

    @FXML
    public void seDeconnecter() throws IOException{
        Stage stage = main.Main.getStage();
        main.Main.user = new User();
        Parent fxmlLoader = FXMLLoader.load(getClass().getResource("../view/login.fxml"));
        stage.setScene(new Scene(fxmlLoader, 600, 500));
    }

    @FXML
    public void closeApplication(){
        Platform.exit();
    }

    @FXML
        public void go() throws IOException{
            Stage stage = main.Main.getStage();
            Parent fxmlLoader = FXMLLoader.load(getClass().getResource("../view/profil_prof.fxml"));
            stage.setScene(new Scene(fxmlLoader, 600, 500));
        }

}
