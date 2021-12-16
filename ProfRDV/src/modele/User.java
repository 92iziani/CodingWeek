package modele;

public class User {
    public Eleve eleve;
    public Prof prof;
    String test = "hello";

    public void setEleve(Eleve eleve) {
        this.eleve = eleve;
    }

    public void setProf(Prof prof) {
        this.prof = prof;
    }

    public String getTest(){
        return this.test;
    }
}
