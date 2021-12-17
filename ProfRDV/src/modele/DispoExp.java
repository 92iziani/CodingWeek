package modele;

import java.time.LocalDate;

public class DispoExp {
    private LocalDate jour;
    private String HeureDebut;
    private String HeureFin;
    private boolean dispo; //1 dispo, 0 indispo

    public DispoExp(){

    }

    public DispoExp(LocalDate jour, String HeureDebut, String HeureFin, boolean dispo){
        this.jour = jour;
        this.HeureDebut = HeureDebut;
        this.HeureFin = HeureFin;
        this.dispo = dispo;
    }

    public LocalDate getJour() {
        return this.jour;
    }

    public void setJour(LocalDate jour) {
        this.jour = jour;
    }

    public String getHeureDebut() {
        return this.HeureDebut;
    }

    public void setHeureDebut(String HeureDebut) {
        this.HeureDebut = HeureDebut;
    }

    public String getHeureFin() {
        return this.HeureFin;
    }

    public void setHeureFin(String HeureFin) {
        this.HeureFin = HeureFin;
    }

    public boolean isDispo() {
        return this.dispo;
    }

    public boolean getDispo() {
        return this.dispo;
    }

    public void setDispo(boolean dispo) {
        this.dispo = dispo;
    }
}
