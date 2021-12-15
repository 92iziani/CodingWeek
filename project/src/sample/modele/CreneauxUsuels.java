package sample.modele;

import java.time.DayOfWeek;

public class CreneauxUsuels {

    private DayOfWeek jour;
    private String heureDebut; // format 8h00
    private String heureFin;

    public CreneauxUsuels() {
    }

    public CreneauxUsuels(DayOfWeek jour, String heureDebut, String heureFin) {
        this.jour = jour;
        this.heureDebut = heureDebut;
        this.heureFin = heureFin;
    }

    public DayOfWeek getJour() {
        return this.jour;
    }

    public void setJour(DayOfWeek jour) {
        this.jour = jour;
    }

    public String getHeureDebut() {
        return this.heureDebut;
    }

    public void setHeureDebut(String heureDebut) {
        this.heureDebut = heureDebut;
    }

    public String getHeureFin() {
        return this.heureFin;
    }

    public void setHeureFin(String heureFin) {
        this.heureFin = heureFin;
    }
}
