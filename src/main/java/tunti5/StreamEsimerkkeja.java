package tunti5;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.stream.Collectors;
import static java.util.stream.Collectors.*;

import tunti5.apuluokat.Henkilo;

public class StreamEsimerkkeja {

    public static void main(String[] args) {
        List<String> automerkit = List.of("Volvo", "BMW", "Mersu", "Volvo");
        
        List<Henkilo> henkilot = List.of(
                new Henkilo("Liisa", 25),
                new Henkilo("Ville", 30),
                new Henkilo("Kalle", 22),
                new Henkilo("Taina", 28),
                new Henkilo("Jere", 15)
        );
        
        // Esimerkki 1: 
        List<Integer> lengths = getMerkkijonojenPituudet(automerkit);
        System.out.println("Merkkijonojen pituus: " + lengths);

        // Esimerkki 2:
        Optional<Henkilo> henkilo = findByNimi(henkilot, "Ville");
        henkilo.ifPresent(h -> System.out.println("Found: " + h));
        
        // Esimerkki 3:
        double keskiIka = getKeskiIka(henkilot, 18);
        System.out.println("Henkilöiden keski-ikä: " + keskiIka);
        
        // Esimerkki 4: 
        Map<String, Long> stringCountMap = laskeEsiintymat(automerkit);
        stringCountMap.forEach((key, value) -> System.out.println(key + ": " + value));
        
        // Esimerkki 5:
        String htmlLista = luoHtmlLista(henkilot);
        System.out.println("HTML Lista:\n" + htmlLista);
    }

    // Esimerkki 1: Käytetään Streameja ja Lambdoja merkkijonojen pituuden selvittämiseen
    public static List<Integer> getMerkkijonojenPituudet(List<String> strings) {
        return strings.stream()
                .map(String::length) // Double colon method reference (ks. esim https://www.geeksforgeeks.org/double-colon-operator-in-java/)
                .collect(Collectors.toList()); // Kerätään tulokset streamista collect-metodilla ja toList-collectorilla.
    }
    
    // Esimerkki 2: Etsi henkilöä nimellä. Tulos on Optional<Henkilo>, koska henkilöä ei välttämättä löydy.
    public static Optional<Henkilo> findByNimi(List<Henkilo> henkilot, String nimi) {
        /*for (Henkilo henkilo : henkilot) {
            if (henkilo.getNimi().equals(nimi)) {
                return Optional.of(henkilo); // Löytyi, kääritään optionaliin.
            }
        }
        return Optional.empty();*/ // Ei löytynyt, palautetaan tyhjä Optional
   
	    return henkilot.stream()
	            .filter(henkilo -> henkilo.getNimi().equalsIgnoreCase(nimi))
	            .findFirst();
    }

    // Esimerkki 3: Käytetään Streameja, Lambdoja ja Optional-luokkaa henkilöiden keski-iän laskemiseen.
    public static double getKeskiIka(List<Henkilo> henkilot, int alaIkaraja) {
        OptionalDouble average = henkilot.stream() // average palauttaa "optional" arvon.
                .filter(henkilo -> henkilo.getIka() > alaIkaraja) // Lambda-funktio, poista henkilöt jotka ovat alle ikärajan
        		.mapToDouble(h -> h.getIka()) // Lambda-funktio, tässä toimisi myös method reference Henkilo::getAge
                .average(); // Double streamista saa ulos esim. averagen suoraan.
        return average.orElse(0);
    }
    
    // Esimerkki 4: Sama kuin viikolla 4, mutta nyt toteutettu streameilla.
    private static <T> Map<T, Long> laskeEsiintymat(List<T> list) {
        Map<T, Long> esiintymat = list.stream()
                .collect(groupingBy(s -> s, counting())); // Käytetään groupingBy-collectoria ja counting-collectoria.
        return esiintymat;
    }
    
    // Esimerkki 5: Luodaan henkilölistasta html-renderöitävä versio, tämä on helppoa mappauksen avulla ja tyypillistä web-ohjelmoinnissa.
    public static String luoHtmlLista(List<Henkilo> henkilot) {
        return henkilot.stream()
                .map(henkilo -> "<li>Nimi: " + henkilo.getNimi() + ", Ikä: " + henkilo.getIka() + "</li>")
                .collect(Collectors.joining("\n", "<ul>", "</ul>")); // Collectorina merkkijonolle Collectors.joining-apuri
    }

}

