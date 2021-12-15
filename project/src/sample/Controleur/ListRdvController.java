import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.ResourceBundle;




public class ListRdvController  implements Initializable   {
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
        //affiche(rdvs);
        
        try{
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection( "jdbc:sqlite:data.db" );
            pst = connection.prepareStatement("select * from rdv where eId=(?) and etat=(?)" );
            pst.setString(1,"1");
            pst.setString(2,"confirmé");

            rs = pst.executeQuery();

           


        while (rs.next()) {

            int rid = rs.getInt("rId");
            String pID = rs.getString("pId");
            String eID = rs.getString("eId");
            String motif = rs.getString("motif");
            String etat = rs.getString("etat"); 
            String date = rs.getString("date");
            String heure = rs.getString("heure");
            Creneau creneau = new Creneau(date,heure);
            RDV rdv = new RDV(rid,eID,pID,etat,creneau,motif);
            rdv1.add(rdv);
            

        }
    }
    catch (Exception e){
        System.out.println(""+e.getMessage());
    }

        affiche1(rdv1);

//////////// REMPLISSAGE DEUXIEME TABLE
        try{
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection( "jdbc:sqlite:data.db" );
            pst2 = connection.prepareStatement("select * from rdv where eId=(?) and etat=(?)" );
            pst2.setString(1,"1");
            pst2.setString(2,"en attente");

            rs2 = pst2.executeQuery();

           

        while (rs2.next()) {

            int rid = rs2.getInt("rId");
            String pID = rs2.getString("pId");
            String eID = rs2.getString("eId");
            String motif = rs2.getString("motif");
            String etat = rs2.getString("etat"); 
            String date = rs2.getString("date");
            String heure = rs2.getString("heure");
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



    
   



