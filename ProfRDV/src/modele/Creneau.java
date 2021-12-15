package modele;

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

    public Creneau(String date2, String heure2) {
        String[] date = date2.split("/");
        this.date = LocalDate.of(Integer.parseInt(date[2]), Integer.parseInt(date[1]), Integer.parseInt(date[0]));
        this.heure = heure2;
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
