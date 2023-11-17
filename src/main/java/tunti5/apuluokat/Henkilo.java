package tunti5.apuluokat;

public class Henkilo {
    private String nimi;
    private int ika;

    public Henkilo(String nimi, int age) {
        this.nimi = nimi;
        this.ika = age;
    }

    public String getNimi() {
        return nimi;
    }

    public int getIka() {
        return ika;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + nimi + '\'' +
                ", age=" + ika +
                '}';
    }
}