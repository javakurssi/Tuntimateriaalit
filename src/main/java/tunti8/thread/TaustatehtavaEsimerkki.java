package tunti8.thread;

/**
 * Tämä esimerkki havainnollistaa tyypillistä säikeiden käyttöä.
 * Suoritamme siis aikaavievän tehtävän taustalla.
 */
public class TaustatehtavaEsimerkki {
    public static void main(String[] args) {
        System.out.println("Pääsäie: " + Thread.currentThread().getName());

        // Luo Runnable, joka edustaa taustatehtävää
        Runnable taustatehtava = () -> {
            System.out.println("Taustatehtävä käynnistetty: " + Thread.currentThread().getName());
            // Simuloi aikaa vievää toimintoa kuten isoa taustalaskentaa, tietojen hakemista useista paikoista yhtä aikaa jne.
            suoritaAikaaVievaTehtava();
            System.out.println("Taustatehtävä suoritettu: " + Thread.currentThread().getName());
        };
        
        // Luo uusi säie taustatehtävälle
        Thread taustasäie = new Thread(taustatehtava);

        // Käynnistä taustasäie
        taustasäie.start();

        // Jatka pääsäikeen työtä
        for (int i = 0; i < 5; i++) {
            System.out.println("Pääsäie työskentelee...");
            try {
                Thread.sleep(1000); // Simuloi muuta työtä pääsäikeessä
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Pääsäie suoritti työnsä.");

        // Odota taustasäikeen suorittamista ennen ohjelman lopettamista
        try {
            taustasäie.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Ohjelma lopetetaan.");
    }

    private static void suoritaAikaaVievaTehtava() {
    	 for (int i = 0; i < 20; i++) {
             System.out.println("Sivusäie työskentelee...");
             try {
                 Thread.sleep(500); // Simuloi työtä sivusäikeessä
             } catch (InterruptedException e) {
                 e.printStackTrace();
             }
         }
    }
}
