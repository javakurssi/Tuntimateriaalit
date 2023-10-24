package tunti1;

import java.util.ArrayList;
import java.util.List;

public class Tunti1 {

    public static void main(String[] args) {
        // Esimerkkejä muuttujista
        int number = 42;
        double pi = 3.14;
        String message = "Hello, world!";
        boolean isJavaFun = true;

        // Esimerkkejä kontrollirakenteista
        if (isJavaFun) {
            System.out.println("Java is fun!");
        } else {
            System.out.println("Java is not fun?");
        }

        int dayOfWeek = 3;
        switch (dayOfWeek) {
            case 1:
                System.out.println("It's Monday");
                break;
            case 2:
                System.out.println("It's Tuesday");
                break;
            // ...

            default:
                System.out.println("It's some other day");
        }

        int x = 5;
        int y = (x > 0) ? 1 : -1;

        // Esimerkkejä silmukoista
        int[] numbersArray = { 1, 2, 3, 4, 5 };
        for (int i = 0; i < numbersArray.length; i++) {
            System.out.print(numbersArray[i] + " ");
        }

        for (int num : numbersArray) {
            System.out.print(num + " ");
        }

        int i = 0;
        while (i < 5) {
            System.out.print(i + " ");
            i++;
        }

        // Esimerkkejä metodeista ja parametreistä
        int sum = add(3, 5);
        System.out.println("Sum: " + sum);

        // Esimerkkejä taulukoista ja listoista
        int[] array = { 1, 2, 3, 4, 5 };
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);

        // Esimerkkejä olio-ohjelmoinnin perusteista
        Animal animal = new Animal("Elephant", 25);
        System.out.println("Person: " + animal.getName() + ", Age: " + animal.getWeight());

        // Esimerkkejä poikkeuskäsittelystä
        try {
            int result = divide(10, 0);
            System.out.println("Result: " + result);
        } catch (ArithmeticException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    // Esimerkkejä metodeista
    public static int add(int a, int b) {
        return a + b;
    }

    public static int divide(int a, int b) {
        return a / b;
    }

    // Esimerkkejä olio-ohjelmoinnin perusteista
    static class Animal {
        private String name;
        private int weight;

        public Animal(String name, int weight) {
            this.name = name;
            this.weight = weight;
        }

        public String getName() {
            return name;
        }

        public int getWeight() {
            return weight;
        }
    }
}
