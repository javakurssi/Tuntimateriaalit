package tunti8.thread;

import java.util.concurrent.Callable;

public class IDConsumerCallable implements Callable<Integer> {
    private int counter = 10;

    public IDConsumerCallable(int counter) {
        this.counter = counter;
    }

    @Override
    public Integer call() throws Exception {
    	IDGeneratorSync idg = IDGeneratorSync.getIDGenerator();
        System.out.println("Callable starting...");
        int id = 0;

        for (int i = 0; i < counter; i++) {
            id = idg.nextID(); //Kasvatetaan id:tä säieturvallisesti koska nextID-metodi on synkronoitu, eli kaksi säiettä eivät voi kutsua sitä yhtä aikaa
            //System.out.println("\t" + this.toString() + " " + id);
        }

        System.out.println("Säie: "+this.toString() + " id tulos: " + id);
        return id;
    }
}
