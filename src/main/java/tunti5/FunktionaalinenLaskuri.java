package tunti5;

import java.util.function.*;

/**
 * Tämän esimerkin on tarkoitus havainnollistaa funktionaalista ohjelmointia ja lambda-funktioita
 * yleisellä tasolla. 
 * Vertailuna vastaavaan edellisen tunnin oliolaskuriin ((1+(2*(3+2))) = 11).
 */
public class FunktionaalinenLaskuri {

    public static void main(String[] args) {
        // Määritellään kaksi lambdafunktiota, jotka ottavat kaksi integer argumenttia ja palauttavat integerin
        BiFunction<Integer, Integer, Integer> sum = (x1, x2) -> x1 + x2; // Lambda-funktio joka ottaa kaksi argumenttia.
        BiFunction<Integer, Integer, Integer> mul = (x1, x2) -> x1 * x2;

        // Kutsutaan funktioita BiFunction functional interfacen apply-metodilla.
        int result = sum.apply(1, mul.apply(2, sum.apply(3, 2)));

        System.out.println(result);
    }
}