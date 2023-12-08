package tunti8.rekursio;

public class RekursioEsimerkki {
    public static void main(String[] args) {
        int luku = 5;

        System.out.println("Rekursiivisen laskeKertoma-funktion tulos: " + laskeKertoma(luku));
        
        tulostaTakaperin("Hei, maailma!");
    }

    /**
     * Rekursiivinen funktio laskee annetun luvun kertoman
     * Funktio kutsuu siis itse itseään.
     */
    private static int laskeKertoma(int n) {
        // Perustapaus: kertoma 0 on 1
        if (n == 0) {
            return 1;
        } else {
            // Rekursiivinen tapaus: kertoma(n) = n * kertoma(n-1)
            return n * laskeKertoma(n - 1);
        }
    }
    
    /**
     * Rekursiivinen funktio tulostaa merkkijonon takaperin
     */
    private static void tulostaTakaperin(String teksti) {
        // Perustapaus: tyhjä tai yhden merkin pituinen merkkijono
        if (teksti.length() <= 1) {
            System.out.print(teksti);
        } else {
            // Rekursiivinen tapaus: tulosta viimeinen merkki ja kutsu funktiota ilman sitä
            System.out.print(teksti.charAt(teksti.length() - 1));
            tulostaTakaperin(teksti.substring(0, teksti.length() - 1));
        }
    }
}