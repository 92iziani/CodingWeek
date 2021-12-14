package sample.modele;

public class Creneau {
    private String date;
    private String heure;

    public Creneau() {
    }

    public Creneau(String date, String heure) {
        this.date = date;
        this.heure = heure;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHeure() {
        return heure;
    }

    public void setHeure(String heure) {
        this.heure = heure;
    }
}
