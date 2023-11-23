package tunti6.apuluokat;

public class Henkilo {
    private int id;
    private String nimi;
    private int ika;

    public Henkilo(String nimi, int ika) {
        this.nimi = nimi;
        this.ika = ika;
    }

    public Henkilo(int id, String nimi, int ika) {
        this.id = id;
        this.nimi = nimi;
        this.ika = ika;
    }

    public String getNimi() {
        return nimi;
    }
    
    public void setNimi(String nimi) {
        this.nimi = nimi;
    }
    
    public void setIka(int ika) {
        this.ika = ika;
    }

    public int getIka() {
        return ika;
    }
    
    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Person{" +
        		"id='" + id + '\'' +
                "name='" + nimi + '\'' +
                ", age=" + ika +
                '}';
    }
}