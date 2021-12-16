package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import modele.CreneauxUsuels;
import modele.Prof;
import modele.User;


public class vuePriseRDVEtudiantController implements Initializable{

    //I NIT BD
    Connection connection = null;
    PreparedStatement pst;
    PreparedStatement pst2;
    ResultSet rs, rs2;

    // AUTRES
    ArrayList<Prof> profs = new ArrayList<Prof>();
    User user = main.Main.user;

    // CONTROLES FXML
    @FXML 
    private ChoiceBox<String> profChoicebox;
    @FXML
    private ChoiceBox<String> heureChoicebox;
    @FXML
    private DatePicker dateChoice;
    @FXML
    private Button quitterButton;
    @FXML
    private TextField motifLabel;

    // FONCTIONS FXML
    @FXML
    private void onProfChoicebox(ActionEvent e){
        updateHeureChoicebox();
        updateDatepicker();
    }

    @FXML
    private void onDatepicker(ActionEvent e){
        updateHeureChoicebox();
    }

    @FXML
    private void envoyerButton(ActionEvent e) throws IOException{
        if (profChoicebox.getSelectionModel().getSelectedItem()!=null && heureChoicebox.getSelectionModel().getSelectedItem()!=null){ // Check si le prof, la date et l'heure sont choisis
            String profName = profChoicebox.getSelectionModel().getSelectedItem();
            String heure = heureChoicebox.getSelectionModel().getSelectedItem();
            LocalDate date = dateChoice.getValue();
            String motif = motifLabel.getText();
            try {
                // Ajouter rdv à la bd
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection( "jdbc:sqlite:ProfRDV/src/database/data-2.db" );
            System.out.println("cc");
            pst = connection.prepareStatement("select * from users where Type=(?) and Nom=(?)" );
            pst.setString(1,"Professeur");
            pst.setString(2,profName);
            rs = pst.executeQuery();

            String eId = this.user.eleve.geteId();
            String pId = rs.getString("uId");
            String Date = localdateToString(date);
            String Heure = heure;
            String Motif =motif;
            String Etat = "En attente";

            System.out.println("cc1");
            PreparedStatement pst3 = connection.prepareStatement("insert into rdv (eId, pId, Date, Heure, Motif, Etat) values ((?), (?), (?), (?), (?), (?))" );
            pst3.setString(1,eId);
            pst3.setString(2,pId);
            pst3.setString(3,Date);
            pst3.setString(4,Heure);
            pst3.setString(5,Motif);
            pst3.setString(6,Etat);
            pst3.execute();
            connection.close();
            }
            catch (Exception e1){
                System.out.println(""+e1.getMessage());
            }
            gotoListerdv();
        }   
        
        
    }

    private String localdateToString(LocalDate date) {
        String formattedDate = date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        return formattedDate;
    }

    private void gotoListerdv() throws IOException {
        Stage stage = main.Main.getStage();
        Parent fxmlLoader = FXMLLoader.load(getClass().getResource("../view/listerdv-2.fxml"));
        stage.setScene(new Scene(fxmlLoader, 600, 500));
    }

    // AUTRES FONCTIONS
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

    private void updateDatepicker(){
        if (profChoicebox.getSelectionModel().getSelectedItem()!=null){
            Prof profSelected = profByName(profChoicebox.getSelectionModel().getSelectedItem());
            dateChoice.setDayCellFactory(picker -> new DateCell() {
                public void updateItem(LocalDate date, boolean empty) {
                    super.updateItem(date, empty);
                    LocalDate today = LocalDate.now();
                    if (profSelected.getCreneaux()!=null){
                        setDisable(empty || isInvalidDate(date) || date.compareTo(today) < 0 ) ;
                    } else {
                        setDisable(empty || date.compareTo(today) < 0 );
                    }
                }

            });
        }
    }

    private boolean isInvalidDate(LocalDate date) {
        if (profChoicebox.getSelectionModel().getSelectedItem()!=null){
            Prof profSelected = profByName(profChoicebox.getSelectionModel().getSelectedItem());
            for (CreneauxUsuels creneau : profSelected.getCreneaux()){
                if (date.getDayOfWeek() == creneau.getJour()){
                    return false;
                }
            } 
        }
        return true;
    }

    private void updateHeureChoicebox(){
        if (profChoicebox.getSelectionModel().getSelectedItem()!=null){
            heureChoicebox.getItems().clear();
            Prof profSelected = profByName(profChoicebox.getSelectionModel().getSelectedItem());
            if (profSelected.getCreneaux()!=null && dateChoice.getValue()!=null){
                for (CreneauxUsuels creneau : profSelected.getCreneaux()){
                    if (creneau.getJour().equals(dateChoice.getValue().getDayOfWeek())){
                        heureChoicebox.getItems().addAll(horaires(creneau));
                    }
                } 
            } 
        }
    }

    private ArrayList<String> horaires(CreneauxUsuels creneau) {
        ArrayList<String> horaires = new ArrayList<String>();
        String[] heureDebut = creneau.getHeureDebut().split(":");
        String[] heureFin = creneau.getHeureFin().split(":");
        for (int i = Integer.parseInt(heureDebut[0])*60 +  Integer.parseInt(heureDebut[1])  ; i < Integer.parseInt(heureFin[0])*60 + Integer.parseInt(heureFin[1]) - 10; i = i+10) {
            if (i%60 == 0){
                horaires.add(String.valueOf(i/60)+":0"+String.valueOf(i%60));
            } else {
                horaires.add(String.valueOf(i/60)+":"+String.valueOf(i%60));
            }
        }
        return horaires;
    }

    // INITIALISE
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        createData();   // Génération des données
        // BIEN CONFIGURER LES CHOICEBOX ET TOUT
        for (Prof prof : profs){
            profChoicebox.getItems().add(prof.getpName());
        }
    }

    

    //AUTRES FONCTIONS
    private void createData() {
        // AJOUT DES PROFS
        try{
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection( "jdbc:sqlite:ProfRDV/src/database/data-2.db" );
            pst = connection.prepareStatement("select * from users where Type=(?) " );
            pst.setString(1,"Professeur");
            rs = pst.executeQuery();

            while(rs.next()){
                pst2 = connection.prepareStatement("select * from disponibiltes inner join users on users.uId=disponibiltes.pId where users.uID = (?)" );
                pst2.setString(1,rs.getString("uId"));
                rs2 = pst2.executeQuery();

                ArrayList<CreneauxUsuels> creneauxUsuels = new ArrayList<CreneauxUsuels>();
                while(rs2.next()){
                    DayOfWeek jour = DayOfWeek.MONDAY;;
                    String jourbd = rs2.getString("Date");
                    if (jourbd.equals("Lundi")){
                        jour = DayOfWeek.MONDAY;
                    } else if (jourbd.equals("Mardi")) {
                        jour = DayOfWeek.TUESDAY;
                    } else if (jourbd.equals("Mercredi")) {
                        jour = DayOfWeek.WEDNESDAY;
                    } else if (jourbd.equals("Jeudi")) {
                        jour = DayOfWeek.THURSDAY;
                    } else if (jourbd.equals("Vendredi")) {
                        jour = DayOfWeek.FRIDAY;
                    } else if (jourbd.equals("Samedi")) {
                        jour = DayOfWeek.SATURDAY;
                    } else if (jourbd.equals("Dimanche")) {
                        jour = DayOfWeek.SUNDAY;
                    }
                    CreneauxUsuels creneauUsuel = new CreneauxUsuels(jour, rs2.getString("Heure"), rs2.getString("Heurefin"));
                    creneauxUsuels.add(creneauUsuel);
                }
                Prof prof1 = new Prof(rs.getString("uId"), rs.getString("Nom"), rs.getString("Email"),creneauxUsuels) ;
                profs.add(prof1);
            }
            connection.close();

        }
        catch (Exception e){
            System.out.println(""+e.getMessage());
        }
    }

    private Prof profByName(String name) {
        for (Prof prof : profs){
            if (prof.getpName().equals(name)){
                return prof;
            }
        }
        return null;
    }
    
}
