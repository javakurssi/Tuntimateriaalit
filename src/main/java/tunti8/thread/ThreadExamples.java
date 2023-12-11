package tunti8.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class ThreadExamples {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        // Esimerkki 1: Singleton-luokka (IDGenerator), ei liity suoraan Threadeihin.
        IDGenerator idg = IDGenerator.getIDGenerator();
        int id = idg.nextID();
        System.out.println("ID: " + id);

        // Esimerkki 2: Säikeiden luominen ja nimien tulostaminen
        Runnable run = () -> System.out.println("Säie käynnistetty: " + Thread.currentThread().getName());
        System.out.println("Pääsäie: " + Thread.currentThread().getName());
        Thread t = new Thread(run);
        t.start();
        for (int i = 0; i < 5; i++) {
            new Thread(run).start();
        }

        // Esimerkki 3: Runnable-rajapinnan toteuttava luokka
        Thread t3 = new Thread(new DemoSäie());
        t3.start();

        // Esimerkki 4: Viive ja säikeiden tilan tarkistus
        while (t3.isAlive()) {
            try {
                Thread.sleep(100); //Pääsäie odottaa
            } catch (InterruptedException e) {
            }
        }
        System.out.println("Säie 3 päättynyt");
        
        // Esimerkki 5: Kriittisen alueen synkronointi (IDGenerator)
        IDGeneratorSync idgSync = IDGeneratorSync.getIDGenerator();
        int syncedId = idgSync.nextID();
        System.out.println("Synkronoitu ID: " + syncedId);

        // Esimerkki 6: ExecutorService ja Callable
        int threadCount = 5;
        List<Callable<Integer>> threads = new ArrayList<>();

        for (int i = 0; i < threadCount; i++) {
            threads.add(new IDConsumerCallable(10));
        }
        
        ExecutorService executor = Executors.newFixedThreadPool(threadCount);

        List<Future<Integer>> futures = executor.invokeAll(threads); // Palautetaan futureja, eli lupaus arvosta tulevaisuudessa.
        		
        int resultId = 0;
        for (Future<Integer> future : futures) {
            resultId = future.get(); // Future palautuu vasta kun Threadin suoritus on valmis, blokkaa pääthreadia siihen asti.
            System.out.println("ID säikeen päättyessä: " + resultId);
        }
        // Executorin sulkeminen
        executor.shutdown();
        
        //Tarkistetaan Singleton IDGeneratorin lopputulos, jota eri säikeet ovat muokanneet.
        IDGeneratorSync idGenerator = IDGeneratorSync.getIDGenerator();
        int finalId = idGenerator.getLastId();
        System.out.println("Lopullinen ID: " + finalId);
    }
}

class DemoSäie implements Runnable {
    @Override
    public void run() {
        System.out.println("Luokka ja säiemetodi: " + Thread.currentThread().getName());
    }
}
