package tunti4;

import java.util.List;
import java.util.Map;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * Tämän esimerkin tarkoituksena on havainnollistaa olioiden monimuotoisuutta ja
 * rajapintojen käyttämistä.
 */
public class ListaEsimerkki {

    public static void main(final String[] args) {
        List<Integer> numeroLista = new LinkedList<>();
        numeroLista.add(1);
        numeroLista.add(2);
        numeroLista.add(3);
        numeroLista.add(1);
        System.out.println(laskeEsiintymat(numeroLista));

        List<String> tekstiLista = new LinkedList<>();
        tekstiLista.add("Pekka");
        tekstiLista.add("Maija");
        tekstiLista.add("Elina");
        tekstiLista.add("Pekka");
        System.out.println(laskeEsiintymat(tekstiLista));

        List<OmaObjekti> omaLista = new LinkedList<>();
        omaLista.add(new OmaObjekti(1, "Volvo"));
        omaLista.add(new OmaObjekti(2, "Mersu"));
        omaLista.add(new OmaObjekti(3, "BMW"));
        omaLista.add(new OmaObjekti(4, "Volvo"));
        System.out.println(laskeEsiintymat(omaLista));
        
        // Bonuksena vielä sorttaus Comparable rajapinnalla
        Collections.sort(omaLista);
        System.out.println("Aakkosjärjestetty autolista:  "+omaLista);

    }

    /**
     * 
     * 
     * @param list
     * @return Metodi palauttaa Mapin, jossa on avaimena parametrina saadun Listan
     *         objekti ja keynä lukumäärä kuinka monta samaa objektia listassa oli.
     */
    private static <T> Map<T, Integer> laskeEsiintymat(List<T> list) {
        Map<T, Integer> tulos = new HashMap<>();
        for (T object : list) {
            if (tulos.containsKey(object)) {
                tulos.put(object, tulos.get(object) + 1);
            } else {
                tulos.put(object, 1);
            }
        }
        return tulos;
    }
}

/**
 * Lisätään esimerkkiin vielä oma objekti, jolla on id ja tekstikenttä ja
 * overridataan luokan hashcode ja equals-metodit siten, että pelkkä
 * tekstisisällön samankaltaisuus tarkoittaa samankaltaisuutta, sitten se
 * voidaan myös antaa mielekkääseen vertailuun laskeEsiintymat metodille.
 * 
 * Luokka toteuttaa myös Comparable rajapinnan, jolloin OmaObjekteja voidaan myös järjestää suhteessa toisiinsa.
 * Tässä tapauksessa järjestys tapahtuu tekstiarvon perusteella String-luokan omaa compare-toteutusta hyödyntäen.
 * Tämä käytännössä aiheuttaa OmaObjektien järjestämisen aakkosjärjestykseen.
 */
class OmaObjekti implements Comparable<OmaObjekti> {
    private int id;
    private String tekstiArvo;

    public OmaObjekti(int id, String tekstiArvo) {
        this.id = id;
        this.tekstiArvo = tekstiArvo;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof OmaObjekti)) {
            return false;
        }
        OmaObjekti toinenObjekti = (OmaObjekti) o;
        return this.tekstiArvo.equals(toinenObjekti.getTekstiArvo());
    }

    @Override
    public int hashCode() {
        return this.tekstiArvo.hashCode();
    }

    @Override
    public String toString() {
        return this.getTekstiArvo();
    }

    public String getTekstiArvo() {
        return this.tekstiArvo;
    }

    @Override
    public int compareTo(OmaObjekti other) {
        return this.tekstiArvo.compareTo(other.tekstiArvo);
    }
}
