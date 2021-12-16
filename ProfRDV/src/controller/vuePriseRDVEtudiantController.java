package controller;

import java.io.IOException;
import java.net.URL;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

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
import javafx.stage.Stage;
import modele.CreneauxUsuels;
import modele.Prof;
import modele.User;


public class vuePriseRDVEtudiantController implements Initializable{

    // AUTRES
    ArrayList<Prof> profs = new ArrayList<Prof>();

    // CONTROLES FXML
    @FXML 
    private ChoiceBox<String> profChoicebox;
    @FXML
    private ChoiceBox<String> heureChoicebox;
    @FXML
    private DatePicker dateChoice;
    @FXML
    private Button quitterButton;

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
        if (profChoicebox.getSelectionModel().getSelectedItem()!=null && heureChoicebox.getSelectionModel().getSelectedItem()!=null && profChoicebox.getSelectionModel().getSelectedItem()!=null){ // Check si le prof, la date et l'heure sont choisis
            gotoListerdv();
            // Ajouter rdv à la bd
        }   
        
        
    }

    private void gotoListerdv() throws IOException {
        Stage stage = main.Main.getStage();
        Parent fxmlLoader = FXMLLoader.load(getClass().getResource("../view/listerdv-2.fxml"));
        stage.setScene(new Scene(fxmlLoader, 600, 500));
    }

    // AUTRES FONCTIONS
    public void closeApplication(){
        Stage stage = (Stage) quitterButton.getScene().getWindow();
        stage.close();
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
        String[] heureDebut = creneau.getHeureDebut().split("h");
        String[] heureFin = creneau.getHeureFin().split("h");

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
        Prof prof1 = new Prof("0", "Dudu", "dudu@gmail.com", new ArrayList<CreneauxUsuels>(Arrays.asList(new CreneauxUsuels(DayOfWeek.MONDAY, "8h00", "12h00"),new CreneauxUsuels(DayOfWeek.TUESDAY, "8h00", "12h00"))));
        Prof prof2 = new Prof("1", "Didi", "didi@gmail.com", new ArrayList<CreneauxUsuels>(Arrays.asList(new CreneauxUsuels(DayOfWeek.WEDNESDAY, "14h00", "18h00"),new CreneauxUsuels(DayOfWeek.FRIDAY, "16h00", "17h30"))));
        Prof prof3 = new Prof("2", "Dada", "dada@gmail.com", new ArrayList<CreneauxUsuels>());
        profs.add(prof1);
        profs.add(prof2);
        profs.add(prof3);
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
