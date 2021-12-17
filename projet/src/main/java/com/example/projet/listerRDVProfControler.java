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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import modele.User;

public class listerRDVProfControler {

    //ADDED
    Image image = new Image(getClass().getResourceAsStream("../view/valider.png"));
   //Image image = new Image("valider.png");
    ImageView view = new ImageView(image);
    

    //ADDED
    //view.setFitHeight(80);
    //view.setPreserveRatio(true);

    User user= main.Main.user;
    Connection connection = null;
    PreparedStatement pst;
    ResultSet rs;

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
            pst = connection.prepareStatement("select * from rdv join users on rdv.eId = users.uId where pId = (?) and Etat = (?) ");
            pst.setString(1, user.prof.getpId());
            pst.setString(2, "En attente");

            rs = pst.executeQuery();

            while (rs.next()) {
                VBox v = new VBox();
                String id = rs.getString("rId");
                String nom = rs.getString("Nom");
                String prenom = rs.getString("Prenom");
                //Button b = new Button("accepter: " + id );
                view.setFitHeight(30);
                view.setPreserveRatio(true);
                Button b = new Button();
                b.setPrefSize(10, 10);
                b.setGraphic(view);

                b.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        accepter(id);
                        addRDVenattente();
                    }
                });
                //Button bb = new Button("refuser : " + id);
                view.setFitHeight(30);
                view.setPreserveRatio(true);
                Button bb = new Button();
                bb.setPrefSize(10, 10);
                bb.setGraphic(view);

                bb.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        refuser(id);
                        addRDVenattente();
                    }
                });
                v.getChildren().addAll( new Label(nom), new Label(prenom), b, bb);
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
            rs = pst.executeQuery();

            while (rs.next()) {
                VBox v = new VBox();
                String id = rs.getString("rId");
                String nom = rs.getString("Nom");
                String prenom = rs.getString("Prenom");
                //Button b = new Button("supprimer: " + id );
                view.setFitHeight(30);
                view.setPreserveRatio(true);
                Button b = new Button();
                b.setPrefSize(10, 10);
                b.setGraphic(view);
                b.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        delete(id);
                        addRDVencomfirme();
                    }
                });

                v.getChildren().addAll( new Label(nom), new Label(prenom), b);
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
            pst = connection.prepareStatement("Update rdv set Etat = (?) WHERE rId = (?);");
            pst.setString(1, "Refuse");
            pst.setString(2, id);



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

    @FXML
    public void closeApplication(){
        Platform.exit();
    }
    public void initialize(){
        System.out.println("cc");
        try{
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection( "jdbc:sqlite:ProfRDV/src/database/data-2.db" );
            pst = connection.prepareStatement("select * from rdv join users on rdv.eId = users.uId where pId = (?) and Etat = (?)");
            pst.setString(1, user.prof.getpId());
            pst.setString(2, "Confirme");
            rs = pst.executeQuery();

            while (rs.next()) {
                VBox v = new VBox();
                String id = rs.getString("rId");
                String nom = rs.getString("Nom");
                String prenom = rs.getString("Prenom");
                //Button b = new Button("supprimer: " + id );
                view.setFitHeight(30);
                view.setPreserveRatio(true);
                Button b = new Button();
                b.setPrefSize(10, 10);
                b.setGraphic(view);
                b.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        delete(id);
                        addRDVencomfirme();
                    }
                });

                v.getChildren().addAll( new Label(nom), new Label(prenom), b);
                this.confirme.getChildren().addAll(v);

            }
            connection.close();
        }
        catch (Exception e){
            System.out.println(""+e.getMessage());
        }

        try{
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection( "jdbc:sqlite:ProfRDV/src/database/data-2.db" );
            pst = connection.prepareStatement("select * from rdv join users on rdv.eId = users.uId where pId = (?) and Etat = (?)");
            pst.setString(1, this.user.prof.getpId());
            pst.setString(2, "En attente");

            rs = pst.executeQuery();

            while (rs.next()) {
                VBox v = new VBox();
                String id = rs.getString("rId");
                String name = rs.getString("Nom");
                String prenom = rs.getString("Prenom");
                //Button b = new Button("accepter: " + id );
                view.setFitHeight(30);
                view.setPreserveRatio(true);
                Button b = new Button();
                b.setPrefSize(10, 10);
                b.setGraphic(view);
                b.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        accepter(id);
                        addRDVenattente();
                    }
                });
               // Button bb = new Button("refuser : " + id);
               view.setFitHeight(30);
                view.setPreserveRatio(true);
               Button bb = new Button();
               bb.setPrefSize(10, 10);

               bb.setGraphic(view);

                bb.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        refuser(id);
                        addRDVenattente();
                    }
                });
                v.getChildren().addAll( new Label(name), new Label(prenom), b, bb);
                this.vboxRDVAttente.getChildren().addAll(v);

            }
            connection.close();
        }
        catch (Exception e){
            System.out.println(""+e.getMessage());
        }
        System.out.println("fin");
    }

    @FXML
    public void seDeconnecter() throws IOException{
        Stage stage = main.Main.getStage();
        main.Main.user = new User();
        Parent fxmlLoader = FXMLLoader.load(getClass().getResource("../view/login.fxml"));
        stage.setScene(new Scene(fxmlLoader, 600, 500));
    }

}
