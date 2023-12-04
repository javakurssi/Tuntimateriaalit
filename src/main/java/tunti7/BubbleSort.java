package tunti7;

public class BubbleSort {

    // Kuplalajittelu järjestää annetun kokonaislukutaulukon kasvavaan järjestykseen
    public static void bubbleSort(int[] arr) {
        int n = arr.length;

        // Ulompi toisto huolehtii siitä, että kaikki indeksit käydään läpi
        for (int i = 0; i < n - 1; i++) {

            // Sisempi toistorakenne vertaa ja vaihtaa peräkkäisiä keskenään,
            // jos ne ovat keskenään väärässä järjestyksessä:
            for (int j = 0; j < n - i - 1; j++) {

                // Kaksi peräkkäistä väärässä järjestyksessä?
                if (arr[j] > arr[j + 1]) {

                    // Vaihdetaan `j` ja `j+1` keskenään!
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;

                    // Tulosta väliaikainen tulos
                    tulostaTaulukko(arr);
                }
            }
        }
    }

    // Apumetodi taulukon tulostamiseen
    private static void tulostaTaulukko(int[] arr) {
        for (int value : arr) {
            System.out.print(value + " ");
        }
        System.out.println();
    }

    // Esimerkki käytöstä
    public static void main(String[] args) {
        int[] jaettavaTaulukko = {64, 34, 25, 90, 12, 22, 11};
        
        System.out.println("Alkuperäinen taulukko:");
        tulostaTaulukko(jaettavaTaulukko);
        
        // Suorita kuplalajittelu
        bubbleSort(jaettavaTaulukko);
        
        System.out.println("Lajiteltu taulukko:");
        tulostaTaulukko(jaettavaTaulukko);
    }
}
