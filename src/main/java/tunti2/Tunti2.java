package tunti2;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/*
 * Tässä tiedostossa on esimerkkejä Map tietorakenteen käyttämisestä. Esimerkit löytyvät myös sivulta
 * https://ohjelmointi2.github.io/map/
 */
public class Tunti2 {

    public static void main(String[] args) {

        // 1. Hajautustaulu
        Map<String, String> postinumerot = new HashMap<String, String>();

        postinumerot.put("00710", "Helsinki");
        postinumerot.put("90014", "Oulu");
        postinumerot.put("33720", "Tampere");
        postinumerot.put("33014", "Tampere");
        
        System.out.println("1. Postinumero 00710: " + postinumerot.get("00710")); // tulostaa "Helsinki"

        // 2. Numeroiden käsitteleminen mapissa
        Map<String, Integer> opintopisteet = new HashMap<String, Integer>();

        // Lisätään arvoja tietyille avaimille:
        opintopisteet.put("swd1tn001", 5);
        opintopisteet.put("swd1tn002", 5);

        // Haetaan yksi arvo:
        int pisteet = opintopisteet.get("swd1tn002");
        System.out.println("2. Opintopisteet swd1tn002: " + pisteet); // 5
        
        Integer pisteetFeikki = opintopisteet.getOrDefault("feikkikurssi", 0);
        System.out.println("2.1. Opintopisteet feikkikurssi: " + pisteetFeikki); // 0

        // 3. Uuden arvon asettaminen
        Map<String, String> numerot = new HashMap<>();
        numerot.put("Uno", "Yksi");
        numerot.put("Dos", "Zwei");
        numerot.put("Uno", "Ein"); // korvaa aikaisemman arvon!
        numerot.putIfAbsent("Dos", "Kaksi"); // ei korvaa mitään
        numerot.replace("Tres", "Drei"); // ei lisää mitään

        System.out.println("3. Numerot: " + numerot);

        // 4. Arvojen poistaminen (remove) ja tarkastaminen (containsKey)
        Map<String, String> countries = new HashMap<>();
        countries.put("Suomi", "Finland");
        countries.put("Ruotsi", "Sweden");
        countries.put("Norja", "Norway");
        countries.containsKey("Ruotsi"); // true
        countries.remove("Ruotsi");
        countries.containsKey("Ruotsi"); // false

        System.out.println("4. Countries:" + countries);

        // 5. Usean arvon asettaminen samalle avaimelle:
        Map<String, List<String>> maat = new HashMap<>();

        List<String> fi = new ArrayList<String>();
        fi.add("Helsinki");
        fi.add("Espoo");
        fi.add("Vantaa");

        List<String> sv = new ArrayList<String>();
        sv.add("Tukholma");
        sv.add("Visby");

        maat.put("Suomi", fi);
        maat.put("Ruotsi", sv);

        System.out.println("5. Maat:" + maat);
        
        fi.add("Tampere");
        System.out.println("5.1. Maat, Tampere mukana:" + maat);

        // 6. Map:in koko sisällön läpikäynti
        Set<String> avaimet = numerot.keySet();
        
        // Käydään läpi kaikki avaimet:
        for (String avain : avaimet) {
            System.out.println("6.1 avain: " + avain);
        }

        Collection<String> arvot = numerot.values();
        
        // Käydään läpi kaikki arvot:
        for (String arvo : arvot) {
            System.out.println("6.3 arvo: " + arvo);
        }
        
        // Käydään läpi kaikki avain-arvo -parit:
        // Set<Entry<String, String>> entrySet = numerot.entrySet();
        for (Entry<String, String> avainArvoPari : numerot.entrySet()) {
	        System.out.println("Avain: " + avainArvoPari.getKey());
	        System.out.println("Arvo: " + avainArvoPari.getValue());
        }

    }
}
