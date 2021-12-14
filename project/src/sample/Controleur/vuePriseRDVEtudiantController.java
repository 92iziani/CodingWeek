import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import modele.Creneau;
import modele.Prof;

public class vuePriseRDVEtudiantController implements Initializable{

    // AUTRES
    ArrayList<Prof> profs = new ArrayList<Prof>();
    ArrayList<String> heuresJournée = new ArrayList<String>();

    // CONTROLES FXML
    @FXML 
    private ChoiceBox<String> profChoicebox;
    @FXML
    private ChoiceBox<String> heureChoicebox;
    @FXML
    private DatePicker dateChoice;

    public static String cc;

    @FXML
    private void onProfChoicebox(ActionEvent e){
        updateHeureChoicebox();
        updateDatepicker();
        vuePriseRDVEtudiantController.cc = "hello";
    }

    @FXML
    private void onDatepicker(ActionEvent e){
        updateHeureChoicebox();
        vuePriseRDVEtudiantController.cc = "hello";
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

                private boolean isInvalidDate(LocalDate date) {
                    for (Creneau creneau : profSelected.getCreneaux()){
                        if (date.compareTo(creneau.getDate()) == 0){
                            return false;
                        }
                    } 
                    return true;
                }
            });
        }
    }

    private void updateHeureChoicebox(){
        if (profChoicebox.getSelectionModel().getSelectedItem()!=null){
            heureChoicebox.getItems().clear();
            Prof profSelected = profByName(profChoicebox.getSelectionModel().getSelectedItem());
            if (profSelected.getCreneaux()!=null && dateChoice.getValue()!=null){
                for (Creneau creneau : profSelected.getCreneaux()){
                    if (creneau.getDate().equals(dateChoice.getValue())){
                        heureChoicebox.getItems().add(creneau.getHeure());
                    }
                } 
            }
        }
    }

    // INITIALISE
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        createData();   // Génération des données
        // BIEN CONFIGURER LES CHOICEBOX ET TOUT
        for (Prof prof : profs){
            profChoicebox.getItems().add(prof.getpName());
        }
        for (String heure : heuresJournée){
            heureChoicebox.getItems().add(heure);
        } 
    }

    

    //AUTRES FONCTIONS
    private void createData() {
        // AJOUT DES PROFS
        Prof prof1 = new Prof("0", "Dudu", "dudu@gmail.com", new ArrayList<Creneau>(Arrays.asList(new Creneau(LocalDate.of(2021, 12, 25), "8h00"),new Creneau(LocalDate.now(), "8h10"))));
        Prof prof2 = new Prof("1", "Didi", "didi@gmail.com", new ArrayList<Creneau>(Arrays.asList(new Creneau(LocalDate.now(), "14h00"),new Creneau(LocalDate.now(), "14h10"))));
        profs.add(prof1);
        profs.add(prof2);
        // CREATION DES CRENEAUXX HORAIRES : 8h00-11h40 et 14h00-17h40
        for (int i = 8;i<11; i++){
            for (int j = 0; j<60; j=j+10){
                if (j==0){
                    heuresJournée.add(String.valueOf(i)+":0"+String.valueOf(j));
                } else {
                    heuresJournée.add(String.valueOf(i)+":"+String.valueOf(j));
                }
            }
        }
        for (int i = 11;i<12; i++){
            for (int j = 0; j<50; j=j+10){
                if (j==0){
                    heuresJournée.add(String.valueOf(i)+":0"+String.valueOf(j));
                } else {
                    heuresJournée.add(String.valueOf(i)+":"+String.valueOf(j));
                }
            }
        }
        for (int i = 14;i<17; i++){
            for (int j = 0; j<60; j=j+10){
                if (j==0){
                    heuresJournée.add(String.valueOf(i)+":0"+String.valueOf(j));
                } else {
                    heuresJournée.add(String.valueOf(i)+":"+String.valueOf(j));
                }
            }
        }
        for (int i = 17;i<18; i++){
            for (int j = 0; j<50; j=j+10){
                if (j==0){
                    heuresJournée.add(String.valueOf(i)+":0"+String.valueOf(j));
                } else {
                    heuresJournée.add(String.valueOf(i)+":"+String.valueOf(j));
                }
            }
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
