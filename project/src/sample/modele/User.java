package sample.modele;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.modele.Eleve;
import sample.modele.Prof;

import java.io.IOException;
import java.util.Observable;


public class User extends Observable{

     Eleve eleve;
     Prof prof;
     String test = "hello";

    public void setEleve(Eleve eleve){
        this.eleve = eleve;
        setChanged();
        notifyObservers();
    }

    public void setProf(Prof prof){
        this.prof = prof;
        setChanged();
        notifyObservers();
    }

    public String getTest(){
        return this.test;
    }


}