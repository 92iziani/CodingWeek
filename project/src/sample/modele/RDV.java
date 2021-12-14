package sample.modele;

public class RDV {
    private String rId;
    private String eId;
    private String pId;
    private Boolean etat;
    private Creneau creneau;
    private String motif;

    public RDV() {
        this.rId = "daSilva";
        this.eId = "daSilva";
        this.pId = "daSilva";
        this.etat = false;
        this.motif = "daSilva";
    }

    public RDV(String rId, String eId, String pId, Boolean etat, Creneau creneau, String motif) {
        this.rId = rId;
        this.eId = eId;
        this.pId = pId;
        this.etat = etat;
        this.creneau = creneau;
        this.motif = motif;
    }

    public String getrId() {
        return rId;
    }

    public void setrId(String rId) {
        this.rId = rId;
    }

    public String geteId() {
        return eId;
    }

    public void seteId(String eId) {
        this.eId = eId;
    }

    public String getpId() {
        return pId;
    }

    public void setpId(String pId) {
        this.pId = pId;
    }

    public Boolean getEtat() {
        return etat;
    }

    public void setEtat(Boolean etat) {
        this.etat = etat;
    }

    public String getMotif() {
        return motif;
    }

    public void setMotif(String motif) {
        this.motif = motif;
    }

    public Creneau getCreneau() {
        return creneau;
    }

    public void setCreneau(Creneau creneau) {
        this.creneau = creneau;
    }
}
