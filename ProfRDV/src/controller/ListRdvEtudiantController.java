package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import modele.Creneau;
import modele.RDV;
import modele.User;


public class ListRdvEtudiantController   implements Initializable   {

   User user= main.Main.user;

    Connection connection = null;
    PreparedStatement pst;
    PreparedStatement pst2;
    ResultSet rs;
    ResultSet rs2;
    
    ArrayList<RDV> rdv1 = new ArrayList<>();
    ArrayList<String> aux1 = new ArrayList<>();
    ArrayList<RDV> rdv2 = new ArrayList<>();
    ArrayList<String> aux2 = new ArrayList<>();


    @FXML
    VBox vbox1;

    @FXML
    VBox vbox2;

    private void affiche1(ArrayList<RDV> rdv1){
        for (RDV rdv : rdv1){
            Label prof = new Label();
            Label date = new Label();
            Label heure = new Label();
            Label motif = new Label();
            Label etat = new Label();
            prof.setText("prof : "+rdv.getpId());
            date.setText("Date : "+rdv.getCreneau().getDate());
            heure.setText("Heure : "+rdv.getCreneau().getHeure());
            motif.setText("Motif : "+rdv.getMotif());
            etat.setText("Etat : "+rdv.getEtat());

            try{
                Class.forName("org.sqlite.JDBC");
                connection = DriverManager.getConnection( "jdbc:sqlite:ProfRDV/src/database/data-2.db" );
                pst = connection.prepareStatement("select * from users where uId=(?)" );
                String temp = rdv.getpId();
                pst.setString(1,temp);
                ResultSet rs = pst.executeQuery();
                while(rs.next()){
                    prof.setText("Professeur : "+" "+rs.getString("Nom")+" "+ rs.getString("Prenom"));
                }


            } catch (Exception e) {
                System.out.println(""+e.getMessage());
            }

            vbox1.getChildren().add(prof);
            vbox1.getChildren().add(date);
            vbox1.getChildren().add(heure);
            vbox1.getChildren().add(motif);
            vbox1.getChildren().add(etat);
            for(int i=0; i<1;i++){
                Label vides = new Label();
                vbox1.getChildren().add(vides);

            }
        }
        
    }

    private void affiche2(ArrayList<RDV> rdv2){
        for (RDV rdv : rdv2){
            Label prof = new Label();
            Label date = new Label();
            Label heure = new Label();
            Label motif = new Label();
            Label etat = new Label();
            prof.setText("prof : "+rdv.getpId());
            date.setText("Date : "+rdv.getCreneau().getDate());
            heure.setText("Heure : "+rdv.getCreneau().getHeure());
            motif.setText("Motif : "+rdv.getMotif());
            etat.setText("Etat : "+rdv.getEtat());


            try{
                Class.forName("org.sqlite.JDBC");
                connection = DriverManager.getConnection( "jdbc:sqlite:ProfRDV/src/database/data-2.db" );
                pst = connection.prepareStatement("select * from users where uId=(?)" );
                String temp = rdv.getpId();
                pst.setString(1,temp);
                ResultSet rs = pst.executeQuery();
                while(rs.next()){
                    prof.setText("Professeur : "+" "+rs.getString("Nom")+" "+ rs.getString("Prenom"));
                }


            } catch (Exception e) {
                System.out.println(""+e.getMessage());
            }


            vbox2.getChildren().add(prof);
            vbox2.getChildren().add(date);
            vbox2.getChildren().add(heure);
            vbox2.getChildren().add(motif);
            vbox2.getChildren().add(etat);
            for(int i=0; i<1;i++){
                Label vides = new Label();
                vbox2.getChildren().add(vides);

            }
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

    @FXML
    public void gotoPriseRDVEleve(ActionEvent e) throws IOException{
        Stage stage = main.Main.getStage();
        Parent fxmlLoader = FXMLLoader.load(getClass().getResource("../view/priserdv.fxml"));
        stage.setScene(new Scene(fxmlLoader, 600, 500));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try{
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection( "jdbc:sqlite:ProfRDV/src/database/data-2.db" );
            pst = connection.prepareStatement("select * from rdv where eId=(?) and Etat=(?)" );
            String temp = this.user.eleve.geteId();
            pst.setString(1,temp);

            pst.setString(2,"Confirme");

            rs = pst.executeQuery();

           
        while (rs.next()) {

            String rid = rs.getString("rId");
            String pID = rs.getString("pId");
            String eID = rs.getString("eId");
            String motif = rs.getString("Motif");
            String etat = rs.getString("Etat"); 
            String date = rs.getString("Date");
            String heure = rs.getString("Heure");
            Creneau creneau = new Creneau(date,heure);
            RDV rdv = new RDV(rid,eID,pID,etat,creneau,motif);


            rdv1.add(rdv);
            

        }
        ////ADD connection close ?
    }
    catch (Exception e){
        System.out.println(""+e.getMessage());
    }

        affiche1(rdv1);

//////////// REMPLISSAGE DEUXIEME TABLE
        try{
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection( "jdbc:sqlite:ProfRDV/src/database/data-2.db" );
            pst2 = connection.prepareStatement("select * from rdv where eId=(?) and Etat=(?)" );
            String temp2 = this.user.eleve.geteId();
            pst2.setString(1,temp2);
            pst2.setString(2,"En attente");

            rs2 = pst2.executeQuery();

           

        while (rs2.next()) {

            String rid = rs2.getString("rId");
            String pID = rs2.getString("pId");
            String eID = rs2.getString("eId");
            String motif = rs2.getString("Motif");
            String etat = rs2.getString("Etat"); 
            String date = rs2.getString("Date");
            String heure = rs2.getString("Heure");
            Creneau creneau = new Creneau(date,heure);
            RDV rdv = new RDV(rid,eID,pID,etat,creneau,motif);
            rdv2.add(rdv);
            

        }
    }
    catch (Exception e){
        System.out.println(""+e.getMessage());
    }

        affiche2(rdv2);
    }


}



    
   



