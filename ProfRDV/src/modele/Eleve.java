package modele;

public class Eleve {
    private String eId;
    private String eName;
    private String eEmail;

    public Eleve() {
    }

    public Eleve(String eId, String eName, String eEmail) {
        this.eId = eId;
        this.eName = eName;
        this.eEmail = eEmail;
    }

    public String geteId() {
        return eId;
    }

    public void seteId(String eId) {
        this.eId = eId;
    }

    public String geteName() {
        return eName;
    }

    public void seteName(String eName) {
        this.eName = eName;
    }

    public String geteEmail() {
        return eEmail;
    }

    public void seteEmail(String eEmail) {
        this.eEmail = eEmail;
    }
}
