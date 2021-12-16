package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import modele.User;

public class profil_prof_controller implements Initializable{
    User user = main.Main.user;


    // VARIABLES DE CLASSE
    Connection connection = null;
    
    // VARIABLES FXML
    @FXML 
    private ChoiceBox<String> jourChoicebox;
    @FXML 
    private ChoiceBox<String> debutChoicebox;
    @FXML
    private ChoiceBox<String> finChoicebox;
    @FXML
    private Button ajouter;
    @FXML
    private Button quitterButton;



    // FONCTIONS FXML
    @FXML
    private void closeApplication(ActionEvent e) {
        Platform.exit();
    }


    // INITIALIZE
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        jourChoicebox.getItems().add("Lundi");
        jourChoicebox.getItems().add("Mardi");
        jourChoicebox.getItems().add("Mercredi");
        jourChoicebox.getItems().add("Jeudi");
        jourChoicebox.getItems().add("Vendredi");
        
        for (int i = 8*60 ; i < 18*60 - 10; i = i+10) {
            if (i%60 == 0){
                debutChoicebox.getItems().add(String.valueOf(i/60)+":0"+String.valueOf(i%60));
                finChoicebox.getItems().add(String.valueOf(i/60)+":0"+String.valueOf(i%60));
            } else {
                debutChoicebox.getItems().add(String.valueOf(i/60)+":"+String.valueOf(i%60));
                finChoicebox.getItems().add(String.valueOf(i/60)+":"+String.valueOf(i%60));
            }
        }
    }

    @FXML
    private void ajouterButton(ActionEvent e) throws IOException{
        if (jourChoicebox.getSelectionModel().getSelectedItem() != null && debutChoicebox.getSelectionModel().getSelectedItem() != null && debutChoicebox.getSelectionModel().getSelectedItem() != null) {
            String jour = jourChoicebox.getSelectionModel().getSelectedItem();
            String debut = debutChoicebox.getSelectionModel().getSelectedItem();
            String fin = finChoicebox.getSelectionModel().getSelectedItem();
            try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection( "jdbc:sqlite:ProfRDV/src/database/data-2.db" );
            String pId =  this.user.prof.getpId();
            PreparedStatement pst = connection.prepareStatement("insert into disponibiltes (pid, Date, Heure, Heurefin) values ((?), (?), (?), (?))" );
            pst.setString(1, pId);
            pst.setString(2, jour);
            pst.setString(3, debut);
            pst.setString(4, fin);
            pst.execute();
            connection.close();
            }
            catch (Exception e1){
                System.out.println(""+e1.getMessage());
            }
            Alert dialog = new Alert(AlertType.INFORMATION);
            dialog.setTitle("ProfRDV de TN");
            dialog.setContentText("Votre disponibilité a été ajoutée !");
            dialog.showAndWait();
        }   
    }
}
