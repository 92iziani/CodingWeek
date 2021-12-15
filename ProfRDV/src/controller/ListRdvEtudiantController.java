package controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
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
    ArrayList<RDV> rdv2 = new ArrayList<>();

    @FXML
    VBox vbox1;

    @FXML
    VBox vbox2;

    private void affiche1(ArrayList<RDV> rdv1){
        for (RDV rdv : rdv1){
            Label prof = new Label();
            Label date = new Label();
            Label motif = new Label();
            Label etat = new Label();
            prof.setText("prof : "+rdv.getpId());
            date.setText("date : "+rdv.getCreneau().getDate());
            motif.setText("motif : "+rdv.getMotif());
            etat.setText("état : "+rdv.getEtat());

            vbox1.getChildren().add(prof);
            vbox1.getChildren().add(date);
            vbox1.getChildren().add(motif);
            vbox1.getChildren().add(etat);
        }
        
    }

    private void affiche2(ArrayList<RDV> rdv2){
        for (RDV rdv : rdv2){
            Label prof = new Label();
            Label date = new Label();
            Label motif = new Label();
            Label etat = new Label();
            prof.setText("prof : "+rdv.getpId());
            date.setText("date : "+rdv.getCreneau().getDate());
            motif.setText("motif : "+rdv.getMotif());
            etat.setText("état : "+rdv.getEtat());

            vbox2.getChildren().add(prof);
            vbox2.getChildren().add(date);
            vbox2.getChildren().add(motif);
            vbox2.getChildren().add(etat);
        }
        
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println(this.user.getTest());
        try{
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection( "jdbc:sqlite:data-2.db" );
            pst = connection.prepareStatement("select * from rdv where eId=(?) and Etat=(?)" );
            String temp = this.user.eleve.geteId();
            System.out.println(temp);
            pst.setString(1,temp);

            pst.setString(2,"Confirme");

            rs = pst.executeQuery();

           
        while (rs.next()) {
            System.out.println("ffff");

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
            connection = DriverManager.getConnection( "jdbc:sqlite:data-2.db" );
            pst2 = connection.prepareStatement("select * from rdv where eId=(?) and Etat=(?)" );
            String temp2 = this.user.eleve.geteId();
            System.out.println(temp2);
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



    
   



