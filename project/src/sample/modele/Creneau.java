package sample.modele;

import java.time.LocalDate;

public class Creneau {
    private LocalDate date;
    private String heure;

    public Creneau() {
    }

    public Creneau(LocalDate date, String heure) {
        this.date = date;
        this.heure = heure;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getHeure() {
        return heure;
    }

    public void setHeure(String heure) {
        this.heure = heure;
    }
}
