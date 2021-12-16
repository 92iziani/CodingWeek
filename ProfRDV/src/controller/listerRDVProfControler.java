package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import modele.Prof;
import modele.User;

public class listerRDVProfControler {

    Connection connection = null;
    PreparedStatement pst;
    ResultSet rs;
    Prof p = new Prof();

    @FXML
    VBox vboxRDVAttente;

    @FXML
    Button close;

    @FXML
    VBox confirme;


    public void addRDVenattente(){
        vboxRDVAttente.getChildren().clear();

        try{
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection( "jdbc:sqlite:ProfRDV/src/database/data-2.db" );
            pst = connection.prepareStatement("select * from rdv where pId = 100 and Etat = (?)");
            pst.setString(1, "En attente");
            rs = pst.executeQuery();

            while (rs.next()) {
                VBox v = new VBox();
                String id = rs.getString("rId");
                String date = rs.getString("Date");
                String etat = rs.getString("Etat");
                Button b = new Button("accepter: " + id );
                b.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        accepter(id);
                        addRDVenattente();
                    }
                });
                Button bb = new Button("refuser : " + id);

                bb.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        refuser(id);
                        addRDVenattente();
                    }
                });
                v.getChildren().addAll( new Label(date), new Label(etat), b, bb);
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
            pst = connection.prepareStatement("select * from rdv where pId = (?) and Etat = (?)");
            pst.setString(1, p.getpId());
            pst.setString(2, "Confirme");
            rs = pst.executeQuery();

            while (rs.next()) {
                VBox v = new VBox();
                String id = rs.getString("rId");
                String date = rs.getString("Date");
                String etat = rs.getString("Etat");
                Button b = new Button("supprimer: " + id );
                b.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        delete(id);
                        addRDVencomfirme();
                    }
                });

                v.getChildren().addAll( new Label(date), new Label(etat), b);
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

        } catch (Exception e) {
            System.out.println("" + e.getMessage());
        }
    }

    public void refuser(String id){
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:ProfRDV/src/database/data-2.db");
            pst = connection.prepareStatement("Update rdv set Etat = (?) WHERE rId = (?);");
            pst.setString(1, "Resfuse");
            pst.setString(2, id);



            pst.executeUpdate();

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

        } catch (Exception e) {
            System.out.println("" + e.getMessage());
        }
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
