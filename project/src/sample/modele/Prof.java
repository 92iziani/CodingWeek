package sample.modele;

import java.util.ArrayList;

public class Prof {
    private String pId;
    private String pName;
    private String pEmail;
    private ArrayList<Creneau> creneaux;

    public Prof() {
    }

    public Prof(String pId, String pName, String pEmail, ArrayList<Creneau> creneaux) {
        this.pId = pId;
        this.pName = pName;
        this.pEmail = pEmail;
        this.creneaux = creneaux;
    }

    public String getpId() {
        return pId;
    }

    public void setpId(String pId) {
        this.pId = pId;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public String getpEmail() {
        return pEmail;
    }

    public void setpEmail(String pEmail) {
        this.pEmail = pEmail;
    }

    public ArrayList<Creneau> getCreneaux() {
        return creneaux;
    }

    public void setCreneaux(ArrayList<Creneau> creneaux) {
        this.creneaux = creneaux;
    }
}
